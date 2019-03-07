package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.SysAuthority;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAuthorityRepository extends BaseRepository<SysAuthority,Long> {

    @Query(value = "select *from sys_authority where id in (:ids)",nativeQuery = true)
    List<SysAuthority> findByIds(@Param("ids") String ids);
}
