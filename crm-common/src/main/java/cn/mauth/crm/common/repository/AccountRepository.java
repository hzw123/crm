package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.Account;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account,Long> {

}
