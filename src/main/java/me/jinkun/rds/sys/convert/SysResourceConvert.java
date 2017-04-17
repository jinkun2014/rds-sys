package me.jinkun.rds.sys.convert;

import me.jinkun.rds.common.base.BaseConvert;
import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.web.form.SysResourceForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SysResource转换类！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class SysResourceConvert extends BaseConvert {

    public static SysResource formToEntity(SysResourceForm form) {
        if (form != null) {
            SysResource entity = new SysResource();
            entity.setId(form.getId());
            entity.setName(form.getName());
            entity.setUrl(form.getUrl());
            entity.setOpenMode(form.getOpenMode());
            entity.setDescription(form.getDescription());
            entity.setIcon(form.getIcon());
            entity.setPid(form.getPid());
            entity.setSeq(form.getSeq());
            entity.setStatus(form.getStatus());
            entity.setResourceType(form.getResourceType());
            entity.setIsLeaf(form.getIsLeaf());
            entity.setDelFlag(form.getDelFlag());
            entity.setUpdateTime(stringToDate(form.getUpdateTime()));
            entity.setCreateTime(stringToDate(form.getCreateTime()));
            return entity;
        }
        return null;
    }

    public static List<SysResource> formListToEntityList(List<SysResourceForm> formList) {
        List<SysResource> entityList = new ArrayList<SysResource>();
        if (formList != null && formList.size() > 0) {
            for (SysResourceForm form : formList) {
                entityList.add(formToEntity(form));
            }
        }
        return entityList;
    }

    public static SysResourceForm entityToForm(SysResource entity) {
        if (entity != null) {
            SysResourceForm form = new SysResourceForm();
            form.setId(entity.getId());
            form.setName(entity.getName());
            form.setUrl(entity.getUrl());
            form.setOpenMode(entity.getOpenMode());
            form.setDescription(entity.getDescription());
            form.setIcon(entity.getIcon());
            form.setPid(entity.getPid());
            form.setSeq(entity.getSeq());
            form.setStatus(entity.getStatus());
            form.setResourceType(entity.getResourceType());
            form.setIsLeaf(entity.getIsLeaf());
            form.setDelFlag(entity.getDelFlag());
            form.setUpdateTime(dateToString(entity.getUpdateTime()));
            form.setCreateTime(dateToString(entity.getCreateTime()));
            return form;
        }
        return null;
    }

    public static List<SysResourceForm> entityListToFormList(List<SysResource> entityList) {
        List<SysResourceForm> formList = new ArrayList<SysResourceForm>();
        if (entityList != null && entityList.size() > 0) {
            for (SysResource entity : entityList) {
                formList.add(entityToForm(entity));
            }
            return formList;
        }
        return formList;
    }
}
