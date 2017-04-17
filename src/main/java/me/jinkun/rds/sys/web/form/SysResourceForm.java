package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.common.base.BaseForm;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysResourceForm extends BaseForm {

    //=====================实体属性=========================
    //主键，数据库字段为：sys_resource.id
    private Long id;

    //资源名称，数据库字段为：sys_resource.name
    private String name;

    //资源路径，数据库字段为：sys_resource.url
    private String url;

    //打开方式 ajax,iframe，数据库字段为：sys_resource.open_mode
    private String openMode;

    //资源介绍，数据库字段为：sys_resource.description
    private String description;

    //资源图标，数据库字段为：sys_resource.icon
    private String icon;

    //父级资源id，数据库字段为：sys_resource.pid
    private Long pid;

    //排序，数据库字段为：sys_resource.seq
    private Integer seq;

    //状态，数据库字段为：sys_resource.status
    private Integer status;

    //资源类别，数据库字段为：sys_resource.resource_type
    private Integer resourceType;

    //是否是叶子，数据库字段为：sys_resource.is_leaf
    private Integer isLeaf;

    //删除标记，数据库字段为：sys_resource.del_flag
    private Integer delFlag;

    //更新时间，数据库字段为：sys_resource.update_time
    private String updateTime;

    //创建时间，数据库字段为：sys_resource.create_time
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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public String getOpenMode() {
        return this.openMode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getResourceType() {
        return this.resourceType;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getIsLeaf() {
        return this.isLeaf;
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
