<%-- 
    Document   : temple
    Created on : 04.12.2017, 14:39:32
    Author     : ksinn
--%>
<%@page import="TasKer.Core.DBEntety"%>
<%@page import="org.apache.log4j.Logger"%>
<%!String pageTitle = "Error";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>
<%

    Logger log = Logger.getLogger(DBEntety.class.getName());
    log.error("error");

%>

<div class="row">
    <div class="col col-3 offset-3 test-btn text-right">
        <h3 class="error">WHOOPS!</h3>
        <hr>
        <p class="error">${pageContext.exception}</p>
    </div>
    <div class="col col-3 text-left">
        <img src="${pageContext.request.contextPath}/resources/img/error.png" alt="error">
    </div>
</div>
<div class="row centered">
    <div class="col text-center">
        <p>Please, come back and try again.</p>
        <p class="space-top"><a href="/elearning/user/cabinet" class="button warning round">&larr; Go back</a></p>           
    </div>
</div>

<%@include file="/footer.jspf"%>

