package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.sys.convert.SysUserRoleConvert;
import me.jinkun.rds.sys.dao.SysUserRoleMapper;
import me.jinkun.rds.sys.domain.SysUserRole;
import me.jinkun.rds.sys.domain.SysUserRoleExample;
import me.jinkun.rds.sys.service.SysUserRoleService;
import me.jinkun.rds.sys.web.form.SysUserRoleForm;
import me.jinkun.rds.common.base.BaseResult;
import me.jinkun.rds.common.base.EUDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    public BaseResult delete(Long id) {
        sysUserRoleMapper.deleteByPrimaryKey(id);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andIdIn(idList);
        sysUserRoleMapper.deleteByExample(example);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult get(Long id) {
        SysUserRole sysUserRole = sysUserRoleMapper.selectByPrimaryKey(id);
        return BaseResult.ok("查询成功", SysUserRoleConvert.entityToForm(sysUserRole));
    }

    @Override
    public BaseResult list(SysUserRoleForm form) {
        SysUserRoleExample example = new SysUserRoleExample();
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectByExample(example);
        return BaseResult.ok("查询成功", SysUserRoleConvert.entityListToFormList(sysUserRoleList));
    }

    @Override
    public EUDataGridResult listPage(SysUserRoleForm form) {
        SysUserRoleExample example = new SysUserRoleExample();
        //设置分页
        example.setStart((form.getPage() - 1) * form.getRows());
        example.setSize(form.getRows());

        //查询条件
        if (form != null) {
            SysUserRoleExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysUserRoleMapper.countByExample(example);
        //查询分页列表
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectPageByExample(example);

        //返回结果
        EUDataGridResult result = new EUDataGridResult(count, SysUserRoleConvert.entityListToFormList(sysUserRoleList));
        return result;
    }

    @Override
    public BaseResult saveOrUpdate(SysUserRoleForm form) {
        SysUserRole entity = SysUserRoleConvert.formToEntity(form);
        if (entity.getId() != null) {
            sysUserRoleMapper.updateByPrimaryKey(entity);
        } else {
            sysUserRoleMapper.insert(entity);
        }
        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult update(SysUserRoleForm form) {
        SysUserRoleExample example = new SysUserRoleExample();
        sysUserRoleMapper.updateByExample(SysUserRoleConvert.formToEntity(form), example);
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