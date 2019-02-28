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

    int countByAccountId(Long accountId);

    @Query(value = "select " +
            "count(1) as a," +
            "sum(if(bus.status=1,1,0)) as b," +
            "sum(if(bus.status=-1,1,0)) as c " +
            "from business_opportunity bus " +
            "where date_format(bus.create_at,:format)=:dayStr",nativeQuery = true)
    String statistics(@Param("format") String format, @Param("dayStr") String dayStr);
}
