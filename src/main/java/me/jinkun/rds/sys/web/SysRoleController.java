package me.jinkun.rds.sys.web;

import me.jinkun.rds.sys.service.SysRoleService;
import me.jinkun.rds.sys.web.form.SysRoleForm;
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
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/sys/roles/ui/{ui}", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-role/sys-role-" + ui;
    }

    @RequestMapping(value = "/sys/roles", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysRoleForm form) {
        return sysRoleService.saveOrUpdate(form);
    }

    @RequestMapping(value = "/sys/roles/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        return sysRoleService.deleteByIds(ids);
    }

    @RequestMapping(value = "/sys/roles", method = RequestMethod.GET)
    @ResponseBody
    public Object list(SysRoleForm form) {
        return sysRoleService.listPage(form);
    }

    @RequestMapping(value = "/sys/roles/all", method = RequestMethod.GET)
    @ResponseBody
    public Object listAll(SysRoleForm form) {
        return sysRoleService.list(form).getData();
    }

    @RequestMapping(value = "/sys/roles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        return sysRoleService.get(id);
    }

    @RequestMapping(value = "/sys/roles/{id}/resources", method = RequestMethod.GET)
    @ResponseBody
    public Object getResources(@PathVariable("id") Long id) {
        return sysRoleService.getResources(id);
    }

    @RequestMapping(value = "/sys/roles/{id}/resources", method = RequestMethod.POST)
    @ResponseBody
    public Object saveResources(@PathVariable("id") Long id, String ids) {
        return sysRoleService.saveResources(id, ids);
    }
}
