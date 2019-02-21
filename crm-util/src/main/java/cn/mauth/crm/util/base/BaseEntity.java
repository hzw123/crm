package cn.mauth.crm.util.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(columnDefinition = "datetime")
    protected Date crateAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime")
    protected Date updateAt;

    /**创建者*/
    @Column(nullable = false)
    private String creator;

    /**修改者*/
    @Column(nullable = false)
    private String modified;

    /**所有者*/
    @Column(nullable = false)
    private String owner;

    /**备注*/
    @Column(columnDefinition = "text")
    protected String remark;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
