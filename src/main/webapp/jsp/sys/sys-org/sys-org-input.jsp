<%--
Created by IntelliJ IDEA.
User: JinKun
Date: 2016-12-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SysOrgForm" method="post">
    <table align="center">
        <input type="hidden" name="id">
        <input type="hidden" name="isLeaf">
        <tr>
            <td></td>
            <td><label>组织名:</label></td>
            <td><input class="easyui-textbox" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>地址:</label></td>
            <td><input class="easyui-textbox" name="address" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>编号:</label></td>
            <td><input class="easyui-textbox" type="text" name="code" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>图标:</label></td>
            <td><input class="easyui-textbox" type="text" name="icon" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>上级部门:</label></td>
            <td><select id="parentOrg" class="easyui-combobox" name="pid" data-options="textField:'text',valueField:'id'" style="width:173px;"></select></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>排序:</label></td>
            <td><input class="easyui-numberspinner" type="text" name="seq" data-options="min:0,max:10000,editable:false,required:false,value:0"/></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"
                   onclick="javascript:SysOrg.input.submitForm()">保存</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                   onclick="javascript:SysOrg.input.close()">返回</a>
            </td>
        </tr>
    </table>
</form>
<script src="<%=request.getContextPath()%>/jsp/sys/sys-org/sys-org.js"></script>
<script>
    SysOrg.input.init('<%=request.getContextPath()%>');
</script>
