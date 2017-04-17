package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.sys.convert.SysUserOrgConvert;
import me.jinkun.rds.sys.dao.SysUserOrgMapper;
import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.domain.SysUserOrgExample;
import me.jinkun.rds.sys.service.SysUserOrgService;
import me.jinkun.rds.sys.web.form.SysUserOrgForm;
import me.jinkun.rds.common.base.BaseResult;
import me.jinkun.rds.common.base.EUDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysUserOrgServiceImpl implements SysUserOrgService {

    @Autowired
    SysUserOrgMapper sysUserOrgMapper;

    public BaseResult delete(Long id) {
        sysUserOrgMapper.deleteByPrimaryKey(id);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult deleteByIds(String ids) {
        SysUserOrgExample example = new SysUserOrgExample();
        example.createCriteria().andIdIn(idsToList(ids));
        sysUserOrgMapper.deleteByExample(example);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult get(Long id) {
        SysUserOrg sysUserOrg = sysUserOrgMapper.selectByPrimaryKey(id);
        return BaseResult.ok("查询成功", SysUserOrgConvert.entityToForm(sysUserOrg));
    }

    @Override
    public BaseResult list(SysUserOrgForm form) {
        SysUserOrgExample example = new SysUserOrgExample();
        List<SysUserOrg> sysUserOrgList = sysUserOrgMapper.selectByExample(example);
        return BaseResult.ok("查询成功", SysUserOrgConvert.entityListToFormList(sysUserOrgList));
    }

    @Override
    public EUDataGridResult listPage(SysUserOrgForm form) {
        SysUserOrgExample example = new SysUserOrgExample();
        //设置分页
        example.setStart((form.getPage() - 1) * form.getRows());
        example.setSize(form.getRows());

        //查询条件
        if (form != null) {
            SysUserOrgExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysUserOrgMapper.countByExample(example);
        //查询分页列表
        List<SysUserOrg> sysUserOrgList = sysUserOrgMapper.selectPageByExample(example);

        //返回结果
        EUDataGridResult result = new EUDataGridResult(count, SysUserOrgConvert.entityListToFormList(sysUserOrgList));
        return result;
    }

    @Override
    public BaseResult saveOrUpdate(SysUserOrgForm form) {
        SysUserOrg entity = SysUserOrgConvert.formToEntity(form);
        if (entity.getId() != null) {
            sysUserOrgMapper.updateByPrimaryKey(entity);
        } else {
            sysUserOrgMapper.insert(entity);
        }
        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult update(SysUserOrgForm form) {
        SysUserOrgExample example = new SysUserOrgExample();
        sysUserOrgMapper.updateByExample(SysUserOrgConvert.formToEntity(form), example);
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