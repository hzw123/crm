package cn.mauth.crm.common.dao;

import cn.mauth.crm.util.base.BaseDao;
import cn.mauth.crm.common.domain.SysUserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserInfoDao extends BaseDao<SysUserInfo,Long> {

    SysUserInfo findByUserName(String userName);

    @Query(value = "",nativeQuery = true)
    List<SysUserInfo> findAdminList(@Param("type") String type);
}
