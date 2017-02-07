package me.jinkun.rds.sys.web;

import me.jinkun.rds.sys.service.SysUserRoleService;
import me.jinkun.rds.sys.web.form.SysUserRoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by jinkun on 2016/12/4.
 */
@Controller
public class SysUserRoleController {

    @Autowired
    SysUserRoleService sysUserRoleService;

    @RequestMapping(value = "/sys/user/roles/ui/{ui}", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-user-role/sys-user-role-" + ui;
    }

    @RequestMapping(value = "/sys/user/roles", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysUserRoleForm form) {
        return sysUserRoleService.saveOrUpdate(form);
    }

    @RequestMapping(value = "/sys/user/roles/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        return sysUserRoleService.deleteByIds(ids);
    }

    @RequestMapping(value = "/sys/user/roles", method = RequestMethod.GET)
    @ResponseBody
    public Object list(SysUserRoleForm form) {
        return sysUserRoleService.listPage(form);
    }

    @RequestMapping(value = "/sys/user/roles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        return sysUserRoleService.get(id);
    }
}
