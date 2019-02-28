package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.Visit;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends BaseRepository<Visit,Long> {

    int countByAccountId(Long accountId);
}
