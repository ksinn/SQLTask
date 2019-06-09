<%-- 
    Document   : temple
    Created on : 04.12.2017, 14:39:32
    Author     : ksinn
--%>
<%!String pageTitle = "temple";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>
<div class="row">
    <div class="col offset-2 col-8">
        <nav class="breadcrumbs">
            <ul>
                <li><span>Home</span></li>
            </ul>
        </nav>
        <nav class="tabs space-top" data-component="tabs">
            <ul>
                <li class="active"><a href="#tab1">Lists</a></li>
                <li><a href="#tab2">...</a></li>
            </ul>
        </nav>
        <div id="tab1">
            <div class="row">
                <div class="col offset-10 col-2 text-right">
                    <p>
                        <a class="green-edit" href="${pageContext.request.contextPath}/task/add/list"><i class="fa fa-plus green-edit" aria-hidden="true"> Add list</i></a>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col col-10">
                    <c:forEach var="list" items="${lists}">
                        <p>
                            <a class="green-edit" href="${pageContext.request.contextPath}/task/list?id=${list.id}">${list.name}</a> <span class="italic small muted">(${list.tasks.size()} tasks)</span>
                        </p> 
                    </c:forEach>
                </div>
            </div>
        </div>
        <div id="tab2">...</div>

    </div>
</div> 

<%@include file="/footer.jspf"%>

