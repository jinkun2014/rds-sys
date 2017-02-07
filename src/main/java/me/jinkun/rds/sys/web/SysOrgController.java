package me.jinkun.rds.sys.web;

import me.jinkun.rds.sys.service.SysOrgService;
import me.jinkun.rds.sys.web.form.SysOrgForm;
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
public class SysOrgController {

    @Autowired
    SysOrgService sysOrgService;

    @RequestMapping(value = "/sys/orgs/ui/{ui}", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-org/sys-org-" + ui;
    }

    @RequestMapping(value = "/sys/orgs", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysOrgForm form) {
        return sysOrgService.saveOrUpdate(form);
    }

    @RequestMapping(value = "/sys/orgs/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        return sysOrgService.deleteByIds(ids);
    }

    @RequestMapping(value = "/sys/orgs", method = RequestMethod.GET)
    @ResponseBody
    public Object list(SysOrgForm form) {
        return sysOrgService.listPage(form);
    }

    @RequestMapping(value = "/sys/orgs/tree", method = RequestMethod.GET)
    @ResponseBody
    public Object tree(SysOrgForm form) {
        return sysOrgService.tree(form);
    }

    @RequestMapping(value = "/sys/orgs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        return sysOrgService.get(id);
    }
}
