package me.jinkun.rds.sys.web;

import me.jinkun.rds.sys.service.SysUserService;
import me.jinkun.rds.sys.web.form.SysUserForm;
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
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/sys/users/ui/{ui}", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-user/sys-user-" + ui;
    }

    @RequestMapping(value = "/sys/users", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysUserForm form) {
        return sysUserService.saveOrUpdate(form);
    }

    @RequestMapping(value = "/sys/users/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        return sysUserService.deleteByIds(ids);
    }

    @RequestMapping(value = "/sys/users", method = RequestMethod.GET)
    @ResponseBody
    public Object list(SysUserForm form) {
        return sysUserService.listPage(form);
    }

    @RequestMapping(value = "/sys/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        return sysUserService.get(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(SysUserForm form) {
        return sysUserService.login(form);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        return sysUserService.logout();
    }

    @RequestMapping(value = "/sys/users/menu/top", method = RequestMethod.GET)
    @ResponseBody
    public Object topMenu() {
        return sysUserService.topMenu();
    }

    @RequestMapping(value = "/sys/users/menu/{id}/tree", method = RequestMethod.GET)
    @ResponseBody
    public Object menuTree(@PathVariable("id") Long id) {
        return sysUserService.menuTree(id);
    }
}
