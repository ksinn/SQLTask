<%-- 
    Document   : CreateTask
    Created on : 05.09.2016, 11:17:11
    Author     : ksinn
--%>
<%!String pageTitle = "Task";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>
<div class="row">
    <div class="col offset-2 col-8">
        <nav class="breadcrumbs">
            <ul>
                <li><a class="green-link" href="${pageContext.request.contextPath}/cabinet">Home</a></li>
                <li><a class="green-link" href="${pageContext.request.contextPath}/task/list?id=${task.list.id}">${task.list.name}</a></li>
                    <c:choose>
                        <c:when test="${not empty param.id}">
                        <li><a class="green-link" href="${pageContext.request.contextPath}/task/task?id=${task.id}">Task</a></li>
                        <li><span>Edit</span></li>
                        </c:when>
                        <c:otherwise>
                        <li><span>Add</span></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </nav>
        <div class="row space-top">
            <div class="col col-7">
                <c:choose>
                    <c:when test="${not empty param.id}">
                        <h3>Edit task in ${task.list.name}</h3>
                    </c:when>
                    <c:otherwise>
                        <h3>Add task in ${task.list.name}</h3>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col col-5 text-right">
                <p>
                    <a href="${pageContext.request.contextPath}/admin/DataBaseManager?group=${list.id}" class="green-edit">
                        <i class="fa fa-code font-green" aria-hidden="true"></i>
                        DBM 
                    </a>
                </p>
            </div>
        </div>

        <hr class="space-both">

        <div class="row centered">
            <div class="col col-9">
                <form id="form" class="form" action="" method="POST">
                    <input type="hidden" name="list" value="${task.list.id}"> 
                    <input type="hidden" name="id" value="${task.id}"> 

                    <div class="form-item sql-teatarea">
                        <label>Question:</label>
                        <textarea id="input" required name="question" rows="6">${task.question}</textarea>
                    </div>

                    <div class="form-item sql-teatarea">
                        <label>SQL answer:</label>                
                        <textarea class="<c:if test="${task.exception!=null}">error</c:if> " rows="6"  required name="answer">${task.answer}</textarea>
                            <label>
                            <c:if test="${task.exception!=null}">
                                <b class="error">${task.exception.message}</b>
                            </c:if>
                        </label>
                    </div>
                    <div class="row gutters centered">
                        <div class="col col-3">
                            <div class="form-item">
                                <label>Time(in minuts):</label>
                                <input class="w20" min="1" type="number" name="time" value="${task.time}">
                            </div>
                        </div>
                        <div class="col col-3">
                            <div class="form-item">
                                <label>Ball:</label>
                                <input class="w20" min="1" type="number" name="ball" value="${task.ball}">
                            </div>
                        </div> 
                        <div class="col col-3">
                            <div class="form-item">
                                <label>Image:</label> 
                                <input class="w20" type="text" name="img" value="${task.img}">
                            </div>
                        </div>
                    </div>
                    <div class="form-item text-centered">
                        <button class="button primary big">Complete</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div> 

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script> 
<script>
    $(document).ready(function () {

        $("#form").validate({
            rules: {
                answer: {
                    required: true,
                    minlength: 10,
                    maxlength: 500
                },
                question: {
                    required: true,
                    minlength: 10,
                    maxlength: 1000
                },
                time: {
                    number: true,
                    min: 0,
                    max: 120
                },
                ball: {
                    number: true,
                    min: 0,
                    max: 100
                }

            }

        });


    }); //end of ready
</script> 
<script src="<%=request.getServletContext().getContextPath()%>/resources/js/tinymce/tinymce.min.js"></script>
<script>

                    tinymce.init({
                        selector: '#input',
                        theme: 'modern',
                        plugins: [
                            'advlist autolink link lists charmap print preview hr anchor pagebreak spellchecker',
                            'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
                            'save table contextmenu directionality emoticons template paste textcolor'
                        ],
                        toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons'

                    });


</script>

<%@include file="/footer.jspf"%>


