package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.Contact;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends BaseRepository<Contact,Long> {

}
