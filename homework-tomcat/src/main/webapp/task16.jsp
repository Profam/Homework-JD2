<%@ page import="java.util.List" %>
<%@ page import="expense.Expense"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>expenses</title>
</head>
<body>
<h2>Expenses List</h2> <br/>
<p><a href="create.jsp" >Create new expense</a></p>
<p><a href="delete.jsp">delete expense</a></p>
<br>
        <td>
            Expenses table:
        </td>
        <%
          List<Expense>table = (List) request.getAttribute("table");
        %>
<table cols="4">
    <% for (Expense expense : table) {%>
     <tr><td><%= expense.getNum()%></td>
    <td><%= expense.getPaydate()%></td>
    <td><%= expense.getReceiver()%></td>
    <td><%= expense.getValue()%></td></tr>
    <% } %>
</table>
</body>
</html>