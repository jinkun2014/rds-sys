package me.jinkun.rds.common.shiro;

import java.io.Serializable;
import java.util.Set;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/1/8.
 */
public class ShiroUser implements Serializable {

    private Long id;
    private final String loginName;
    private String name;
    private Set<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShiroUser(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
