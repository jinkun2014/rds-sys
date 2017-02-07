package me.jinkun.rds.sys.web;

import me.jinkun.rds.sys.service.SysUserOrgService;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;
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
public class SysUserOrgController {

    @Autowired
    SysUserOrgService sysUserOrgService;

    @RequestMapping(value = "/sys/user/orgs/ui/{ui}", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-user-org/sys-user-org-" + ui;
    }

    @RequestMapping(value = "/sys/user/orgs", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysUserOrgForm form) {
        return sysUserOrgService.saveOrUpdate(form);
    }

    @RequestMapping(value = "/sys/user/orgs/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        return sysUserOrgService.deleteByIds(ids);
    }

    @RequestMapping(value = "/sys/user/orgs", method = RequestMethod.GET)
    @ResponseBody
    public Object list(SysUserOrgForm form) {
        return sysUserOrgService.listPage(form);
    }

    @RequestMapping(value = "/sys/user/orgs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        return sysUserOrgService.get(id);
    }
}
