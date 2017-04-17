package me.jinkun.rds.sys.convert;

import me.jinkun.rds.common.base.BaseConvert;
import me.jinkun.rds.sys.domain.SysRole;
import me.jinkun.rds.sys.web.form.SysRoleForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysRole转换类！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysRoleConvert extends BaseConvert {

    public static SysRole formToEntity(SysRoleForm form) {
        if (form != null) {
            SysRole entity = new SysRole();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setSeq(form.getSeq());
            entity.setDescription(form.getDescription());
            entity.setStatus(form.getStatus());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysRole> formListToEntityList(List<SysRoleForm> formList) {
        List<SysRole> entityList = new ArrayList<SysRole>();
        if (formList != null && formList.size() > 0) {
            for (SysRoleForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysRoleForm entityToForm(SysRole entity) {
        if (entity != null) {
            SysRoleForm form = new SysRoleForm();
            form.setId(entity.getId());
            form.setName(entity.getName());
            form.setSeq(entity.getSeq());
            form.setDescription(entity.getDescription());
            form.setStatus(entity.getStatus());
            form.setDelFlag(entity.getDelFlag());
            form.setUpdateTime(dateToString(entity.getUpdateTime()));
            form.setCreateTime(dateToString(entity.getCreateTime()));
            return form;
        }
        return null;
    }

    public static List<SysRoleForm> entityListToFormList(List<SysRole> entityList) {
        List<SysRoleForm> formList = new ArrayList<SysRoleForm>();
        if (entityList != null && entityList.size() > 0) {
            for (SysRole entity : entityList) {
                formList.add(entityToForm(entity));
            }
            return formList;
        }
        return formList;
    }
}
