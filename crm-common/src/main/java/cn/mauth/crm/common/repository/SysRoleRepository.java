package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.SysRole;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SysRoleRepository extends BaseRepository<SysRole,Long> {

    @Query(value = "select id from sys_role where name=:name",nativeQuery = true)
    Long findIdByName(@Param("name") String name);

    @Query(value = "insert into sys_user_role (user_id,role_id) values(:userId,:roleId)",nativeQuery = true)
    @Modifying
    @Transactional
    void addUserRole(@Param("userId") Long userId,@Param("roleId") Long roleId);

    @Query(value = "select count(1) from sys_user_role where user_id=:userId and role_id=:roleId",nativeQuery = true)
    int countByUserAndRole(@Param("userId") Long userId,@Param("roleId") Long roleId);
}
