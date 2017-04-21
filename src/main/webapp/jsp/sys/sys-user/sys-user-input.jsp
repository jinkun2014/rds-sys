<%--
Created by IntelliJ IDEA.
User: JinKun
Date: 2016-12-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SysUserForm" method="post">
    <table class="com_table" align="center">
        <input type="hidden" name="id">
        <tr>
            <td></td>
            <td><label>登录名:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="loginName" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>用户名:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>密码:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="password" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>性别:</label></td>
            <td>
                <select class="easyui-combobox" name="sex" data-options="panelHeight:'auto',value:'0'" style="width:173px;">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>年龄:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="age" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>手机号:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="phone" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>用户类别:</label></td>
            <td>
                <select class="easyui-combobox" name="userType" data-options="panelHeight:'auto',value:'0'" style="width:173px;">
                    <option value="0">用户</option>
                    <option value="1">管理员</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>用户状态:</label></td>
            <td>
                <select class="easyui-combobox" name="status" data-options="panelHeight:'auto',value:'0'" style="width:173px;">
                    <option value="0">启用</option>
                    <option value="1">停用</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>所属部门:</label></td>
            <td><select id="SysUserOrg" name="orgId" class="com_input" data-options="textField:'text',valueField:'id',multiple:true,onlyLeafCheck:true,required:true"></select></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>所有角色:</label></td>
            <td><select id="SysUserRole" name="roleId" class="com_input" data-options="textField:'name',valueField:'id',multiple:true,onlyLeafCheck:true,required:true" style="width:173px;"></select></td>
            <td></td>
        </tr>
    </table>
</form>
<script src="<%=request.getContextPath()%>/jsp/sys/sys-user/sys-user.js"></script>
<script>
    SysUser.input.init('<%=request.getContextPath()%>');
</script>
