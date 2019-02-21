package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends BaseRepository<Contract,Long> {
}
