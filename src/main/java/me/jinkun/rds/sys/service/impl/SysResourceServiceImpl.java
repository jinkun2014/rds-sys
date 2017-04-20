package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.common.base.BaseResult;
import me.jinkun.rds.common.base.EUDataGridResult;
import me.jinkun.rds.common.base.Tree;
import me.jinkun.rds.sys.convert.SysResourceConvert;
import me.jinkun.rds.sys.dao.SysResourceMapper;
import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.domain.SysResourceExample;
import me.jinkun.rds.sys.service.SysResourceService;
import me.jinkun.rds.sys.service.SysRoleResourceService;
import me.jinkun.rds.sys.web.form.SysResourceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    SysResourceMapper sysResourceMapper;

    @Autowired
    SysRoleResourceService sysRoleResourceService;

    public BaseResult delete(Long id) {
        sysResourceMapper.deleteByPrimaryKey(id);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);

        sysRoleResourceService.deleteByResourceIds(idList);

        //删除资源
        SysResourceExample example = new SysResourceExample();
        example.createCriteria().andIdIn(idList);
        sysResourceMapper.deleteByExample(example);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult get(Long id) {
        SysResource sysResource = sysResourceMapper.selectByPrimaryKey(id);
        return BaseResult.ok("查询成功", SysResourceConvert.entityToForm(sysResource));
    }

    @Override
    public BaseResult list(SysResourceForm form) {
        SysResourceExample example = new SysResourceExample();
        List<SysResource> sysResourceList = sysResourceMapper.selectByExample(example);
        return BaseResult.ok("查询成功", SysResourceConvert.entityListToFormList(sysResourceList));
    }

    @Override
    public EUDataGridResult listPage(SysResourceForm form) {
        SysResourceExample example = new SysResourceExample();
        //设置分页
        example.setStart((form.getPage() - 1) * form.getRows());
        example.setSize(form.getRows());

        //排序
        example.setOrderByClause("seq ASC");

        //查询条件
        if (form != null) {
            SysResourceExample.Criteria criteria = example.createCriteria();

            //其它条件
        }

        //查询总记录
        long count = sysResourceMapper.countByExample(example);
        //查询分页列表
        List<SysResource> sysResourceList = sysResourceMapper.selectPageByExample(example);

        //返回结果
        EUDataGridResult result = new EUDataGridResult(count, SysResourceConvert.entityListToFormList(sysResourceList));
        return result;
    }

    @Override
    public List<Tree> tree(SysResourceForm form) {
        SysResourceExample example = new SysResourceExample();
        example.setOrderByClause("seq ASC");
        example.createCriteria().andDelFlagEqualTo(0);
        List<SysResource> resourceList = sysResourceMapper.selectByExample(example);
        return prepareTree(resourceList);
    }


    private List<Tree> prepareTree(List<SysResource> resourceList) {
        List<Tree> allTreeList = resourceListToTreeList(resourceList);
        List<Tree> topList = new ArrayList<>();
        for (Tree tree : allTreeList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (tree.getPid() == null) {
                tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
                topList.add(tree);
            }
        }
        return topList;
    }

    private List<Tree> prepareTreeChiled(Long id, List<Tree> allTreeList) {
        // 子菜单
        List<Tree> childList = new ArrayList<>();
        for (Tree tree : allTreeList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (tree.getPid() != null && tree.getPid().equals(id)) {
                childList.add(tree);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Tree tree : childList) {
            if (tree.getIsLeaf() == 1) {
                tree.setChildren(prepareTreeChiled(tree.getId(), allTreeList));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    private List<Tree> resourceListToTreeList(List<SysResource> resourceList) {
        List<Tree> treeList = new ArrayList<>();
        if (resourceList != null && resourceList.size() > 0) {
            for (SysResource resource : resourceList) {
                treeList.add(resourceToTree(resource));
            }
        }
        return treeList;
    }

    private Tree resourceToTree(SysResource resource) {
        Tree tree = new Tree();
        tree.setId(resource.getId());
        tree.setText(resource.getName());
        tree.setIconCls(resource.getIcon());
        tree.setIsLeaf(resource.getIsLeaf());
        tree.setPid(resource.getPid());
        tree.setAttributes(resource);
        return tree;
    }

    @Override
    public BaseResult saveOrUpdate(SysResourceForm form) {
        SysResource entity = SysResourceConvert.formToEntity(form);

        if (entity.getId() != null) {
            entity.setUpdateTime(new Date());
            sysResourceMapper.updateByPrimaryKeySelective(entity);
        } else {
            entity.setIsLeaf(0);
            entity.setDelFlag(0);
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            sysResourceMapper.insert(entity);
        }

        if (entity.getPid() != null) {
            SysResource resource = sysResourceMapper.selectByPrimaryKey(entity.getPid());
            resource.setIsLeaf(1);
            sysResourceMapper.updateByPrimaryKeySelective(resource);
        }

        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult update(SysResourceForm form) {
        SysResourceExample example = new SysResourceExample();
        sysResourceMapper.updateByExample(SysResourceConvert.formToEntity(form), example);
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