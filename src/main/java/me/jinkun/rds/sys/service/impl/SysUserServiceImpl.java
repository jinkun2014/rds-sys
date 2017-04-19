package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.common.shiro.ShiroUser;
import me.jinkun.rds.common.utils.UtilMd5;
import me.jinkun.rds.sys.convert.SysUserConvert;
import me.jinkun.rds.sys.dao.*;
import me.jinkun.rds.sys.domain.*;
import me.jinkun.rds.sys.service.SysUserService;
import me.jinkun.rds.sys.web.form.SysUserForm;
import me.jinkun.rds.common.base.BaseResult;
import me.jinkun.rds.common.base.EUDataGridResult;
import me.jinkun.rds.common.base.Tree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserOrgMapper sysUserOrgMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    SysResourceMapper sysResourceMapper;

    public BaseResult delete(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
        SysUserOrgExample userOrgExample = new SysUserOrgExample();
        userOrgExample.createCriteria().andUserIdEqualTo(id);
        sysUserOrgMapper.deleteByExample(userOrgExample);
        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult deleteByIds(String ids) {
        //删除用户
        List<Long> idList = idsToList(ids);
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdIn(idList);
        sysUserMapper.deleteByExample(example);

        //删除中间表
        SysUserOrgExample userOrgExample = new SysUserOrgExample();
        userOrgExample.createCriteria().andUserIdIn(idList);
        sysUserOrgMapper.deleteByExample(userOrgExample);

        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult get(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        SysUserForm userForm = SysUserConvert.entityToForm(sysUser);

        //查询user-org中间表
        SysUserOrgExample userOrgExample = new SysUserOrgExample();
        userOrgExample.createCriteria().andUserIdEqualTo(id);
        List<SysUserOrg> userOrgList = sysUserOrgMapper.selectByExample(userOrgExample);
        userForm.setOrgId(userOrgList.get(0).getOrgId());

        //查询user-role中间表
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(id);
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(userRoleExample);
        if (userRoleList != null && userRoleList.size() > 0) {
            Long[] ids = new Long[userRoleList.size()];
            for (int i = 0; i < userRoleList.size(); i++) {
                ids[i] = userRoleList.get(i).getRoleId();
            }
            userForm.setRoleId(ids);
        }

        return BaseResult.ok("查询成功", userForm);
    }

    @Override
    public BaseResult list(SysUserForm form) {
        SysUserExample example = new SysUserExample();
        List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
        return BaseResult.ok("查询成功", SysUserConvert.entityListToFormList(sysUserList));
    }

    @Override
    public EUDataGridResult listPage(SysUserForm form) {
        long count = 0;
        EUDataGridResult result = null;

        SysUserExample example = new SysUserExample();

        //查询条件
        if (form != null) {
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo(0);

            //条件-用户名
            if (form.getName() != null) {
                criteria.andNameLike("%" + form.getName() + "%");
            }

            //其它条件
            //如果机构不为空就根据机构去查
            if (form.getOrgId() != null) {
                SysUserOrgExample userOrgExample = new SysUserOrgExample();
                userOrgExample.setStart((form.getPage() - 1) * form.getRows());
                userOrgExample.setSize(form.getRows());
                SysUserOrgExample.Criteria userOrgCriteria = userOrgExample.createCriteria();
                userOrgCriteria.andOrgIdEqualTo(form.getOrgId());

                count = sysUserMapper.countByExample(example);
                List<SysUserOrg> userOrgList = sysUserOrgMapper.selectByExample(userOrgExample);

                if (userOrgList != null && userOrgList.size() > 0) {
                    criteria.andIdIn(getUserIdList(userOrgList));
                } else {
                    return new EUDataGridResult(0, SysUserConvert.entityListToFormList(null));
                }
            } else {
                //设置分页
                example.setStart((form.getPage() - 1) * form.getRows());
                example.setSize(form.getRows());

                //查询总记录
                count = sysUserMapper.countByExample(example);
            }

        }
        //查询分页列表
        List<SysUser> sysUserList = sysUserMapper.selectPageByExample(example);

        //返回结果
        result = new EUDataGridResult(count, SysUserConvert.entityListToFormList(sysUserList));
        return result;
    }

    @Override
    public BaseResult login(SysUserForm form) {
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.getLoginName(), form.getPassword());
        try {
            user.login(token);
            return BaseResult.ok("登录成功");
        } catch (UnknownAccountException e) {
            return BaseResult.fail("账号不存在！");
        } catch (DisabledAccountException e) {
            return BaseResult.fail("账号未启用！");
        } catch (IncorrectCredentialsException e) {
            return BaseResult.fail("密码错误！");
        } catch (Throwable e) {
            return BaseResult.fail("登录失败");
        }
    }

    @Override
    public SysUser getByLoginName(String loginName) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andDelFlagEqualTo(0);
        List<SysUser> userList = sysUserMapper.selectByExample(example);
        if (userList != null && userList.size() == 1) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public BaseResult topMenu() {
        List<Tree> treeList = new ArrayList<>();

        //获取登录用户ID
        Subject subject = SecurityUtils.getSubject();
        ShiroUser principal = (ShiroUser) subject.getPrincipal();
        Long userId = principal.getId();

        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(userRoleExample);
        for (SysUserRole userRole : userRoleList) {
            SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
            roleResourceExample.createCriteria().andRoleIdEqualTo(userRole.getRoleId());
            List<SysRoleResource> roleResourceList = sysRoleResourceMapper.selectByExample(roleResourceExample);
            for (SysRoleResource roleResource : roleResourceList) {
                SysResource resource = sysResourceMapper.selectByPrimaryKey(roleResource.getResourceId());
                if (resource != null && resource.getPid() == null) {
                    Tree tree = resourceToTree(resource);
                    if (!treeList.contains(tree)) {
                        treeList.add(tree);
                    }
                }
            }
        }

        //排序调整菜单顺序
        Collections.sort(treeList, new Comparator<Tree>() {
            @Override
            public int compare(Tree t1, Tree t2) {
                return t1.getSeq() - t2.getSeq();
            }
        });
        return BaseResult.ok("请求成功", treeList);
    }

    @Override
    public List<Tree> menuTree(Long id) {
        List<Tree> treeList = new ArrayList<>();

        Subject subject = SecurityUtils.getSubject();
        ShiroUser principal = (ShiroUser) subject.getPrincipal();
        Long userId = principal.getId();

        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(userRoleExample);
        for (SysUserRole userRole : userRoleList) {
            SysRoleResourceExample roleResourceExample = new SysRoleResourceExample();
            roleResourceExample.createCriteria().andRoleIdEqualTo(userRole.getRoleId());
            List<SysRoleResource> roleResourceList = sysRoleResourceMapper.selectByExample(roleResourceExample);
            for (SysRoleResource roleResource : roleResourceList) {
                SysResourceExample resourceExample = new SysResourceExample();
                resourceExample.createCriteria().andIdEqualTo(roleResource.getResourceId()).andResourceTypeEqualTo(0);//菜单
                List<SysResource> resourceList = sysResourceMapper.selectByExample(resourceExample);
                if (resourceList != null && resourceList.size() > 0) {
                    Tree tree = resourceToTree(resourceList.get(0));
                    if (!treeList.contains(tree)) {
                        treeList.add(tree);
                    }
                }

            }
        }
        List<Tree> childList = new ArrayList<>();
        for (Tree tree : treeList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (id.equals(tree.getPid())) {
                tree.setChildren(prepareTreeChiled(tree.getId(), treeList));
                childList.add(tree);
            }
        }

        //排序调整菜单顺序
        Collections.sort(childList, new Comparator<Tree>() {
            @Override
            public int compare(Tree t1, Tree t2) {
                return t1.getSeq() - t2.getSeq();
            }
        });

        return childList;
    }

    @Override
    public BaseResult logout() {
        Subject user = SecurityUtils.getSubject();
        user.logout();
        return BaseResult.ok("退出成功");
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

    private Tree resourceToTree(SysResource resource) {
        Tree tree = new Tree();
        tree.setId(resource.getId());
        tree.setText(resource.getName());
        tree.setIconCls(resource.getIcon());
        tree.setIsLeaf(resource.getIsLeaf());
        tree.setPid(resource.getPid());
        tree.setAttributes(resource);
        tree.setSeq(resource.getSeq());
        return tree;
    }

    private List<Long> getUserIdList(List<SysUserOrg> userOrgList) {
        List<Long> idList = new ArrayList<>();
        for (SysUserOrg userOrg : userOrgList) {
            idList.add(userOrg.getUserId());
        }
        return idList;
    }

    @Override
    public BaseResult saveOrUpdate(SysUserForm form) {

        SysUser entity = SysUserConvert.formToEntity(form);

        if (form.getId() != null) {
            SysUser oldEntity = sysUserMapper.selectByPrimaryKey(form.getId());
            entity.setUpdateTime(new Date());
            entity.setPassword(UtilMd5.md5(entity.getPassword(), String.valueOf(oldEntity.getCreateTime().getTime())));
            sysUserMapper.updateByPrimaryKeySelective(entity);

            //删除user-org中间表就数据
            SysUserOrgExample userOrgExample = new SysUserOrgExample();
            userOrgExample.createCriteria().andUserIdEqualTo(form.getId());
            sysUserOrgMapper.deleteByExample(userOrgExample);

            //删除user-role中间表就数据
            SysUserRoleExample userRoleExample = new SysUserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(form.getId());
            sysUserRoleMapper.deleteByExample(userRoleExample);

        } else {
            //判断登录名是否存在
            SysUser user = getByLoginName(form.getLoginName());
            if (user != null) {
                BaseResult.fail("登录名已经存在");
            }

            //保存用户
            entity.setDelFlag(0);
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            //用创建时间作为盐给密码加密
            entity.setPassword(UtilMd5.md5(entity.getPassword(), String.valueOf(entity.getCreateTime().getTime())));
            sysUserMapper.insert(entity);
        }

        //保存user-org中间表新数据
        SysUserOrg userOrg = new SysUserOrg();
        userOrg.setUserId(entity.getId());
        userOrg.setOrgId(form.getOrgId());
        sysUserOrgMapper.insert(userOrg);

        //保存user-role中间表
        if (form.getRoleId() != null && form.getRoleId().length > 0) {
            for (Long roleId : form.getRoleId()) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(entity.getId());
                userRole.setRoleId(roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }

        return BaseResult.ok("保存成功");
    }

    @Override
    public BaseResult update(SysUserForm form) {
        SysUserExample example = new SysUserExample();
        sysUserMapper.updateByExample(SysUserConvert.formToEntity(form), example);
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