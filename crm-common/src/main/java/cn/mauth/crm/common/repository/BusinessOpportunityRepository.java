package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessOpportunityRepository extends BaseRepository<BusinessOpportunity,Long> {

    @Query(value = "update business_opportunity set status=:status where id=:id",nativeQuery = true)
    @Modifying
    void updateStatus(@Param("id") Long id,@Param("status") int status);

    int countByAccountId(Long accountId);

    @Query(value = "select " +
            "count(1) as a," +
            "sum(bus.amount) as at," +
            "sum(if(bus.status=1,1,0)) as b," +
            "sum(if(bus.status=1,bus.amount,0)) as bt," +
            "sum(if(bus.status=-1,1,0)) as f " +
            "from business_opportunity bus " +
            "where bus.org_id=:orgId " +
            "and date_format(bus.create_at,:format)=:timeStr",nativeQuery = true)
    String statisticsFromOrg(@Param("format") String format, @Param("timeStr") String timeStr,@Param("orgId") Long orgId);

    @Query(value = "select " +
            "count(1) as a," +
            "sum(bus.amount) as at," +
            "sum(if(bus.status=1,1,0)) as b," +
            "sum(if(bus.status=1,bus.amount,0)) as bt," +
            "sum(if(bus.status=-1,1,0)) as f " +
            "from business_opportunity bus " +
            "where bus.owner_id=:ownerId " +
            "and date_format(bus.create_at,:format)=:timeStr",nativeQuery = true)
    String statisticsFromOwnerId(@Param("format") String format, @Param("timeStr") String timeStr,@Param("ownerId") String ownerId);


    @Query(value = "select " +
            "sg.name as name," +
            "sg.sort as sort," +
            "count(bus.id) as total," +
            "sum(bus.amount) as totalAmount " +
            "from business_opportunity bus,stage sg " +
            "where bus.stage_id=sg.id " +
            "and date_format(bus.create_at,:format)=:timeStr " +
            "and bus.org_id=:orgId " +
            "and sg.org_id=:orgId " +
            "GROUP by sg.name,sg.sort " +
            "ORDER by sort ASC ",nativeQuery = true)
    List<Object[]> statisticsStage(@Param("format") String format, @Param("timeStr") String timeStr, @Param("orgId") Long orgId);

    @Query(value = "select " +
            "sg.name as name," +
            "sg.sort as sort," +
            "count(bus.id) as total," +
            "sum(bus.amount) as totalAmount " +
            "from business_opportunity bus,stage sg " +
            "where bus.stage_id=sg.id " +
            "and date_format(bus.create_at,:format)=:timeStr " +
            "and bus.owner_id=:ownerId " +
            "and sg.org_id=:orgId " +
            "GROUP by sg.name,sg.sort " +
            "ORDER by sort ASC ",nativeQuery = true)
    List<Object[]> statisticsStage(@Param("format") String format, @Param("timeStr") String timeStr,@Param("orgId") Long orgId,@Param("ownerId") String ownerId);
}
