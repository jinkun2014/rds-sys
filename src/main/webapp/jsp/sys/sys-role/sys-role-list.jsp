<%--
Created by IntelliJ IDEA.
User: JinKun
Date: 2016-12-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 工具栏 -->
<div id="SysRoleToolbar"  style="padding:5px;height:auto">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="javascript:SysRole.list.add()">增加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true" onclick="javascript:SysRole.list.delete()">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true" onclick="javascript:SysRole.list.edit()">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SysRole.list.reload()">刷新</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SysRole.list.authorize()">授权</a>
    <span style="float: right;margin-right: 10px;padding: 1px">
        <span>角色名:</span>
        <input lang="searchSysRole" name="name" style="line-height:19px;border:1px solid #ccc">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" plain="true" onclick="javascript:SysRole.list.clear()">清除</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true" onclick="javascript:SysRole.list.search()">搜索</a>
    </span>
</div>
<!-- 列表 -->
<table id="SysRoleList" data-options="border:false" style="width: 100%;" title="角色"></table>
<!-- 弹窗  --> <!-- inline:true 不然多次打开tab会重复提交表单 -->
<div id="SysRoleEdit" title="角色" style="width:500px;height:300px;top: 100px;padding: 10px;display: none" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true,buttons:[{text:'保存',iconCls:'icon-save',handler:function(){SysRole.input.submitForm()}},{text:'取消',iconCls:'icon-cancel',handler:function(){SysRole.input.close()}}]"></div>
<div id="SysRoleResourceEdit" title="资源"  style="width:1000px;height:500px;top: 100px;padding: 10px;display: none" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true"></div>
<script src="<%=request.getContextPath()%>/jsp/sys/sys-role/sys-role.js"></script>
<script>
    SysRole.list.init('<%=request.getContextPath()%>');
</script>