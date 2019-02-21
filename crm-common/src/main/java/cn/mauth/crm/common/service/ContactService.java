package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Contact;
import cn.mauth.crm.common.repository.ContactRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactService extends BaseService<ContactRepository,Contact>{

    public ContactService(ContactRepository repository) {
        super(repository);
    }

}
