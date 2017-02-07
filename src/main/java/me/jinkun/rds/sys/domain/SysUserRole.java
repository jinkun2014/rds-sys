package me.jinkun.rds.sys.domain;

import java.io.Serializable;


/**
 * @Description: 用户-角色,数据库表为： sys_user_role<br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysUserRole implements Serializable{
    //主键id，数据库字段为：sys_user_role.id
    private Long id;

    //用户id，数据库字段为：sys_user_role.user_id
    private Long userId;

    //角色id，数据库字段为：sys_user_role.role_id
    private Long roleId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

}