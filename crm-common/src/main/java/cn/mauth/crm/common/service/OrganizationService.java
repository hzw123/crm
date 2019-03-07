package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.OrgUser;
import cn.mauth.crm.common.domain.Organization;
import cn.mauth.crm.common.domain.OrganizeEmployee;
import cn.mauth.crm.common.repository.OrganizationRepository;
import cn.mauth.crm.common.repository.OrganizeEmployeeRepository;
import cn.mauth.crm.common.repository.SysRoleRepository;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.util.common.Constants;
import cn.mauth.crm.util.common.PageUtil;
import cn.mauth.crm.util.common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrganizationService extends BaseService<OrganizationRepository,Organization>{

    @Autowired
    private OrganizeEmployeeRepository organizeEmployeeRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    public OrganizationService(OrganizationRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<Organization> findAll(Organization org) {
        return repository.findAll(this.specification(org));
    }

    public Page<Organization> page(Organization org,Pageable pageable) {
        return repository.findAll(this.specification(org), PageUtil.getPageable(pageable));
    }

    private Specification<Organization> specification(Organization org){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(org.getName()))
                list.add(cb.equal(root.get("name"),org.getName()));

            if(StringUtils.isNotEmpty(org.getCode()))
                list.add(cb.equal(root.get("code"),org.getCode()));

            if(StringUtils.isNotEmpty(org.getCreatorId()))
                list.add(cb.equal(root.get("creatorId"),org.getCreatorId()));

            if(StringUtils.isNotEmpty(org.getModifiedId()))
                list.add(cb.equal(root.get("modifiedId"),org.getModifiedId()));

            if(StringUtils.isNotEmpty(org.getOwnerId()))
                list.add(cb.equal(root.get("ownerId"),org.getOwnerId()));

            if(org.getStatus()!=-1)
                list.add(cb.equal(root.get("Status"),org.getStatus()));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }


    public Organization findByCode(String code) {
        return repository.findByCode(code);
    }

    /**
     * 添加组织下机构多个用户
     * @param orgId
     * @param users
     * @return
     */
    @Transactional
    public boolean addUsers(Long orgId,Set<Long> users){

        if(repository.findById(orgId).get()==null){
            return false;
        }

        if(organizeEmployeeRepository.countByOrgId(orgId)>0){
            organizeEmployeeRepository.findByOrOrgId(orgId).forEach(r->{
                for (Long id:users) {
                    if(r.getId()==id){
                        users.remove(id);
                        break;
                    }
                }
            });
        }

        List<OrganizeEmployee> list=new ArrayList<>();

        OrganizeEmployee organizeEmployee=null;

        for (Long id:users) {
           organizeEmployee=new OrganizeEmployee();
           organizeEmployee.setOrgId(orgId);
           organizeEmployee.setUserId(id);
        }

        if(list.size()>0){
            organizeEmployeeRepository.saveAll(list);
        }
        return true;
    }


    /**
     * 添加组织下机构单个用户
     * @param organizeEmployee
     * @return
     */
    @Transactional
    public void addUser(OrganizeEmployee organizeEmployee){
        Long orgId=organizeEmployee.getOrgId();

        Long userId=organizeEmployee.getUserId();

        if(organizeEmployeeRepository.countByOrgIdAndUserId(orgId, userId)>0){

            OrganizeEmployee old=organizeEmployeeRepository.findIdByOrgIdAndUserId(orgId,userId);

            organizeEmployee.setId(old.getId());

            organizeEmployee.setCrateAt(old.getCrateAt());
        }

        Long roleId=sysRoleRepository.findIdByName(Constants.MANAGEER);

        if(sysRoleRepository.countByUserAndRole(userId,roleId)==0){
            sysRoleRepository.addUserRole(userId,roleId);
        }

        organizeEmployeeRepository.save(organizeEmployee);
    }

    /**
     * 移除机构下单个用户
     * @param orgId
     * @param userId
     */
    public void removeUser(Long orgId,Long userId){
        organizeEmployeeRepository.deleteByOrgIdAndUserId(orgId,userId);
    }

    /**
     * 移除机构下多个用户
     * @param orgId
     * @param set
     */
    public void removeUser(Long orgId,Set<Long> set){
        StringBuffer sb=new StringBuffer();

        set.forEach(id->{
            sb.append(id);
            sb.append(",");
        });

        String userIds=sb.substring(0,sb.length()-1);

        organizeEmployeeRepository.deleteByOrgIdAndUserIds(orgId,userIds);
    }

    /**
     * 修改机构下的用户
     * @param organizeEmployee
     */
    public void updateOrgUser(OrganizeEmployee organizeEmployee){

        OrganizeEmployee old=organizeEmployeeRepository.findById(organizeEmployee.getId()).get();

        organizeEmployee.setCrateAt(old.getCrateAt());

        organizeEmployeeRepository.save(organizeEmployee);
    }

    public Result removeById(Long id) {
        if(organizeEmployeeRepository.countByOrgId(id)>0){
            Result.error("该组织下还有用户");
        }

        repository.deleteById(id);

        return Result.success("删除成功");
    }

    /**
     * 查询机构下的用户
     * @param orgId
     * @return
     */
    public List<OrgUser> findUserByOrg(Long orgId){
        return organizeEmployeeRepository.findUsersByOrgId(orgId);
    }

}
