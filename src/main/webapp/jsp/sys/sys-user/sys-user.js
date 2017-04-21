var ctx = "";//项目部署的工程名
var SysUserList;
var SysUserEdit;
var SysUserForm;

//其它组件
var SysUserOrgTree;
var SysUserOrg;
var SysUserRole;

var SysUser = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/users/ui/input";
        },
        listUI: function () {
            return ctx + "/sys/users/ui/list";
        },
        list: function () {
            return ctx + "/sys/users/";
        },
        save: function () {
            return ctx + "/sys/users/";
        },
        delete: function (ids) {
            return ctx + "/sys/users/" + ids;
        },
        get: function (id) {
            return ctx + "/sys/users/" + id;
        },
        orgTree: function () {
            return ctx + "/sys/orgs/tree";
        },
        roleListAll: function () {
            return ctx + "/sys/roles/all";
        },
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysUser.input.initComponent();
            SysUser.input.initForm();
        },
        initComponent: function () {
            SysUserForm = $("#SysUserForm");
            SysUserOrg = $("#SysUserOrg");
            SysUserRole = $("#SysUserRole");
        },
        initForm: function () {
            SysUserForm.form({
                url: SysUser.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        SysUser.input.close();
                        SysUser.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysUserForm.submit();
        },
        close: function () {
            SysUserEdit.dialog('close');
        }
    },
    list: {
        init: function (ct) {
            ctx = ct;
            SysUser.list.initComponent();
            SysUser.list.initOrgTree();
            SysUser.list.initList();
        },
        initComponent: function () {
            SysUserList = $("#SysUserList");
            SysUserEdit = $('#SysUserEdit');
            SysUserOrgTree = $('#SysUserOrgTree');
        },
        initOrgTree: function () {
            SysUserOrgTree.tree({
                method: 'get',
                url: SysUser.URL.orgTree(),
                onSelect: function (node) {
                    if (SysUserOrgTree.tree('isLeaf', node.target)) {
                        SysUserList.datagrid({
                            queryParams: {orgId: node.id},
                        });
                    }
                }
            });
        },
        initList: function () {
            SysUserList.datagrid({
                url: SysUser.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 30,
                toolbar: '#SysUserToolbar',//SysUser.list.toolbar,
                singleSelect: false,
                collapsible: false,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键id', hidden: true},
                    {field: 'loginName', title: '登录名', width: '8.636%', hidden: false},
                    {field: 'name', title: '用户名', width: '8.636%', hidden: false},
                    {field: 'password', title: '密码', width: '8.636%', hidden: true},
                    {field: 'sex', title: '性别', width: '8.636%', hidden: false,formatter:function(value,row,index){return value==0?'男':'女';}},
                    {field: 'age', title: '年龄', width: '8.636%', hidden: false},
                    {field: 'phone', title: '手机号', width: '8.636%', hidden: false},
                    {field: 'userType', title: '用户类别', width: '8.636%', hidden: false,formatter:function(value,row,index){return value==0?'用户':'管理员';}},
                    {field: 'status', title: '用户状态', width: '8.636%', hidden: false,formatter:function(value,row,index){return value==0?'启用':'停用';}},
                    {field: 'updateTime', title: '更新时间', width: '10%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '10%', hidden: false},
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index,row) {
                    SysUserList.datagrid("unselectAll");
                    SysUserList.datagrid("selectRow",index);
                },
            });
        },
        getSelectionsIds: function () {
            var sels = SysUserList.datagrid("getSelections");
            var ids = [];
            for (var i in sels) {
                ids.push(sels[i].id);
            }
            ids = ids.join(",");
            return ids;
        },
        //增
        add: function () {
            SysUserEdit.dialog({
                    href: SysUser.URL.inputUI(),
                    onLoad: function () {
                        SysUserOrg.combotree({
                            url: SysUser.URL.orgTree(),
                            method: 'get',
                            panelHeight: 'auto'
                        });
                        SysUserRole.combobox({
                            url: SysUser.URL.roleListAll(),
                            method: 'get',
                            panelHeight: 'auto'
                        });
                    }
                })
                .dialog("open");
        },
        //改
        edit: function () {
            var sels = SysUserList.datagrid("getSelections");
            if (sels.length < 1) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            if (sels.length > 1) {
                $.messager.alert("对话框", "只能选择一行");
                return;
            }

            SysUserEdit.dialog({
                    href: SysUser.URL.inputUI(),
                    onLoad: function () {
                        //方案一：使用Form的load去load数据
                        //SysUserForm.form("load", SysUser.URL.get(sels[0].id));
                        //方案二：直接使用列表的row数据
                        //SysUserForm.form("load",sels[0]);
                        //方案三：使用Ajax请求数据
                        $.ajax({
                            type: "GET",
                            url: SysUser.URL.get(sels[0].id),
                            success: function (data) {
                                if (data.code == 200) {
                                    SysUserForm.form("load", data.data);
                                    SysUserOrg.combotree({
                                        url: SysUser.URL.orgTree(),
                                        method: 'get',
                                        panelHeight: 'auto',
                                        onLoadSuccess: function () {
                                            SysUserOrg.combotree('setValue', data.data.orgId);
                                        }
                                    });
                                    SysUserRole.combobox({
                                        url: SysUser.URL.roleListAll(),
                                        method: 'get',
                                        panelHeight: 'auto',
                                        onLoadSuccess: function () {
                                            SysUserRole.combobox('setValues', data.data.roleId);
                                        }
                                    });
                                }
                            }
                        });
                    }
                })
                .dialog("open");
        },
        //删
        delete: function () {
            var ids = SysUser.list.getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            $.messager.confirm({
                title: '确认提示框',
                msg: '你确定要删除吗？',
                fn: function (r) {
                    if (r) {
                        $.ajax({
                            type: "DELETE",
                            url: SysUser.URL.delete(ids),
                            success: function () {
                                SysUser.list.reload();
                                SysUser.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysUserList.datagrid("reload");
        },
        clearSelectionsAndChecked:function(){
            SysUserList.datagrid("clearChecked");
            SysUserList.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysUser']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysUserList.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysUser']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysUser']").each(function (i) {
                $(this).val("");
            });
        }
    }
}
