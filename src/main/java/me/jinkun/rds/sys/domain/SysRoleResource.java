package me.jinkun.rds.sys.domain;

import java.io.Serializable;


/**
 * @Description: 角色资源,数据库表为： sys_role_resource<br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysRoleResource implements Serializable{
    //主键id，数据库字段为：sys_role_resource.id
    private Long id;

    //角色id，数据库字段为：sys_role_resource.role_id
    private Long roleId;

    //资源id，数据库字段为：sys_role_resource.resource_id
    private Long resourceId;

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