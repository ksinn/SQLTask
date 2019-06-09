<%--
    Document : addUser
    Created on : 19.12.2017, 12:04:53
    Author : vitaliy Pak
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@include file="/header.jspf" %>

<div class="row">
    <div class="col offset-2 col-8 centered">
        <div class="row">
            <div class="col offset-4 col-4 text-center">
                <h3>Show result of student on Database</h3>
            </div>
        </div>
        <div class="row">
            <form class="col offset-4 col-4 form" action="" method="post">
                <div class="form-item">
                    <label>List_id</label>
                    <input type="text" name="list_id" value="${param.list_id}" required >
                </div>
                <div class="form-item">
                    <label>Student_id</label>
                    <input type="text" name="student_id" value="${param.student_id}">
                </div>
                <div class="form-item">
                    <label>Work_id</label>
                    <input type="text" name="work_id" value="${param.work_id}">
                </div>
                <div class="form-item">
                    <button class="button primary width-100 big">Search</button>
                </div>
            </form>
        </div>
	<br/>
	<hr/>
        <c:choose>
            <c:when test="${not empty param.list_id}">
                <c:choose>
                    <c:when test="${not empty param.student_id}">
                        <c:choose>
                            <c:when test="${not empty param.work_id}">
                                <sql:query var="res" dataSource="jdbc/entetyDB">
                                    select task_id, t.question, t.answer, result, work_id from task_result tr inner join task t on t.id = tr.task_id where work_id in (select id from works where list_id = ? and user_id = ?) and work_id = ?
                                    <sql:param value="${Integer.parseInt(param.list_id)}"/>
                                    <sql:param value="${Integer.parseInt(param.student_id)}"/>
                                    <sql:param value="${Integer.parseInt(param.work_id)}"/>
                                </sql:query>
                            </c:when>
                            <c:otherwise>
                                <sql:query var="res" dataSource="jdbc/entetyDB">
                                    select task_id, t.question, t.answer, result, work_id from task_result tr inner join task t on t.id = tr.task_id where work_id in (select id from works where list_id = ? and user_id = ?) order by work_id
                                    <sql:param value="${Integer.parseInt(param.list_id)}"/>
                                    <sql:param value="${Integer.parseInt(param.student_id)}"/>
                                </sql:query>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <sql:query var="res" dataSource="jdbc/entetyDB">
                            select task_id, t.question, t.answer, result, work_id from task_result tr inner join task t on t.id = tr.task_id where work_id in (select id from works where list_id = ?) order by work_id
                            <sql:param value="${Integer.parseInt(param.list_id)}"/>
                        </sql:query>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
        <c:if test="${res.rowCount eq 0}">
            <h3 class="error text-center">No result</h3>
        </c:if>
        <c:forEach var="a" items="${res.rows}">
            <div class="row centered space-top <c:if test="${a.result eq 0}">bg-error</c:if><c:if test="${a.result eq 1}">bg-success</c:if>">
                <div class="col col-1">
                    <p>task:${a.task_id} work:${a.work_id}</p>
                </div>
                <div class="col col-11 text-left">
                    <p class="italic">${a.question}</p>
                </div>
            </div>
            <c:if test="${a.result eq 0}">
                <div style="background-color:orange">
                    <p style="color:green;font-weight:bold;text-align:center">${a.answer}</p>
                    <sql:query var="questions" dataSource="jdbc/entetyDB">
                        select t.id, t.question, a.answer, t.answer as rightanswer, flag, work_id
                        from attempt a join task t on t.id = a.task_id where work_id = ? and task_id = ? order by a.id
                        <sql:param value="${Integer.parseInt(a.work_id)}"/>
                        <sql:param value="${Integer.parseInt(a.task_id)}"/>
                    </sql:query>
                    <c:forEach var="quest" items="${questions.rows}">
                        <p class="strong" style="padding:0 50px">${quest.answer}</p>
                        <hr/>
                    </c:forEach>
                </div>
            </c:if>
            <hr>
        </c:forEach>
    </div>
</div>
<%@include file="/footer.jspf" %>
</body>
</html>
