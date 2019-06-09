<%-- 
    Document   : index
    Created on : 07.12.2016, 13:36:01
    Author     : ksinn
--%>

<%@page import="TasKer.Tasks.Impl.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    Service serv = new Service();
    serv.getById(1);
if(request.getSession().getAttribute("user_id")==null){
    response.setHeader("Location", serv.getURL());
    response.setHeader("Cache-Control", "no-store");
    response.setStatus(301);
} else {
    response.sendRedirect("cabinet");
}
%>
