package cn.mauth.crm.common.repository;

import cn.mauth.crm.common.domain.Organization;
import cn.mauth.crm.util.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends BaseRepository<Organization,Long> {

    Organization findByCode(String code);
}
