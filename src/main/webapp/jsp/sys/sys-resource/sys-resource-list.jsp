<%--
Created by IntelliJ IDEA.
User: JinKun
Date: 2016-12-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 工具栏 -->
<div id="SysResourceToolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="javascript:SysResource.list.add()">增加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true" onclick="javascript:SysResource.list.delete()">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true" onclick="javascript:SysResource.list.edit()">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SysResource.list.reload()">刷新</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SysResource.list.collapseAll()">折叠</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SysResource.list.expandAll()">展开</a>
</div>
<!-- 列表 -->
<table id="SysResourceList" data-options="border:false" style="width: 100%;" title="资源"></table>
<!-- 弹窗  --> <!-- inline:true 不然多次打开tab会重复提交表单 -->
<div id="SysResourceEdit" title="资源" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true" title="资源" style="width:1000px;height:500px;top: 100px;padding: 10px;display: none"></div>
<script src="<%=request.getContextPath()%>/jsp/sys/sys-resource/sys-resource.js"></script>
<script>
    SysResource.list.init('<%=request.getContextPath()%>');
</script>