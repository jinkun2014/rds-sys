<%--
  Created by IntelliJ IDEA.
  User: jinkun
  Date: 2016/7/24
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="resources/common/tag.jsp" %>
<html>
<head>
    <title>登录</title>
    <%@include file="resources/common/header.jsp" %>
    <script>
        function login() {
            var loginName = $("#loginName").textbox("getValue");
            var password = $("#password").textbox("getValue");
            $.ajax({
                type: "POST",
                url: "/login",
                data: {loginName: loginName, password: password},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 200) {
                        window.location.href = "/";
                    }
                }
            });
        }
    </script>
</head>
<body>
<div style="width:600px;margin:150px auto;">
    <div class="easyui-panel" title="欢迎登录"
         style="width:500px;height:300px;padding:10px;text-align:center; overflow:hidden;"
         data-options="closable:false,collapsible:false,minimizable:false,maximizable:false">
        <form id="loginForm" method="post">
            <table style="margin:50px auto;">
                <input type="hidden" name="id">
                <tr>
                    <td></td>
                    <td><label>登录名:</label></td>
                    <td><input class="easyui-textbox" type="text" id="loginName" name="loginName"
                               data-options="required:true,value:'admin'"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label>密&nbsp;码:</label></td>
                    <td><input class="easyui-passwordbox" type="text" id="password" name="password"
                               data-options="required:true,value:'123456'"/></td>
                    <td></td>
                </tr>
                <tr style="height: 20px;"></tr>
                <tr>
                    <td colspan="4" align="center">
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"
                           onclick="javascript:login()">登录</a>
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                           onclick="javascript:void(0)">返回</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
