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
    <div class="col offset-4 col-4 text-center">
        <h3>Works</h3>
    </div>
</div>
<div class="row">
    <form class="col offset-4 col-4 form" action="" method="post">
        <div class="form-item">
            <label>List id</label>
            <input type="number" name="list_id" value="${param.list_id}">
        </div>
        <div class="form-item">
            <label>User id</label>
            <input type="number" name="user_id" value="${param.user_id}">
        </div>
        <div class="form-item">
            <button class="button primary width-100 big">Search</button>
        </div>
    </form>
</div>
<c:choose>
    <c:when test="${not empty param.list_id}">
        <c:choose>
            <c:when test="${not empty param.user_id}">
                <sql:query var="res" dataSource="jdbc/entetyDB">
                    select works.id, works.work_key, works.list_id, works.user_id, list_name, works.addDate
                    from works join task_list on works.list_id = task_list.id
                    where list_id = ? and works.user_id = ?
                    order by works.addDate
                    <sql:param value="${Integer.parseInt(param.list_id)}"/>
                    <sql:param value="${Integer.parseInt(param.user_id)}"/>
                </sql:query>
            </c:when>
            <c:otherwise>
                <sql:query var="res" dataSource="jdbc/entetyDB">
                    select works.id, works.work_key, works.list_id, works.user_id, list_name, works.addDate
                    from works join task_list on works.list_id = task_list.id
                    where list_id = ?
                    order by works.addDate                    
                    <sql:param value="${Integer.parseInt(param.list_id)}"/>
                </sql:query>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${not empty param.user_id}">
                <sql:query var="res" dataSource="jdbc/entetyDB">
                    select works.id, works.work_key, works.list_id, works.user_id, list_name, works.addDate
                    from works join task_list on works.list_id = task_list.id
                    where works.user_id = ?
                    order by works.addDate
                    <sql:param value="${Integer.parseInt(param.user_id)}"/>
                </sql:query>
            </c:when>
            <c:otherwise>
                <sql:query var="res" dataSource="jdbc/entetyDB">
                    select works.id, works.work_key, works.list_id, works.user_id, list_name, works.addDate
                    from works join task_list on works.list_id = task_list.id
                    order by works.addDate                    
                </sql:query>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<div class="row centered space-top">
    <div class="col text-center">
        <c:if test="${res.rowCount eq 0}">
            <h3 class="error">No result</h3>
        </c:if>
        <table>
            <tr>
                <td>date</td>
                <td>id</td>
                <td>user</td>
                <td>list</td>
                <td>key</td>
            </tr>
            <c:forEach var="a" items="${res.rows}">
                <tr>
                    <td>${a.addDate}</td>
                    <td>${a.id}</td>
                    <td>${a.user_id}</td>
                    <td>${a.list_id} ${a.list_name} </td>
                    <td>${a.work_key}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<%@include file="/footer.jspf" %>
</body>
</html>


