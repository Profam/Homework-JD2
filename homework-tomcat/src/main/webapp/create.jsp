<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create expense</title>
</head>
<body>
<h3>New expense</h3>
<form method="post" action="/homework-tomcat/create">
    <label>Number of expense:</label><br>
    <input type="text" name="num" required/><br><br>
    <label>Paydate:</label><br>
    <input type="text" name="paydate" required/><br><br>
    <label>Receiver of expense:</label><br>
    <input type="text" name="receiver" required/><br><br>
    <label>Value:</label><br>
    <input type="text" name="value" required/><br><br>
    <input type="submit" value="add expense" name="add espense"><br>
</form>
</body>
</html>