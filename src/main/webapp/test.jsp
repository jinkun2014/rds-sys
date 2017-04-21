<%--
  Created by IntelliJ IDEA.
  User: jinkun
  Date: 2017/4/21
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 101 Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<form class="form-horizontal">
    <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls">
            <input type="text" id="inputEmail" placeholder="Email">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="inputPassword">Password</label>
        <div class="controls">
            <input type="password" id="inputPassword" placeholder="Password">
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <label class="checkbox">
                <input type="checkbox"> Remember me
            </label>
            <button type="submit" class="btn">Sign in</button>
        </div>
    </div>
</form>
</body>
</html>
