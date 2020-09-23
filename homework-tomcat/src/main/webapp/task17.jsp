<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>expenses</title>
</head>
<body>
<h2>Expenses List</h2> <br/>
<br>
        <td>
            Expenses table:
        </td>
<c:forEach var="expense" items="${requestScope.table}">
    <table cols="4">
        <tr><td><c:out value="${expense.num}"/></td>
        <td><c:out value="${expense.paydate}"/></td>
        <td><c:out value="${expense.receiver}"/></td>
        <td><c:out value="${expense.value}"/></td></tr>
    </table>
</c:forEach>
</body>
</html>