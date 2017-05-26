var ctx = "";//项目部署的工程名
var SysRoleList;
var SysRoleEdit;
var SysRoleForm;

//其它组件
var SysRoleResourceEdit;
var SysResourceTree;

var SysRole = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/roles/ui/input";
        },
        listUI: function () {
            return ctx + "/sys/roles/ui/list";
        },
        resourceUI: function () {
            return ctx + "/sys/roles/ui/resource";
        },
        list: function () {
            return ctx + "/sys/roles/";
        },
        save: function () {
            return ctx + "/sys/roles/";
        },
        delete: function (ids) {
            return ctx + "/sys/roles/" + ids;
        },
        get: function (id) {
            return ctx + "/sys/roles/" + id;
        },
        resourceTree: function () {
            return ctx + "/sys/resources/tree"
        },
        getResources: function (id) {
            return ctx + "/sys/roles/" + id + "/resources";
        },
        saveResources: function (id) {
            return ctx + "/sys/roles/" + id + "/resources";
        },
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysRole.input.initComponent();
            SysRole.input.initForm();
        },
        initComponent: function () {
            SysRoleForm = $("#SysRoleForm");
        },
        initForm: function () {
            SysRoleForm.form({
                url: SysRole.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        SysRole.input.close();
                        SysRole.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysRoleForm.submit();
        },
        close: function () {
            SysRoleEdit.dialog('close');
        }
    },
    resource: {
        init: function (ct, id) {
            ctx = ct;
            SysRole.resource.initComponent();
            SysRole.resource.initList(id);
        },
        initComponent: function () {
            SysResourceTree = $("#SysResourceTree");
        },
        initList: function (id) {
            SysResourceTree.tree({
                method: 'get',
                url: SysRole.URL.resourceTree(),
                checkbox: function (node) {
                    return true;
                },
                onLoadSuccess: function (node, data) {

                    //回显资源列表
                    $.ajax({
                        type: "GET",
                        url: SysRole.URL.getResources(id),
                        success: function (data) {
                            if (data.code == 200) {
                                //回显已有的权限
                                var root = SysResourceTree.tree('getRoots'); // 取到树的根节点
                                for (var i in root) {
                                    for (var j in data.data) {
                                        console.info(data.data[j]);
                                        SysRole.resource.checkTreeNode(root, data.data[j]);
                                    }
                                }
                            }
                        }
                    });
                }
            });
        },
        save: function () {
            var nodes = SysResourceTree.tree('getChecked', ['checked', 'indeterminate']);
            if (nodes != null) {
                var ids = [];
                for (var i in nodes) {
                    ids.push(nodes[i].id);
                }
                ids = ids.join(",");

                $.ajax({
                    type: "POST",
                    url: SysRole.URL.saveResources(SysRoleList.datagrid("getSelections")[0].id),
                    data: {ids: ids},
                    success: function (data) {
                        if (data.code == 200) {
                            SysRole.resource.close();
                        }
                    }
                });
            }
            SysRole.resource.close();
        },
        cancel: function () {
            SysRole.resource.close();
        },
        close: function () {
            SysRoleResourceEdit.dialog('close');
        },
        // 递归遍历所有节点并选中
        checkTreeNode: function (children, id) {
            if (children) {
                for (var i = 0; i < children.length; i++) {
                    if (children[i].id == id) {
                        var node = SysResourceTree.tree('find', children[i].id).target;
                        if (SysResourceTree.tree('isLeaf', node)) {
                            SysResourceTree.tree('check', node); // 选中树叶子节点
                        }
                        break;
                    } else {
                        if (children[i].children != null) {
                            SysRole.resource.checkTreeNode(children[i].children, id); // 没有找到则接着遍历
                        }
                    }
                }
            }
        }
    },
    list: {
        init: function (ct) {
            ctx = ct;
            SysRole.list.initComponent();
            SysRole.list.initList();
        },
        initComponent: function () {
            SysRoleList = $("#SysRoleList");
            SysRoleEdit = $('#SysRoleEdit');
            SysRoleResourceEdit = $('#SysRoleResourceEdit');
        },
        initList: function () {
            SysRoleList.datagrid({
                url: SysRole.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 30,
                toolbar: '#SysRoleToolbar',//SysRole.list.toolbar,
                //允许多选
                singleSelect: false,
                //设置复选框和行的选择状态不同步
                checkOnSelect: true,
                selectOnCheck: true,
                collapsible: false,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键id', hidden: true},
                    {field: 'name', title: '角色名', width: '13.571%', hidden: false},
                    {field: 'seq', title: '排序号', width: '13.571%', hidden: false},
                    {field: 'description', title: '简介', width: '13.571%', hidden: false},
                    {
                        field: 'status',
                        title: '状态',
                        width: '13.571%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '启用' : '停用';
                        }
                    },
                    {field: 'delFlag', title: '删除标记', width: '13.571%', hidden: true},
                    {field: 'updateTime', title: '更新时间', width: '13.571%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '13.571%', hidden: false},
                    {
                        field: 'action',
                        title: '操作',
                        width: '10%',
                        hidden: false,
                        //formatter: function (value, row, index) {
                        //    var html = '<a href="#" class="easyui-linkbutton-edit" data-options="iconCls:\'icon-edit\'" plain="true" onclick="javascript:SysRole.list.edit('+row.id+')">编辑</a>';
                        //    html += '<a href="#" class="easyui-linkbutton-authorize" data-options="iconCls:\'icon-reload\'" plain="true" onclick="javascript:SysRole.list.authorize('+row.id+')">授权</a>'
                        //    return html;
                        //}
                    }
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index, row) {
                    SysRoleList.datagrid("unselectAll");
                    SysRoleList.datagrid("selectRow", index);
                },
                onLoadSuccess: function (data) {
                    $('.easyui-linkbutton-edit').linkbutton({text: '编辑'});
                    $('.easyui-linkbutton-authorize').linkbutton({text: '授权'});
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysRoleList.datagrid("getSelections");
            var ids = [];
            for (var i in sels) {
                ids.push(sels[i].id);
            }
            ids = ids.join(",");
            return ids;
        },
        //增
        add: function () {
            SysRoleEdit.dialog({
                    href: SysRole.URL.inputUI(),
                    onLoad: function () {

                    }
                })
                .dialog("open");
        },
        //改
        edit: function () {
            var sels = SysRoleList.datagrid("getSelections");
            if (sels.length < 1) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            if (sels.length > 1) {
                $.messager.alert("对话框", "只能选择一行");
                return;
            }

            SysRoleEdit.dialog({
                    href: SysRole.URL.inputUI(),
                    onLoad: function () {
                        //方案一：使用Form的load去load数据
                        //SysRoleForm.form("load", SysRole.URL.get(sels[0].id));
                        //方案二：直接使用列表的row数据
                        //SysRoleForm.form("load",sels[0]);
                        //方案三：使用Ajax请求数据
                        $.ajax({
                            type: "GET",
                            url: SysRole.URL.get(sels[0].id),
                            success: function (data) {
                                if (data.code == 200) {
                                    SysRoleForm.form("load", data.data);
                                }
                            }
                        });
                    }
                })
                .dialog("open");
        },
        //删
        delete: function () {
            var ids = SysRole.list.getSelectionsIds();
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
                            url: SysRole.URL.delete(ids),
                            success: function () {
                                SysRole.list.reload();
                                //如果不清空，删除还可以编辑BUG
                                SysRole.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysRoleList.datagrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysRoleList.datagrid("clearChecked");
            SysRoleList.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysRole']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysRoleList.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysRole']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                    $('.easyui-linkbutton-edit').linkbutton({text: '编辑'});
                    $('.easyui-linkbutton-authorize').linkbutton({text: '授权'});
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysRole']").each(function (i) {
                $(this).val("");
            });
        },
        //授权
        authorize: function () {
            var sels = SysRoleList.datagrid("getSelections");
            if (sels.length < 1) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            if (sels.length > 1) {
                $.messager.alert("对话框", "只能选择一行");
                return;
            }

            SysRoleResourceEdit.dialog({
                    href: SysRole.URL.resourceUI(),
                    buttons: [{
                        text: '保存',
                        iconCls: 'icon-save',
                        handler: function () {
                            SysRole.resource.save();
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            SysRole.resource.cancel();
                        }
                    }],
                    onLoad: function () {
                        //var root = SysResourceTree.tree('getRoot'); // 取到树的根节点
                        //SysRole.list.selectTreeNode(root.children, 244);
                        SysRole.resource.init(ctx, sels[0].id);
                    }
                })
                .dialog("open");
        },
    }
}
