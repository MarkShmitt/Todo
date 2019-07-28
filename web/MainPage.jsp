<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.07.2019
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo App</title>
    <style type="text/css">
        <%@include file="WEB-INF/css/style.css"%>
    </style>
</head>
<body>

<div class="header">It will be verification and registration users, and synchronization with calendar.<br>
    To be continued...
</div>

<div class="container">
    <form action="insert" method="post">
        <label for="new-task">Add Item</label>
        <input id="new-task" type="text" name="title" required>
        <button type="submit">Add</button>
    </form>

    <h3>Todo</h3>
    <ul id="incomplete-tasks">
        <c:forEach var="task" items="${tasks}">
            <c:if test="${task.completeFlag == false}">
                <li class="editMode">
                    <form action="update" method="post">
                        <input type="hidden" name="id" value="<c:out value='${task.id}' />" />
                        <input type="checkbox" name="completeFlag" value="true"/>
                        <input type="text" name="title" value="<c:out value="${task.title}" />" required/>
                        <label><c:out value="${task.title}" /></label>
                        <button class="edit" type="submit">Edit</button>
                    </form>
                    <form action="delete" method="post">
                        <input type="hidden" name="id" value="<c:out value='${task.id}' />" />
                        <button class="delete" type="submit">Delete</button>
                    </form>
                </li>
            </c:if>
        </c:forEach>
    </ul>

    <h3>Completed</h3>
    <ul id="completed-tasks">
        <c:forEach var="task" items="${tasks}">
            <c:if test="${task.completeFlag == true}">
                <li>
                    <form action="update" method="post">
                        <input type="hidden" name="id" value="<c:out value='${task.id}' />" />
                        <input type="checkbox" name="completeFlag" value="true" checked/>
                        <input type="text" name="title" value="<c:out value="${task.title}" />" required/>
                        <label><c:out value="${task.title}" /></label>
                        <button class="edit" type="submit">Edit</button>
                    </form>
                    <form action="delete" method="post">
                        <input type="hidden" name="id" value="<c:out value='${task.id}' />" />
                        <button class="delete" type="submit">Delete</button>
                    </form>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>
</body>
</html>