package me.jinkun.rds.sys.web.form;

import me.jinkun.rds.common.base.BaseForm;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysOrgForm extends BaseForm {

    //=====================实体属性=========================
    //主键id，数据库字段为：sys_org.id
    private Long id;

    //组织名，数据库字段为：sys_org.name
    private String name;

    //地址，数据库字段为：sys_org.address
    private String address;

    //编号，数据库字段为：sys_org.code
    private String code;

    //图标，数据库字段为：sys_org.icon
    private String icon;

    //父级主键，数据库字段为：sys_org.pid
    private Long pid;

    //叶子节点，数据库字段为：sys_org.is_leaf
    private Integer isLeaf;

    //排序，数据库字段为：sys_org.seq
    private Integer seq;

    //删除标记，数据库字段为：sys_org.del_flag
    private Integer delFlag;

    //更新时间，数据库字段为：sys_org.update_time
    private String updateTime;

    //创建时间，数据库字段为：sys_org.create_time
    private String createTime;

    //=====================表单属性=========================
    private String iconCls;


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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
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

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getIsLeaf() {
        return this.isLeaf;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return this.seq;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
}
