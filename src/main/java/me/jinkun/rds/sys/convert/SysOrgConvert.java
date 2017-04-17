package me.jinkun.rds.sys.convert;

import me.jinkun.rds.common.base.BaseConvert;
import me.jinkun.rds.sys.domain.SysOrg;
import me.jinkun.rds.sys.web.form.SysOrgForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysOrg转换类！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysOrgConvert extends BaseConvert {

    public static SysOrg formToEntity(SysOrgForm form) {
        if (form != null) {
            SysOrg entity = new SysOrg();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setAddress(form.getAddress());
            entity.setCode(form.getCode());
            entity.setIcon(form.getIcon());
            entity.setPid(form.getPid());
            entity.setIsLeaf(form.getIsLeaf());
            entity.setSeq(form.getSeq());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysOrg> formListToEntityList(List<SysOrgForm> formList) {
        List<SysOrg> entityList = new ArrayList<SysOrg>();
        if (formList != null && formList.size() > 0) {
            for (SysOrgForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysOrgForm entityToForm(SysOrg entity) {
        if (entity != null) {
            SysOrgForm form = new SysOrgForm();
            form.setId(entity.getId());
            form.setName(entity.getName());
            form.setAddress(entity.getAddress());
            form.setCode(entity.getCode());
            form.setIcon(entity.getIcon());
            form.setPid(entity.getPid());
            form.setIsLeaf(entity.getIsLeaf());
            form.setSeq(entity.getSeq());
            form.setDelFlag(entity.getDelFlag());
            form.setUpdateTime(dateToString(entity.getUpdateTime()));
            form.setCreateTime(dateToString(entity.getCreateTime()));
            form.setIconCls(entity.getIcon());
            return form;
        }
        return null;
    }

    public static List<SysOrgForm> entityListToFormList(List<SysOrg> entityList) {
        List<SysOrgForm> formList = new ArrayList<SysOrgForm>();
        if (entityList != null && entityList.size() > 0) {
            for (SysOrg entity : entityList) {
                formList.add(entityToForm(entity));
            }
            return formList;
        }
        return formList;
    }
}
