<%-- 
    Document   : CreateTask
    Created on : 05.09.2016, 11:17:11
    Author     : ksinn
--%>
<%!String pageTitle = "List settings";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>

<div class="row">
    <div class="col offset-2 col-8">
        <nav class="breadcrumbs">
            <ul>
                <li><a class="green-link" href="${pageContext.request.contextPath}/cabinet">Home</a></li>
                    <c:choose>
                        <c:when test="${not empty param.id}">
                        <li><a class="green-link" href="${pageContext.request.contextPath}/task/list?id=${list.id}">${list.name}</a></li>
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
                        <h3>Edit list ${list.name}</h3>
                    </c:when>
                    <c:otherwise>
                        <h3>Add list</h3>
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
            <div class="col">
                <form id="form" class="form" action="" method="POST">
                    <input type="hidden" name="id" value="${list.id}">

                    <div class="form-item">
                        <label>Name:</label>
                        <input type="text" required name="name" value="${list.name}">
                    </div>

                    <div class="form-item">
                        <label>Schema:</label>
                        <input type="text" required name="schema" value="${list.schema}">
                    </div>

                    <div class="form-item">
                        <label>
                            <input type="checkbox" name="public" <c:if test="${list.isPublic()}"> checked="true" </c:if>> Can other users see this list?
                        </label>
                    </div>
                    <div class="form-item text-centered">
                        <button class="button primary w20 big">Complete</button>
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
                name: {
                    minlength: 1,
                    maxlength: 32
                },
                schema: {
                    minlength: 1
                }

            }

        });


    });
</script> 


<%@include file="/footer.jspf"%>



