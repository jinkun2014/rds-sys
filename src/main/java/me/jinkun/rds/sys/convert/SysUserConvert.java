package me.jinkun.rds.sys.convert;

import me.jinkun.rds.common.base.BaseConvert;
import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.web.form.SysUserForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysUser转换类！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysUserConvert extends BaseConvert {

    public static SysUser formToEntity(SysUserForm form) {
        if (form != null) {
            SysUser entity = new SysUser();
            entity.setId(form.getId());
            entity.setLoginName(form.getLoginName());
            entity.setName(form.getName());
            entity.setPassword(form.getPassword());
            entity.setSex(form.getSex());
            entity.setAge(form.getAge());
            entity.setPhone(form.getPhone());
            entity.setUserType(form.getUserType());
            entity.setStatus(form.getStatus());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysUser> formListToEntityList(List<SysUserForm> formList) {
        List<SysUser> entityList = new ArrayList<SysUser>();
        if (formList != null && formList.size() > 0) {
            for (SysUserForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysUserForm entityToForm(SysUser entity) {
        if (entity != null) {
            SysUserForm form = new SysUserForm();
            form.setId(entity.getId());
            form.setLoginName(entity.getLoginName());
            form.setName(entity.getName());
            form.setPassword(entity.getPassword());
            form.setSex(entity.getSex());
            form.setAge(entity.getAge());
            form.setPhone(entity.getPhone());
            form.setUserType(entity.getUserType());
            form.setStatus(entity.getStatus());
            form.setDelFlag(entity.getDelFlag());
            form.setUpdateTime(dateToString(entity.getUpdateTime()));
            form.setCreateTime(dateToString(entity.getCreateTime()));
            return form;
        }
        return null;
    }

    public static List<SysUserForm> entityListToFormList(List<SysUser> entityList) {
        List<SysUserForm> formList = new ArrayList<SysUserForm>();
        if (entityList != null && entityList.size() > 0) {
            for (SysUser entity : entityList) {
                formList.add(entityToForm(entity));
            }
            return formList;
        }
        return formList;
    }
}
