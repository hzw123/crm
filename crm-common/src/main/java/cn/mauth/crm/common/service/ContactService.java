package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Contact;
import cn.mauth.crm.common.repository.ContactRepository;
import cn.mauth.crm.util.base.BaseService;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService extends BaseService<ContactRepository,Contact>{

    public ContactService(ContactRepository repository) {
        super(repository);
    }

    public List<Contact> findAll(Contact contact) {
        return repository.findAll(this.specification(contact));
    }

    public Page<Contact> page(Contact contact,Pageable pageable) {
        return repository.findAll(this.specification(contact),PageUtil.getPageable(pageable));
    }

    private Specification<Contact> specification(Contact contact){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(contact.getAccountId()!=null&&contact.getAccountId()>0)
                list.add(cb.equal(root.get("accountId"),contact.getAccountId()));

            if(StringUtils.isNotEmpty(contact.getName()))
                list.add(cb.equal(root.get("name"),contact.getName()));

            if(StringUtils.isNotEmpty(contact.getDepartment()))
                list.add(cb.equal(root.get("department"),contact.getDepartment()));

            if(StringUtils.isNotEmpty(contact.getJob()))
                list.add(cb.equal(root.get("job"),contact.getJob()));

            if(StringUtils.isNotEmpty(contact.getSchool()))
                list.add(cb.equal(root.get("school"),contact.getSchool()));

            if(StringUtils.isNotEmpty(contact.getSource()))
                list.add(cb.equal(root.get("source"),contact.getSource()));

            if(StringUtils.isNotEmpty(contact.getSuperior()))
                list.add(cb.equal(root.get("superior"),contact.getSuperior()));


            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
