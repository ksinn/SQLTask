<%-- 
    Document   : FinishTest
    Created on : 10.08.2016, 14:39:43
    Author     : ksinn
--%>
<%!String pageTitle = "temple";%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/header.jspf"%>
<script src="${pageContext.request.contextPath}/resources/js/Chart.min.js"></script>   

<div class="row centered text-center">
    <div class="col col-8 space-top">
        <!--<canvas id="chart"style="zoom: 1"></canvas>-->
        <img src="${pageContext.request.contextPath}/resources/img/salut.png" alt="salut">
        <h4>You complete exam</h4>    
        <h1 class="error">${work.countAccepted}/${work.count} task was accepted</h1>
        <p class="space-top"><a href="/elearning" class="button warning round">&larr; Go back</a></p>           
    </div>
</div>
        

<script>
    var ctx = document.getElementById("chart").getContext('2d');
    var myPieChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            datasets: [{
                    data: [${work.countAccepted}, ${work.countWrong}],
                    backgroundColor: [
                        'rgba(16, 209, 232, 0.8)',
                        'rgba(255, 201, 82, 0.8)'
                    ],
                    borderColor: [
                        'rgba(163, 209, 232, 1)',
                        'rgba(255, 201, 82, 1)'
                    ]
                }],
            labels: [
                'Accepted',
                'Wrong'
            ]
        },
        options: {}
    });
</script>

<%@include file="/footer.jspf"%>
