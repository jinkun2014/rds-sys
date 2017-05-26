<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="resources/common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>基础权限管理系统</title>
    <%@include file="resources/common/header.jsp" %>

    <style>
        html, body {
            margin: 0;
            width: 100%;
            height: 100%;
            overflow-y: hidden
        }

        .mainmenu {
            width: 65px;
            height: 84px;
            float: left;
            text-align: center;
            color: #ffffff;
            cursor: pointer;
            font-weight: bold;
        }

        .mainmenuover {
            width: 65px;
            height: 84px;
            background: #06449B;
            float: left;
            text-align: center;
            color: #ffffff;
            cursor: pointer;
            font-weight: bold;
        }

        .secondmenu {
            background: #ffffff;
            border: 1px solid #ffffff;
            cursor: pointer;
            width: 50%
        }

        .secondmenuover {
            background: #E4EFFA;
            border: 1px solid #C6D8F0;
            cursor: pointer;
            width: 50%
        }
    </style>
    <script>
        var globalWindow;
        var topMenuCache;
        var secondMenuCache = {};
        var thirdMenuCache = {};

        function mainmenu_over(obj) {
            obj.className = "mainmenuover";
        }
        function mainmenu_out(obj) {
            obj.className = "mainmenu";
        }
        function secondmenu_over(obj) {
            obj.className = "secondmenuover";
        }
        function secondmenu_out(obj) {
            obj.className = "secondmenu";
        }

        function onClickThirdMenu(id,text,url) {
            var tabs = $("#tabs");
            var tab = tabs.tabs("getTab", text);
            if (tab) {
                tabs.tabs("select", text);
            } else {

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

        //加载3级菜单
        function loadThirdMenu(id, data) {
            //清空菜单
            $('#thirdMenu-' + id).empty();

            if (data.length % 2 == 0) {
                for (var i = 0; i < data.length; i += 2) {
                    var tr =
                            '<tr>' +
                            '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i].id + ',\''+data[i].text+'\',\''+data[i].attributes.url+'\')">' +
                            '<div style="width:100%;text-align:center;padding-top:6px;">' +
                            '<img src="'+data[i].iconCls+'" width="32" height="32">' +
                            '</div>' +
                            '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i].text + '</div>' +
                            '</td>' +
                            '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i+1].id + ',\''+data[i+1].text+'\',\''+data[i+1].attributes.url+'\')">' +
                            '<div style="width:100%;text-align:center;padding-top:6px;">' +
                            '<img src="'+data[i+1].iconCls+'" width="32" height="32">' +
                            '</div>' +
                            '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i + 1].text + '</div>' +
                            '</td>' +
                            '<tr/>';

                    $('#thirdMenu-' + id).append(tr);
                }
            } else {
                for (var i = 0; i < data.length - 1; i += 2) {
                    var tr =
                            '<tr>' +
                            '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i].id + ',\''+data[i].text+'\',\''+data[i].attributes.url+'\')">' +
                            '<div style="width:100%;text-align:center;padding-top:6px;">' +
                            '<img src="'+data[i].iconCls+'" width="32" height="32">' +
                            '</div>' +
                            '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i].text + '</div>' +
                            '</td>' +
                            '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i+1].id + ',\''+data[i+1].text+'\',\''+data[i+1].attributes.url+'\')">' +
                            '<div style="width:100%;text-align:center;padding-top:6px;">' +
                            '<img src="'+data[i+1].iconCls+'" width="32" height="32">' +
                            '</div>' +
                            '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i + 1].text + '</div>' +
                            '</td>' +
                            '<tr/>';

                    $('#thirdMenu-' + id).append(tr);
                }
                var tr =
                        '<tr>' +
                        '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i].id + ',\''+data[i].text+'\',\''+data[i].attributes.url+'\')">' +
                        '<div style="width:100%;text-align:center;padding-top:6px;">' +
                        '<img src="'+data[i].iconCls+'" width="32" height="32">' +
                        '</div>' +
                        '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[data.length - 1].text + '</div>' +
                        '</td>' +
                        '<td class="secondmenu">' +
                        '</td>' +
                        '<tr/>';

                $('#thirdMenu-' + id).append(tr);
            }
        }

        function onClickSecondMenu(id) {
            //从缓存加载3级菜单
            if (thirdMenuCache['' + id] != null) {
                loadThirdMenu(id, thirdMenuCache['' + id]);
                return;
            }


            //从网络加载3级菜单
            $.ajax({
                type: "GET",
                url: "/sys/users/menu/" + id + "/tree",
                success: function (data) {
                    //加入缓存
                    thirdMenuCache['' + id] = data;

                    loadThirdMenu(id, data);
                }
            });
        }

        //加载二级菜单
        var secondMenuIds = new Array();
        function loadSecondMenu(data) {
            //清空选择事件，防止在移除时触发选择时间
            $('#secondMenu').accordion({
                onSelect: function (title, index) {

                }
            });

            //清空菜单
            for (var i = secondMenuIds.length - 1; i >= 0; i--) {
                $('#secondMenu').accordion('remove', i);
            }

            //初始化选择事件
            $('#secondMenu').accordion({
                onSelect: function (title, index) {
                    onClickSecondMenu(secondMenuIds[index]);
                }
            });

            secondMenuIds = new Array();
            for (var i = 0; i < data.length; i++) {
                //缓存2级菜单的id
                secondMenuIds[i] = data[i].id;

                //新增2级菜单
                $('#secondMenu').accordion('add', {
                    title: data[i].text,
                    content: '<table id="thirdMenu-' + data[i].id + '" width="100%" cellpadding="2" cellspacing="5"></table>',
                    selected: false
                });
            }

            //默认选择第1个
            //$('#secondMenu').accordion('select', 0);
        }

        function onClickTopMenu(id) {
            //从缓存加载2级菜单
            if (secondMenuCache['' + id] != null) {
                loadSecondMenu(secondMenuCache['' + id]);
                return;
            }

            //从网络加载2级菜单
            $.ajax({
                type: "GET",
                url: "/sys/users/menu/" + id + "/tree",
                success: function (data) {
                    //加入缓存
                    secondMenuCache['' + id] = data;

                    loadSecondMenu(data);
                }
            });
        }

        function logout(){
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

        $(function () {
            globalWindow = $("#globalWindow");

            //加载当前用户的1级菜单
            $.ajax({
                type: "GET",
                url: "/sys/users/menu/top",
                success: function (data) {

                    if (data.code == 200) {
                        //清空菜单
                        //$('#topMenu').empty();

                        for (var i = 0; i < data.data.length; i++) {
                            var div =
                                    '<div class="mainmenu" onclick="onClickTopMenu(' + data.data[i].id + ')" onmouseover="mainmenu_over(this)" onmouseout="mainmenu_out(this)">' +
                                    '<div style="margin:10px 5px 10px 5px">' +
                                    '<img src="'+data.data[i].iconCls+'" width="35" height="35"/>' +
                                    '</div>' +
                                    '<div style="font-size:13px">' + data.data[i].text + '</div>' +
                                    '</div>';
                            $("#topMenu").append(div);
                        }

                        //退出系统
                        var div =
                                '<div class="mainmenu" onclick="logout()" onmouseover="mainmenu_over(this)" onmouseout="mainmenu_out(this)">' +
                                '<div style="margin:10px 5px 10px 5px">' +
                                '<img src="/resources/images/32x32/sign-out.png" width="35" height="35"/>' +
                                '</div>' +
                                '<div style="font-size:13px">退出登录</div>' +
                                '</div>';
                        $("#topMenu").append(div);


                        //默认选择第一个
                        onClickTopMenu(data.data[0].id);
                    }
                }
            });

            //默认收起右侧面板
            $("div.easyui-layout").layout('collapse','east');
        });
    </script>
</head>

<body>
<!-- 全局弹出框 -->
<div id="globalWindow" style="display: none"></div>
<!-- 主布局 -->
<div class="easyui-layout" style="width:100%;height:100%;margin:0">
    <!-- 顶部导航栏 -->
    <div data-options="region:'north',split:false,border:false"
         style="height:85px;overflow:hidden;background:#0B7CBE url(./resources/images/logotitle.jpg) no-repeat">
        <table align="right" cellpadding="0" cellspacing="0">
            <tr>
                <td id="topMenu">
                    <!--
                    <div class="mainmenu" onclick="" onmouseover="mainmenu_over(this)" onmouseout="mainmenu_out(this)">
                        <div style="margin:10px 5px 10px 5px">
                            <img src="./resources/images/1.png" width="35" height="35"/>
                        </div>
                        <div style="font-size:13px">信息发布</div>
                    </div>
                    <div class="mainmenu" onclick="" onmouseover="mainmenu_over(this)" onmouseout="mainmenu_out(this)">
                        <div style="margin:10px 5px 10px 5px">
                            <img src="./resources/images/1.png" width="35" height="35"/>
                        </div>
                        <div style="font-size:13px">数据管理</div>
                    </div>
                    <div class="mainmenu" onclick="" onmouseover="mainmenu_over(this)" onmouseout="mainmenu_out(this)">
                        <div style="margin:10px 5px 10px 5px">
                            <img src="./resources/images/1.png" width="35" height="35"/>
                        </div>
                        <div style="font-size:13px">系统设置</div>
                    </div>
                     -->
                </td>
            </tr>
        </table>
    </div>
    <div data-options="region:'south',split:false,border:true" style="height:50px;"></div>
    <div data-options="region:'east',split:false,border:true" title="East" style="width:120px;"></div>

    <!-- 左侧菜单 -->
    <div data-options="region:'west',split:false,border:true" title="系统菜单" style="width:205px;overflow-y: hidden">
        <div id="secondMenu" class="easyui-accordion" data-options="border:false" style="width:auto;height:100%;">
            <!--
             <div title="二级菜单" data-options="iconCls:'icon-reload'" style="overflow:hidden;">
                <table id="thirdMenu" width="100%" cellpadding="2" cellspacing="5">
                    <tr>
                        <td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)">
                            <div style="width:100%;text-align:center;padding-top:6px;">
                                <img src="./resources/images/1-1.png" width="32" height="32">
                            </div>
                            <div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">三级菜单1</div>
                        </td>
                        <td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)">
                            <div style="width:100%;text-align:center;padding-top:6px;">
                                <img src="./resources/images/1-1.png" width="32" height="32">
                            </div>
                            <div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">三级菜单2</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)">
                            <div style="width:100%;text-align:center;padding-top:6px;">
                                <img src="./resources/images/1-1.png" width="32" height="32">
                            </div>
                            <div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">三级菜单3</div>
                        </td>
                    </tr>
                </table>
            </div>
             -->
        </div>
    </div>

    <!-- 主要内容 -->
    <div data-options="region:'center',border:false">
        <div id="tabs" class="easyui-tabs" data-options="tools:'#tab-tools',border:false"
             style="width:auto;height:100%">
        </div>
        <div id="tab-tools">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"
               onclick="addPanel()"></a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"
               onclick="removePanel()"></a>
        </div>
        <script type="text/javascript">
            var index = 0;
            function addPanel() {
                index++;
                $('#tabs').tabs('add', {
                    title: 'Tab' + index,
                    content: '<div style="padding:10px">Content' + index + '</div>',
                    closable: true
                });
            }
            function removePanel() {
                var tab = $('#tabs').tabs('getSelected');
                if (tab) {
                    var index = $('#tabs').tabs('getTabIndex', tab);
                    $('#tabs').tabs('close', index);
                }
            }
        </script>
    </div>
</div>
</body>
</html>
