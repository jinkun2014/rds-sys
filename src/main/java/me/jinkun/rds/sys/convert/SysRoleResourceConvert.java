package me.jinkun.rds.sys.convert;

import me.jinkun.rds.common.base.BaseConvert;
import me.jinkun.rds.sys.domain.SysRoleResource;
import me.jinkun.rds.sys.web.form.SysRoleResourceForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysRoleResource转换类！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysRoleResourceConvert extends BaseConvert {

    public static SysRoleResource formToEntity(SysRoleResourceForm form) {
        if (form != null) {
            SysRoleResource entity = new SysRoleResource();
            entity.setId(form.getId());
            entity.setRoleId(form.getRoleId());
            entity.setResourceId(form.getResourceId());
            return entity;
        }
        return null;
    }

    public static List<SysRoleResource> formListToEntityList(List<SysRoleResourceForm> formList) {
        List<SysRoleResource> entityList = new ArrayList<SysRoleResource>();
        if (formList != null && formList.size() > 0) {
            for (SysRoleResourceForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysRoleResourceForm entityToForm(SysRoleResource entity) {
        if (entity != null) {
            SysRoleResourceForm form = new SysRoleResourceForm();
            form.setId(entity.getId());
            form.setRoleId(entity.getRoleId());
            form.setResourceId(entity.getResourceId());
            return form;
        }
        return null;
    }

    public static List<SysRoleResourceForm> entityListToFormList(List<SysRoleResource> entityList) {
        List<SysRoleResourceForm> formList = new ArrayList<SysRoleResourceForm>();
        if (entityList != null && entityList.size() > 0) {
            for (SysRoleResource entity : entityList) {
                formList.add(entityToForm(entity));
            }
            return formList;
        }
        return formList;
    }
}
