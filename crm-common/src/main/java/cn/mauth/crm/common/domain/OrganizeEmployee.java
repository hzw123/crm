package cn.mauth.crm.common.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 机构下的员工
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrganizeEmployee implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(columnDefinition = "datetime")
    private Date crateAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime")
    private Date updateAt;

    /**机构Id*/
    @Column(nullable = false)
    private Long orgId;

    /**机构下员工Id*/
    @Column(nullable = false)
    private Long userId;

    /**是否管理员*/
    private boolean isAdmin;

    /**是否主管*/
    private boolean isManager;

    /**状态*/
    private int status;

    /**主管Id*/
    private Long parentId;

    public OrganizeEmployee() {
    }

    public OrganizeEmployee(Long orgId, Long userId) {
        this.orgId = orgId;
        this.userId = userId;
    }

    public OrganizeEmployee(Long orgId, Long userId, boolean isManager) {
        this.orgId = orgId;
        this.userId = userId;
        this.isManager = isManager;
    }

    public OrganizeEmployee(Long orgId, Long userId, boolean isAdmin, boolean isManager, int status, Long parentId) {
        this.orgId = orgId;
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.isManager = isManager;
        this.status = status;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCrateAt() {
        return crateAt;
    }

    public void setCrateAt(Date crateAt) {
        this.crateAt = crateAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
