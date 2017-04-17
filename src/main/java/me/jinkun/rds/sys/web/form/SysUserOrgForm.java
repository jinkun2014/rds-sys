package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.common.base.BaseForm;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysUserOrgForm extends BaseForm {

    //=====================实体属性=========================
    //主键id，数据库字段为：sys_user_org.id
    private Long id;

    //用户id，数据库字段为：sys_user_org.user_id
    private Long userId;

    //机构id，数据库字段为：sys_user_org.org_id
    private Long orgId;

    //=====================表单属性=========================


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
