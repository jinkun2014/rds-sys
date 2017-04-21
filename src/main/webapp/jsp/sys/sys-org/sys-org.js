var ctx = "";//项目部署的工程名
var SysOrgList;
var SysOrgEdit;
var SysOrgForm;

//其它组件
var parentOrg;

var SysOrg = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/orgs/ui/input";
        },
        listUI: function () {
            return ctx + "/sys/orgs/ui/list";
        },
        list: function () {
            return ctx + "/sys/orgs/";
        },
        tree: function () {
            return ctx + "/sys/orgs/tree";
        },
        save: function () {
            return ctx + "/sys/orgs/";
        },
        delete: function (ids) {
            return ctx + "/sys/orgs/" + ids;
        },
        get: function (id) {
            return ctx + "/sys/orgs/" + id;
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysOrg.input.initComponent();
            SysOrg.input.initForm();
        },
        initComponent: function () {
            SysOrgForm = $("#SysOrgForm");
            parentOrg = $("#parentOrg");
        },
        initForm: function () {
            SysOrgForm.form({
                url: SysOrg.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        SysOrg.input.close();
                        SysOrg.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysOrgForm.submit();
        },
        close: function () {
            SysOrgEdit.dialog('close');
        },
    },
    list: {
        init: function (ct) {
            ctx = ct;
            SysOrg.list.initComponent();
            SysOrg.list.initList();
        },
        initComponent: function () {
            SysOrgList = $("#SysOrgList");
            SysOrgEdit = $('#SysOrgEdit');
        },
        initList: function () {
            SysOrgList.treegrid({
                url: SysOrg.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 30,
                toolbar: '#SysOrgToolbar',//SysOrg.list.toolbar,
                singleSelect: false,
                collapsible: false,
                idField: 'id',
                treeField: 'name',
                parentField: 'pid',
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键id', hidden: true},
                    {field: 'name', title: '组织名', width: '13.571%', hidden: false},
                    {field: 'address', title: '地址', width: '13.571%', hidden: false},
                    {field: 'code', title: '编号', width: '13.571%', hidden: false},
                    {field: 'icon', title: '图标', width: '13.571%', hidden: false},
                    {field: 'pid', title: '父级主键', width: '13.571%', hidden: true},
                    {field: 'seq', title: '排序', width: '13.571%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '13.571%', hidden: false},
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (row) {
                    SysOrgList.treegrid("unselectAll");
                    SysOrgList.treegrid("selectRow",row.id);
                },
                loadFilter: function (data, parentId) {
                    var opt = $(this).data().treegrid.options;
                    var parentField;
                    if (opt.parentField) {
                        parentField = opt.parentField;
                        var jsonStr = JSON.stringify(data); //可以将json对象转换成json对符串
                        jsonStr = jsonStr.replace(new RegExp(parentField, "gm"), "_parentId");
                        return JSON.parse(jsonStr); //可以将json字符串转换成json对象
                    }
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysOrgList.datagrid("getSelections");
            var ids = [];
            for (var i in sels) {
                ids.push(sels[i].id);
            }
            ids = ids.join(",");
            return ids;
        },
        //增
        add: function () {
            SysOrgEdit.dialog({
                    href: SysOrg.URL.inputUI(),
                    onLoad: function () {
                        parentOrg.combotree({
                            url: SysOrg.URL.tree(),
                            method: 'get',
                            panelHeight: 'auto'
                        });
                    }
                })
                .dialog("open");
        },
        //改
        edit: function () {
            var sels = SysOrgList.treegrid("getSelections");
            if (sels.length < 1) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            if (sels.length > 1) {
                $.messager.alert("对话框", "只能选择一行");
                return;
            }

            SysOrgEdit.dialog({
                    href: SysOrg.URL.inputUI(),
                    onLoad: function () {
                        //方案一：使用Form的load去load数据
                        //SysOrgForm.form("load", SysOrg.URL.get(sels[0].id));
                        //方案二：直接使用列表的row数据
                        //SysOrgForm.form("load",sels[0]);
                        //方案三：使用Ajax请求数据
                        $.ajax({
                            type: "GET",
                            url: SysOrg.URL.get(sels[0].id),
                            success: function (data) {
                                if (data.code == 200) {
                                    SysOrgForm.form("load", data.data);
                                    parentOrg.combotree({
                                        url: SysOrg.URL.tree(),
                                        method: 'get',
                                        panelHeight: 'auto',
                                        onLoadSuccess: function () {
                                            parentOrg.combotree('setValue', data.data.pid);
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
            var ids = SysOrg.list.getSelectionsIds();
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
                            url: SysOrg.URL.delete(ids),
                            success: function () {
                                SysOrg.list.reload();
                                SysOrg.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysOrgList.treegrid("reload");
        },
        collapseAll: function () {
            var roots = SysOrgList.treegrid("getRoots");
            for (var i in roots) {
                SysOrgList.treegrid("collapseAll", roots[i].id);
            }
        },
        expandAll: function () {
            var roots = SysOrgList.treegrid("getRoots");
            for (var i in roots) {
                SysOrgList.treegrid("expandAll", roots[i].id);
            }
        },
        clearSelectionsAndChecked: function () {
            SysOrgList.treegrid("clearChecked");
            SysOrgList.treegrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysOrg']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysOrgList.treegrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysOrg']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysOrg']").each(function (i) {
                $(this).val("");
            });
        }
    }
}
