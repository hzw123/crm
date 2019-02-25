package cn.mauth.crm.common.domain;

import cn.mauth.crm.util.enums.StateEnum;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**阶段*/
@Entity
public class Stage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(columnDefinition = "datetime")
    private Date createAt;

    /**阶段名称*/
    @Column(nullable = false,length = 100)
    private String name;

    /**类型*/
    private StateEnum type;

    /**组织ID*/
    private Long orgId;

    /**排序*/
    private int sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateEnum getType() {
        return type;
    }

    public void setType(StateEnum type) {
        this.type = type;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
