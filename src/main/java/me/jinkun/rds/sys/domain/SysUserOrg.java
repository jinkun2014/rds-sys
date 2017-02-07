package me.jinkun.rds.sys.domain;

import java.io.Serializable;


/**
 * @Description: 用户-机构,数据库表为： sys_user_org<br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysUserOrg implements Serializable{
    //主键id，数据库字段为：sys_user_org.id
    private Long id;

    //用户id，数据库字段为：sys_user_org.user_id
    private Long userId;

    //机构id，数据库字段为：sys_user_org.org_id
    private Long orgId;

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

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

}