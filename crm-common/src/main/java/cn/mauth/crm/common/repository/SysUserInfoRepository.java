package cn.mauth.crm.common.repository;

import cn.mauth.crm.util.base.BaseRepository;
import cn.mauth.crm.common.domain.SysUserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SysUserInfoRepository extends BaseRepository<SysUserInfo,Long> {

    SysUserInfo findByUserName(String userName);

    @Query(value = "select *from sys_user_info " +
            "where is_delete=0 " +
            "and id in (" +
                "select user_id from sys_user_role " +
                    "where role_id in (" +
                        "select id from sys_role where name=:type" +
                    ")" +
            ")", nativeQuery = true)
    List<SysUserInfo> findAdminList(@Param("type") String type);

    @Query(value = "update sys_user_info set is_delete=1 where id=:id",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteById(@Param("id") Long id);

    @Query(value = "update sys_user_info set is_delete=1 where id in (:ids)",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByIds(@Param("ids") String ids);


}
