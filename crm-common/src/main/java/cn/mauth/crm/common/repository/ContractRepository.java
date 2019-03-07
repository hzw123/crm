package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends BaseRepository<Contract,Long> {

    int countByAccountId(Long accountId);

    @Query(value = "select count(1) from contract where date_format(create_at,'%Y%m%d')=date_format(now(),'%Y%m%d')",nativeQuery = true)
    int countByToDay();
}
