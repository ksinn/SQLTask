<%-- 
    Document   : addUser
    Created on : 19.12.2017, 12:04:53
    Author     : ksinn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@include file="/header.jspf" %>
<div class="row">
    <div class="col offset-2 col-8 centered">
        <div class="row">
            <div class="col offset-4 col-4 text-center">
                <h3>User Attempt</h3>
            </div>
        </div>
        <div class="row">
            <form class="col offset-4 col-4 form" action="" method="post">
                <div class="form-item">
                    <label>Work id</label>
                    <input type="text" name="work_id" value="${param.work_id}" required >
                </div>
                <div class="form-item">
                    <label>Task id</label>
                    <input type="text" name="task_id" value="${param.task_id}">
                </div>
                <div class="form-item">
                    <button class="button primary width-100 big">Search</button>
                </div>
            </form>
        </div>
        <c:choose>
            <c:when test="${not empty param.work_id}">
                <c:choose>
                    <c:when test="${not empty param.task_id }">
                        <sql:query var="res" dataSource="jdbc/entetyDB">
                            select t.id, t.question, a.answer, t.answer as rightanswer, flag, work_id 
                            from attempt a join task t on t.id = a.task_id where work_id = ? and task_id = ?
                            <sql:param value="${Integer.parseInt(param.work_id)}"/>
                            <sql:param value="${Integer.parseInt(param.task_id)}"/>
                        </sql:query>
                    </c:when>
                    <c:otherwise>
                        <sql:query var="res" dataSource="jdbc/entetyDB">
                            select t.id, t.question, a.answer, t.answer as rightanswer, flag, work_id
                            from attempt a join task t on t.id = a.task_id where work_id = ?
                            <sql:param value="${Integer.parseInt(param.work_id)}"/>
                        </sql:query>            
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <sql:query var="res" dataSource="jdbc/entetyDB">
                    select t.id, t.question, a.answer, t.answer as rightanswer, flag, work_id
                    from attempt a join task t on t.id = a.task_id
                </sql:query>            
            </c:otherwise>
        </c:choose>


        <c:if test="${res.rowCount eq 0}">
            <h3 class="error text-center">No result</h3>
        </c:if>
        <c:forEach var="a" items="${res.rows}">            
            <div class="row centered space-top">
                <div class="col col-1 <c:if test="${a.flag eq 1}">bg-success</c:if>">
                    <p>flag: ${a.flag}</p>
                    <p>work: ${a.work_id}</p>                    
                </div>                
                <div class="col col-11 text-left">
                    <p class="italic">${a.id}. ${a.question}</p>
                    <p>tuter answer: ${a.rightanswer}</p>
                    <p class="strong">student answer: ${a.answer}</p>

                </div>               
            </div>
            <hr>
        </c:forEach>

    </div>
</div>
<%@include file="/footer.jspf" %>
</body>
</html>