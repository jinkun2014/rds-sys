<%--
Created by IntelliJ IDEA.
User: JinKun
Date: 2016-12-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SysRoleForm" method="post">
    <table align="center">
        <input type="hidden" name="id">
        <tr>
            <td></td>
            <td><label>角色名:</label></td>
            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>排序号:</label></td>
            <td><input class="easyui-numberspinner" type="text" name="seq" data-options="min:0,max:10000,editable:false,required:false,value:0"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>简介:</label></td>
            <td><input class="easyui-textbox" type="text" name="description" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>状态:</label></td>
            <td>
                <select class="easyui-combobox" name="status" data-options="panelHeight:'auto',value:'0'" style="width:173px;">
                    <option value="0">启用</option>
                    <option value="1">停用</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"
                   onclick="javascript:SysRole.input.submitForm()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                   onclick="javascript:SysRole.input.close()">返回</a>
            </td>
        </tr>
    </table>
</form>
<script src="<%=request.getContextPath()%>/jsp/sys/sys-role/sys-role.js"></script>
<script>
    SysRole.input.init('<%=request.getContextPath()%>');
</script>
