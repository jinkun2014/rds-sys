package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.common.base.BaseForm;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysRoleResourceForm extends BaseForm {

    //=====================实体属性=========================
    //主键id，数据库字段为：sys_role_resource.id
    private Long id;

    //角色id，数据库字段为：sys_role_resource.role_id
    private Long roleId;

    //资源id，数据库字段为：sys_role_resource.resource_id
    private Long resourceId;

    //=====================表单属性=========================


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return this.resourceId;
    }

}
