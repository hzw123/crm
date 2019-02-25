package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.bean.OrgUser;
import cn.mauth.crm.common.domain.OrganizeEmployee;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrganizeEmployeeRepository extends BaseRepository<OrganizeEmployee,Long>{

    List<OrganizeEmployee> findByOrOrgId(Long orgId);

    int countByOrgId(Long orgId);

    @Query(value = "delete from organize_employee where org_id=:orgId and user_id=:userId",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByOrgIdAndUserId(@Param("orgId") Long orgId,@Param("userId") Long userId);

    int countByOrgIdAndUserId(Long orgId,Long userId);

    OrganizeEmployee findIdByOrgIdAndUserId(Long orgId,Long userId);

    @Query(value = "delete from organize_employee where org_id=:orgId and user_id in (:userIds)",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByOrgIdAndUserIds(@Param("orgId") Long orgId,@Param("userIds") String userIds);

    void deleteByOrgId(Long orgId);

    @Query(value = "select new cn.mauth.crm.common.bean.OrgUser(u,oe)" +
            "from SysUserInfo u,OrganizeEmployee oe " +
            "where oe.orgId=:orgId " +
            "and u.id=oe.userId")
    List<OrgUser> findUsersByOrgId(@Param("orgId") Long orgId);

}
