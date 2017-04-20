package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.common.base.BaseResult;
import me.jinkun.rds.common.base.EUDataGridResult;
import me.jinkun.rds.sys.convert.SysRoleConvert;
import me.jinkun.rds.sys.dao.SysRoleMapper;
import me.jinkun.rds.sys.domain.SysRole;
import me.jinkun.rds.sys.domain.SysRoleExample;
import me.jinkun.rds.sys.service.SysRoleResourceService;
import me.jinkun.rds.sys.service.SysRoleService;
import me.jinkun.rds.sys.web.form.SysRoleForm;
import me.jinkun.rds.sys.web.form.SysRoleResourceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleResourceService sysRoleResourceService;

    public BaseResult delete(Long id) {
        //删除关联资源
        sysRoleResourceService.deleteByRoleId(id);

        sysRoleMapper.deleteByPrimaryKey(id);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);

        //删除关联资源
        sysRoleResourceService.deleteByRoleIds(idList);

        //删除角色表
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andIdIn(idList);
        sysRoleMapper.deleteByExample(example);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult get(Long id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        return BaseResult.ok("查询成功", SysRoleConvert.entityToForm(sysRole));
    }

    @Override
    public BaseResult list(SysRoleForm form) {
        SysRoleExample example = new SysRoleExample();
        List<SysRole> sysRoleList = sysRoleMapper.selectByExample(example);
        return BaseResult.ok("查询成功", SysRoleConvert.entityListToFormList(sysRoleList));
    }

    @Override
    public EUDataGridResult listPage(SysRoleForm form) {
        SysRoleExample example = new SysRoleExample();
        //设置分页
        example.setStart((form.getPage() - 1) * form.getRows());
        example.setSize(form.getRows());

        //查询条件
        if (form != null) {
            SysRoleExample.Criteria criteria = example.createCriteria();
            //条件-角色名
            if (form.getName() != null) {
                criteria.andNameLike("%" + form.getName() + "%");
            }

            //其它条件

        }

        //查询总记录
        long count = sysRoleMapper.countByExample(example);
        //查询分页列表
        List<SysRole> sysRoleList = sysRoleMapper.selectPageByExample(example);

        //返回结果
        EUDataGridResult result = new EUDataGridResult(count, SysRoleConvert.entityListToFormList(sysRoleList));
        return result;
    }

    @Override
    public BaseResult getResources(Long id) {
        List<Long> idList = sysRoleResourceService.getResourcesIdsByRoleId(id);
        return BaseResult.ok("请求成功", idList);
    }

    @Override
    public BaseResult saveResources(Long roleId, String ids) {
        //删除以前的数据
        sysRoleResourceService.deleteByRoleId(roleId);

        if (ids != null && !"".equals(ids)) {
            //保存新数据
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                SysRoleResourceForm roleResourceForm = new SysRoleResourceForm();
                roleResourceForm.setRoleId(roleId);
                roleResourceForm.setResourceId(Long.valueOf(idArr[i]));
                sysRoleResourceService.saveOrUpdate(roleResourceForm);
            }
        }
        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult saveOrUpdate(SysRoleForm form) {
        SysRole entity = SysRoleConvert.formToEntity(form);
        if (entity.getId() != null) {
            sysRoleMapper.updateByPrimaryKey(entity);
        } else {
            entity.setStatus(0);
            entity.setDelFlag(0);
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            sysRoleMapper.insert(entity);
        }
        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult update(SysRoleForm form) {
        SysRoleExample example = new SysRoleExample();
        sysRoleMapper.updateByExample(SysRoleConvert.formToEntity(form), example);
        return BaseResult.ok("更新成功");
    }

    private List<Long> idsToList(String ids) {
        String[] id = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            idList.add(Long.parseLong(id[i]));
        }
        return idList;
    }
}