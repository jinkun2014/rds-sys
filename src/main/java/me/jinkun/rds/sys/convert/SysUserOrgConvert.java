package me.jinkun.rds.sys.convert;

import me.jinkun.rds.common.base.BaseConvert;
import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysUserOrg转换类！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysUserOrgConvert extends BaseConvert {

    public static SysUserOrg formToEntity(SysUserOrgForm form) {
        if (form != null) {
            SysUserOrg entity = new SysUserOrg();
            entity.setId(form.getId());
            entity.setUserId(form.getUserId());
            entity.setOrgId(form.getOrgId());
            return entity;
        }
        return null;
    }

    public static List<SysUserOrg> formListToEntityList(List<SysUserOrgForm> formList) {
        List<SysUserOrg> entityList = new ArrayList<SysUserOrg>();
        if (formList != null && formList.size() > 0) {
            for (SysUserOrgForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysUserOrgForm entityToForm(SysUserOrg entity) {
        if (entity != null) {
            SysUserOrgForm form = new SysUserOrgForm();
            form.setId(entity.getId());
            form.setUserId(entity.getUserId());
            form.setOrgId(entity.getOrgId());
            return form;
        }
        return null;
    }

    public static List<SysUserOrgForm> entityListToFormList(List<SysUserOrg> entityList) {
        List<SysUserOrgForm> formList = new ArrayList<SysUserOrgForm>();
        if (entityList != null && entityList.size() > 0) {
            for (SysUserOrg entity : entityList) {
                formList.add(entityToForm(entity));
            }
            return formList;
        }
        return formList;
    }
}
