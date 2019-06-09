<%-- 
    Document   : Group
    Created on : 23.10.2016, 20:18:31
    Author     : ksinn
--%>
<%!String pageTitle = "List";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>
<div class="row">
    <div class="col offset-2 col-8">
        <nav class="breadcrumbs">
            <ul>
                <li><a class="green-link" href="${pageContext.request.contextPath}/cabinet">Home</a></li>
                <li><span>${list.name}</span></li>
            </ul>
        </nav>
        <div class="row space-top">
            <div class="col col-7">
                <h3>${list.name}</h3>
                <c:choose>
                    <c:when test="${list.isPublic()}">
                        <i title="All user can use this list" class="fa fa-eye font-green" aria-hidden="true" ></i>
                    </c:when>
                    <c:otherwise>
                        <i title="Only you can use this list" class="fa fa-eye" aria-hidden="true"></i>
                    </c:otherwise>
                </c:choose>
                <span class="italic small muted">${list.tasks.size()} tasks</span>
            </div>
            <div class="col col-5 text-right">
                <p>
                    <a class="green-edit" href="edit/list?id=${list.id}">
                        <i class="fa fa-cog font-green" aria-hidden="true"></i>
                        Edit
                    </a>
                    <a href="add/task?list=${list.id}" class="green-edit">
                        <i class="fa fa-plus font-green" aria-hidden="true"></i>
                        Add task 
                    </a>
                    <a class="green-edit" href="?id=${list.id}&check=1">
                        <i class="fa fa-check-circle font-green" aria-hidden="true"></i>
                        Check
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/DataBaseManager?group=${list.id}" class="green-edit">
                        <i class="fa fa-code font-green" aria-hidden="true"></i>
                        DBM 
                    </a>
                </p>
            </div>
        </div>

        <hr class="space-both">

        <c:forEach var="task" items="${tasks}"> 
            <div class="row">
                <c:choose>
                    <c:when test="${param.check!=null}">
                        <c:choose>
                            <c:when test="${task.valid()}">
                                <div class="col col-1 text-center align-middle <c:if test="${task.getExecuteResult().size()==1}">bg-warning</c:if>">
                                        <i class="fa fa-clock"></i><br>
                                        <span>${task.time}</span><br>
                                    <span>min</span>
                                </div>                                
                            </c:when>
                            <c:otherwise>
                                <div class="col col-1 text-center align-middle bg-error">
                                    <i class="fa fa-clock"></i><br>
                                    <span>${task.time}</span><br>
                                    <span>min</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <div class="col col-1 text-center align-middle">
                            <i class="fa fa-clock"></i><br>
                            <span>${task.time}</span><br>
                            <span>min</span>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="col col-9">
                    <p class="bold"><a class="green-link" href="task?id=${task.id}">${task.question}</a></p>
                    <p>
                        <p>${task.answer}</p>
                    </p>
                </div>
                <div class="col col-2 text-right">
                    <a class="green-edit" href="${pageContext.request.contextPath}/task/edit/task?id=${task.id}">
                        <i class="fa fa-cog"></i> Edit
                    </a>
                </div>
            </div>                             
            <hr>
        </c:forEach>        
    </div>
</div> 
<%@include file="/footer.jspf"%>




