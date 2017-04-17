package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.sys.convert.SysOrgConvert;
import me.jinkun.rds.sys.dao.SysOrgMapper;
import me.jinkun.rds.sys.domain.SysOrg;
import me.jinkun.rds.sys.domain.SysOrgExample;
import me.jinkun.rds.sys.service.SysOrgService;
import me.jinkun.rds.sys.web.form.SysOrgForm;
import me.jinkun.rds.common.base.BaseResult;
import me.jinkun.rds.common.base.EUTreeGridResult;
import me.jinkun.rds.common.base.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    SysOrgMapper sysOrgMapper;

    public BaseResult delete(Long id) {
        sysOrgMapper.deleteByPrimaryKey(id);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult deleteByIds(String ids) {
        SysOrgExample example = new SysOrgExample();
        example.createCriteria().andIdIn(idsToList(ids));
        List<SysOrg> orgList = sysOrgMapper.selectByExample(example);
        for (SysOrg org : orgList) {
            org.setDelFlag(1);
            sysOrgMapper.updateByPrimaryKeySelective(org);
        }
        return BaseResult.ok("删除成功");
    }


    @Override
    public BaseResult get(Long id) {
        SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(id);
        return BaseResult.ok("查询成功", SysOrgConvert.entityToForm(sysOrg));
    }

    @Override
    public BaseResult list(SysOrgForm form) {
        SysOrgExample example = new SysOrgExample();
        example.createCriteria().andDelFlagEqualTo(0);
        List<SysOrg> sysOrgList = sysOrgMapper.selectByExample(example);
        return BaseResult.ok("查询成功", SysOrgConvert.entityListToFormList(sysOrgList));
    }

    @Override
    public EUTreeGridResult listPage(SysOrgForm form) {
        SysOrgExample example = new SysOrgExample();
        //设置分页
        example.setStart((form.getPage() - 1) * form.getRows());
        example.setSize(form.getRows());

        //排序
        example.setOrderByClause("seq ASC");

        //查询条件
        if (form != null) {
            SysOrgExample.Criteria criteria = example.createCriteria();
            //其它条件
            criteria.andDelFlagEqualTo(0);
        }

        //查询总记录
        long count = sysOrgMapper.countByExample(example);
        //查询分页列表
        List<SysOrg> sysOrgList = sysOrgMapper.selectPageByExample(example);

        //返回结果
        EUTreeGridResult result = new EUTreeGridResult(count, SysOrgConvert.entityListToFormList(sysOrgList));
        return result;
    }

    @Override
    public List<Tree> tree(SysOrgForm form) {
        SysOrgExample example = new SysOrgExample();
        example.setOrderByClause("seq ASC");
        example.createCriteria().andDelFlagEqualTo(0);
        List<SysOrg> orgList = sysOrgMapper.selectByExample(example);
        return prepareTree(orgList);
    }

    private List<Tree> prepareTree(List<SysOrg> orgList) {
        List<Tree> allTreeList = orgListToTreeList(orgList);
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

    private List<Tree> orgListToTreeList(List<SysOrg> orgList) {
        List<Tree> treeList = new ArrayList<>();
        if (orgList != null && orgList.size() > 0) {
            for (SysOrg org : orgList) {
                treeList.add(orgToTree(org));
            }
        }
        return treeList;
    }

    private Tree orgToTree(SysOrg org) {
        Tree tree = new Tree();
        tree.setId(org.getId());
        tree.setText(org.getName());
        tree.setIconCls(org.getIcon());
        tree.setIsLeaf(org.getIsLeaf());
        tree.setPid(org.getPid());
        return tree;
    }

    @Override
    public BaseResult saveOrUpdate(SysOrgForm form) {
        SysOrg entity = SysOrgConvert.formToEntity(form);

        if (entity.getId() != null) {
            entity.setUpdateTime(new Date());
            sysOrgMapper.updateByPrimaryKeySelective(entity);
        } else {
            entity.setIsLeaf(0);
            entity.setDelFlag(0);
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            sysOrgMapper.insert(entity);
        }

        //更新父机构状态
        if (entity.getPid() != null) {
            SysOrg org = sysOrgMapper.selectByPrimaryKey(entity.getPid());
            org.setIsLeaf(1);
            sysOrgMapper.updateByPrimaryKey(org);
        }
        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult update(SysOrgForm form) {
        SysOrgExample example = new SysOrgExample();
        sysOrgMapper.updateByExample(SysOrgConvert.formToEntity(form), example);
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