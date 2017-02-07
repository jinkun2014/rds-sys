package me.jinkun.rds.sys.web;

import me.jinkun.rds.sys.service.SysResourceService;
import me.jinkun.rds.sys.web.form.SysResourceForm;
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
public class SysResourceController {

    @Autowired
    SysResourceService sysResourceService;

    @RequestMapping(value = "/sys/resources/ui/{ui}", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-resource/sys-resource-" + ui;
    }

    @RequestMapping(value = "/sys/resources", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysResourceForm form) {
        return sysResourceService.saveOrUpdate(form);
    }

    @RequestMapping(value = "/sys/resources/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        return sysResourceService.deleteByIds(ids);
    }

    @RequestMapping(value = "/sys/resources", method = RequestMethod.GET)
    @ResponseBody
    public Object list(SysResourceForm form) {
        return sysResourceService.listPage(form);
    }

    @RequestMapping(value = "/sys/resources/tree", method = RequestMethod.GET)
    @ResponseBody
    public Object tree(SysResourceForm form) {
        return sysResourceService.tree(form);
    }

    @RequestMapping(value = "/sys/resources/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        return sysResourceService.get(id);
    }
}
