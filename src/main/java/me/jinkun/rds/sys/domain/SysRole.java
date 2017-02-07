package me.jinkun.rds.sys.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;


/**
 * @Description: 角色,数据库表为： sys_role<br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysRole implements Serializable{
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
    private Date updateTime;

    //创建时间，数据库字段为：sys_role.create_time
    private Date createTime;

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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

}