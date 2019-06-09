<%-- 
    Document   : PassTest
    Created on : 09.08.2016, 16:40:28
    Author     : ksinn
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pageTitle!=null?pageTitle:"SQLTasKer"}</title>
        <link href="${pageContext.request.contextPath}/resources/img/favicon.png" rel="shortcut icon" type="image/x-icon">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kube_new.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kube.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/kube-ext.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/master.css">
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/kube.js"></script>

    </head>
    <body>
        <div class="row space-top">
            <div class="col bg-warning text-center" data-component="sticky">
                ${examinator.popMessage()}
            </div>
        </div>
        <div class="green row space-top">
            <div class="col col-12 centered">
                <script src="${pageContext.request.contextPath}/resources/js/flipclock.js"></script>         
                <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flipclock.css">

                <div class="row centered">
                    <div class="col col-8 text-center">
                        <figure>
                            <img src="${answer.task.img}">
                            <figcaption>Relation schema of database</figcaption>
                        </figure>
                    </div> 
                    <div class="col col-4 text-center">
                        <div class="row">
                            <div class="col offset-2 text-center">
                                <div class="clock" style=" zoom:0.8"></div>
                            </div>
                        </div>
                        <hr>
                        <div class="row centered">
                            <div class='col'>
                                <div class="row space-both">
                                    <div class="col offset-3 col-2 text-left"> 
                                        <span class="italic">${examinator.solvedProblems}/${examinator.work.count}</span>
                                    </div>
                                    <div class="col col-2 text-center">
                                        <button onclick="$.modalwindow({target: '#next-modal', width: '500px'});" class="label outline round small">Пропустить</button> 
                                    </div>
                                    <div class="col col-2 text-right"> 
                                        <span class="italic" title="ball"><i class="fa fa-diamond"></i> ${answer.task.ball}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row centered">
                            <div class="col text-left">        
                                <form class="form" id="checkAnswer" method="POST" action="">    
                                    <div class="form-item">
                                        <label class="strong">${answer.task.question}</label>
                                        <textarea id="sql_input" rows="8" required  name="answer">${answer.query}</textarea>
                                    </div>
                                    <div class="form-item text-center">
                                        <button id="run" class="warning small button round"><i class="fa fa-play"></i></button>
                                        <div class="desc">or press Shift+Enter</div>
                                    </div>
                                </form>
                            </div>
                        </div>   
                    </div>       
                </div>
                <hr class="space-top">
                <div class="row centered">
                    <div class="col col-1 pull-left">
                        <b><i>Result:</i></b>
                    </div>
                    <div class="col col-11"> 
                        <c:if test="${checkedAnswer!=null}">
                            <c:if test="${checkedAnswer.hasException()}">
                                <b style="color: #f00">${checkedAnswer.exception.message}</b>
                            </c:if>
                            <c:if test="${checkedAnswer.hasResultArray()}">
                                <table class="bordered striped">
                                    <c:forEach var="row" items="${checkedAnswer.resultArray}">
                                        <tr>
                                            <c:forEach var="cell" items="${row}">
                                                <td>${cell}</td>
                                            </c:forEach>
                                        </tr> 
                                    </c:forEach>    
                                </table>
                            </c:if>
                        </c:if>
                    </div>
                </div>   
                <div id="my-modal" class="modal-box hide">
                    <div class="modal">
                        <span class="close"></span>
                        <div class="modal-header">Woops</div>
                        <div class="modal-body text-center">
                            <p>Time expired</p>
                            <p><a id="time_out" href="next" class="button outline">next task &rArr;</a></p>
                        </div>
                    </div>
                </div>
                <div id="next-modal" class="modal-box hide">
                    <div class="modal">
                        <span class="close"></span>
                        <div class="modal-header">Пропустить задание</div>
                        <div class="modal-body text-center">                           
                            <p>Если вы пропустите задание, то вы не сможете потом к нему вернуться.</p>
                            <p>Вы уверены, что хотите пропустить это здание?</p>
                            <p class="strong">За это задание вам будет выстввлено <span class="error">0 балов</span></p>
                            <p><a href="next" class="button outline">Пропустить</a> <a href="#" class="button outline primary" data-action="modal-close">Нет</a></p>
                        </div>
                    </div>
                </div>
                <script>



                    var t = (${examinator.leftTime()}) / 1000;
                    if (t < 0)
                        t = 0;
                    clock = $('.clock').FlipClock(t, {
                        clockFace: 'MinuteCounter',
                        autoStart: true,
                        language: 'us-us',
                        countdown: true,
                        callbacks: {
                            stop: function () {
                                $('#my-modal').on('open.modal', function ()
                                {
                                    setTimeout(window.location = "${pageContext.request.contextPath}/exam/next", 10000);
                                });
                                $.modalwindow({target: '#my-modal', width: '300px'});



                            }
                        }

                    });
                    $('#sql_input').focus();
                    $('#sql_input').keydown(function (e) {
                        if (e.shiftKey && e.keyCode === 13) {
                            $('#run').click();
                            return false;
                        }
                    });
                </script> 

                <%@include file="/footer.jspf"%>



