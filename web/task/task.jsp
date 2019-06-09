<%-- 
    Document   : Test
    Created on : 13.07.2016, 16:56:55
    Author     : ksinn
--%>
<%!String pageTitle = "Task";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>

<div class="row">
    <div class="col offset-2 col-8 centered">
        <nav class="breadcrumbs">
            <ul>
                <li><a class="green-link" href="${pageContext.request.contextPath}/cabinet">Home</a></li>
                <li><a class="green-link" href="${pageContext.request.contextPath}/task/list?id=${task.list.id}">${task.list.name}</a></li>
                <li><span>Task</span></li>
            </ul>
        </nav>
        <div class="row space-top">
            <div class="col col-7">
                <h3>${task.list.name}</h3>
                <p><i class="fa fa-clock-o font-green" aria-hidden="true"></i> ${task.time} min  <i title="Ball" class="fa fa-diamond font-green" aria-hidden="true"></i> ${task.ball} </p>
            </div>
            <div class="col col-5 text-right">
                <p>
                    <a class="green-edit" href="edit/task?id=${task.id}">
                        <i class="fa fa-cog font-green" aria-hidden="true"></i>
                        Edit
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/DataBaseManager?group=${list.id}" class="green-edit">
                        <i class="fa fa-code font-green" aria-hidden="true"></i>
                        DBM 
                    </a>
                </p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col">
                <p>${task.question}</p>
                <b>${task.answer}</b><br>
            </div>
        </div>
        <hr>
        <div class="row space-top">
            <div class="col col-5">
                <p class="italic">Result:</p>
                <div class="text-center">
                    <c:choose>
                        <c:when test="${task.exception!=null}">
                            <b class="error">${task.exception.message}</b>
                        </c:when>                
                        <c:when test="${task.executeResult!=null}">
                            <table class="bordered striped">                                
                                <c:forEach var="row" items="${task.executeResult}">
                                    <tr>
                                        <c:forEach var="cell" items="${row}">
                                            <td>${cell}</td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </table> 
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="col col-7 text-center">
                <figure class="image">
                    <img width="600px" class="db-schem" src="${task.img}">
                    <figcaption>Relation schema of database</figcaption>
                </figure>
            </div>
        </div>
    </div>
</div>
<%@include file="/footer.jspf"%>

