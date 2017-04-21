<%--
  Created by IntelliJ IDEA.
  User: jinkun
  Date: 2016/7/24
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="resources/common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <%@include file="resources/common/header.jsp" %>

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/index.css"/>
    <script>
        var globalWindow;
        $(function () {
            //初始化全局的Window
            globalWindow = $("#globalWindow");

            //加载当前用户的1级菜单
            $.ajax({
                type: "GET",
                url: "/sys/users/menu/top",
                success: function (data) {
                    if (data.code == 200) {
                        //动态添加1级菜单按钮
                        for (var i = 0; i < data.data.length; i++) {
                            $("#topMenu").append("<a href=\"#\" style='margin-left: 15px;margin-bottom: 3px;' onclick='loadChildMenu(" + data.data[i].id + ")' class=\"easyui-linkbutton-menu\" data-options=\"iconCls:'" + data.data[i].iconCls + "',plain:true\">" + data.data[i].text + "</a>");//<div class="datagrid-btn-separator" style="height:24px;line-height: 36px;margin: 6px 0 6px 0;"></div>
                            if (i == 0) {
                                loadChildMenu(data.data[i].id);
                            }
                        }
                        //设置1级菜单按钮样式
                        $(".easyui-linkbutton-menu").each(function () {
                            $(this).linkbutton({plain: true});
                        });
                    }
                }
            });

            //退出登录
            $("#logout").linkbutton({
                onClick: function () {
                    $.ajax({
                        type: "GET",
                        url: "/logout",
                        success: function (data) {
                            if (data.code == 200) {
                                window.location.href = "/login.jsp";
                            }
                        }
                    });
                }
            });
        });

        function loadChildMenu(id) {
            $('#menu').tree({
                url: "/sys/users/menu/" + id + "/tree",
                method: 'get',
                lines:true,
                onClick: function (node) {
                    if ($('#menu').tree("isLeaf", node.target)) {
                        var tabs = $("#tabs");
                        var tab = tabs.tabs("getTab", node.text);
                        if (tab) {
                            tabs.tabs("select", node.text);
                        } else {
                            var text = node.text;
                            var url = node.attributes.url;

                            //加载默认显示页面
                            if (!url) {
                                url = "/non.jsp";
                            }

                            tabs.tabs('add', {
                                title: text,
                                href: url,
                                closable: true,
                                bodyCls: "content",
                                border: false
                            });
                        }
                    }
                }
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',title:''" style="height: 40px;">
    <div class="headerTop">
        <!-- 一级菜单 -->
        <div id="topMenu" style="float: left; margin-bottom: 5px;"></div>

        <!-- 用户信息 -->
        <span style="float: right;margin-right: 10px;text-align: center">
            <b>当前用户:&nbsp;<shiro:principal property="name"></shiro:principal></b>&nbsp;
            <a href="#" id="logout" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
               style="margin-bottom: 3px;">退出系统</a>
        </span>
    </div>
</div>
<div data-options="region:'west',title:'菜单',split:false" style="width:180px;">
    <ul id="menu" style="margin-top: 10px;margin-left: 5px;"></ul>
</div>
<div data-options="region:'center',title:''">
    <div id="tabs" class="easyui-tabs" style="width: 100%;">
        <div title="首页" style="padding:1px;">
            欢迎来到我的世界！${basePath}-${ctx}-<%=request.getContextPath()%>
        </div>
    </div>
</div>
<!-- 全局弹出框 -->
<div id="globalWindow" style="display: none"></div>
</body>
</html>
