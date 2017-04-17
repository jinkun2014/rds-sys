package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.common.base.BaseForm;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysRoleForm extends BaseForm {

    //=====================实体属性=========================
    //主键id，数据库字段为：sys_role.id
    private Long id;

    //角色名，数据库字段为：sys_role.name
    private String name;

    //排序号，数据库字段为：sys_role.seq
    private Integer seq;

    //简介，数据库字段为：sys_role.description
    private String description;

    //状态，数据库字段为：sys_role.status
    private Integer status;

    //删除标记，数据库字段为：sys_role.del_flag
    private Integer delFlag;

    //更新时间，数据库字段为：sys_role.update_time
    private String updateTime;

    //创建时间，数据库字段为：sys_role.create_time
    private String createTime;

    //=====================表单属性=========================


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

}
