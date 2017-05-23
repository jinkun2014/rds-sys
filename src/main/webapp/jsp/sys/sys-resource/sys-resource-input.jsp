<%--
Created by IntelliJ IDEA.
User: JinKun
Date: 2016-12-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SysResourceForm" method="post">
    <table class="com_table" align="center">
        <input type="hidden" name="id">
        <tr>
            <td></td>
            <td><label>资源名称:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>资源路径:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="url" data-options="required:false,value:'/resources/images/32x32/1.png'"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>打开方式:</label></td>
            <td>
                <select class="easyui-combobox" name="openMode" data-options="panelHeight:'auto',value:'0'">
                    <option value="0">ajax</option>
                    <option value="1">iframe</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>资源介绍:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="description" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>资源图标:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="icon" data-options="required:false"/></td>
            <td>
                <a href="#" class="easyui-linkbutton" onclick="javascript:SysResource.input.chooseIcon()">选择</a>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><label>上级资源</label></td>
            <td>
                <select id="parentResource" class="easyui-combobox com_input" name="pid" data-options="textField:'text',valueField:'id'"></select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>排序:</label></td>
            <td><input class="easyui-numberspinner" type="text" name="seq" data-options="min:0,max:10000,editable:true,required:false,value:0"/></td>
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
            <td></td>
            <td><label>资源类别:</label></td>
            <td>
                <select class="easyui-combobox" name="resourceType" data-options="panelHeight:'auto',value:'0'" style="width:173px;">
                    <option value="0">菜单</option>
                    <option value="1">按钮</option>
                </select>
            </td>
            <td></td>
        </tr>
    </table>
</form>
<script src="<%=request.getContextPath()%>/jsp/sys/sys-resource/sys-resource.js"></script>
<script>
    SysResource.input.init('<%=request.getContextPath()%>');
</script>
