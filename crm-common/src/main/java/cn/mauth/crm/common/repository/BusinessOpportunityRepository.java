package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessOpportunityRepository extends BaseRepository<BusinessOpportunity,Long> {

    @Query(value = "update business_opportunity set status=:status where id=:id",nativeQuery = true)
    @Modifying
    void updateStatus(@Param("id") Long id,@Param("status") int status);

}
