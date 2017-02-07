package me.jinkun.rds.sys.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description: 用户,数据库表为： sys_user<br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysUser implements Serializable {
    //主键id，数据库字段为：sys_user.id
    private Long id;

    //登录名，数据库字段为：sys_user.login_name
    private String loginName;

    //用户名，数据库字段为：sys_user.name
    private String name;

    //密码，数据库字段为：sys_user.password
    private String password;

    //性别，数据库字段为：sys_user.sex
    private Integer sex;

    //年龄，数据库字段为：sys_user.age
    private Integer age;

    //手机号，数据库字段为：sys_user.phone
    private String phone;

    //用户类别，数据库字段为：sys_user.user_type
    private Integer userType;

    //用户状态，数据库字段为：sys_user.status
    private Integer status;

    //删除标记，数据库字段为：sys_user.del_flag
    private Integer delFlag;

    //更新时间，数据库字段为：sys_user.update_time
    private Date updateTime;

    //创建时间，数据库字段为：sys_user.create_time
    private Date createTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserType() {
        return this.userType;
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