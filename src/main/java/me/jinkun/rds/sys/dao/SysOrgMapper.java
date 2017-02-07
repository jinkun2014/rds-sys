package me.jinkun.rds.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import me.jinkun.rds.sys.domain.SysOrg;
import me.jinkun.rds.sys.domain.SysOrgExample;
import java.util.Date;
import java.util.Date;


/**
 * @Description: 组织机构,数据库表为： sys_org<br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public interface SysOrgMapper {
    long countByExample(SysOrgExample example);

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    List<SysOrg> selectByExample(SysOrgExample example);

    List<SysOrg> selectPageByExample(SysOrgExample example);

    SysOrg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}