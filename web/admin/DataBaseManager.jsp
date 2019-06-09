<%-- 
    Document   : DataBaseManager
    Created on : 05.09.2016, 17:59:03
    Author     : ksinn
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/Error.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DBManager</title>
        <link href="${pageContext.request.contextPath}/img/favicon.png" rel="shortcut icon" type="image/x-icon">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
        <!-- Kube CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/kube.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/kube-ext.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css">
    </head>
    <body>

        <%@include file="/header.jsp"%>

        <div class="row centered registration">
            <div class="col col-4">

                <form id="form" class="form" action="DataBaseManager" method="POST">
                    <input type="hidden" name="group" value="${param["group"]}">
                    <div class="form-item sql-teatarea">
                        <label>SQL Query:</label>
                        <textarea required name="query">${query}</textarea>
                    </div>
                    
                    <div class="form-item">
                        <button data-component="message" data-target="#my-message-primary" class="button primary width-100 big">Complete</button>
                    </div>
                </form>
            </div>
        </div>
                    
            <div id="my-message-primary" class="message primary" style="display:block;position: absolute;">
                <span class="close small inverted"></span>
                ${message}
            </div>                    
                    
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/kube.min.js"></script>
        <script>
            $(document).ready(function(){

                $("#form").validate({

                   rules:{ 

                        query:{
                            required: true,
                            minlength: 10
                        }                        
                   }

                });


            }); //end of ready       
            $(document).ready(function(){
                $("#my-message-primary span").click(function(){
                    $("#my-message-primary").removeAttr("style");
                })  
            });                
        </script> 
        <%@include file="/footer.jsp" %>
    </body>
</html>

