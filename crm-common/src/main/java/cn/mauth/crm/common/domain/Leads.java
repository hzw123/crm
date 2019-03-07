package cn.mauth.crm.common.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 线索
 */
@Entity
public class Leads implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "datetime")
    private Date createAt;

    /**机构Id*/
    private Long orgId;

    /***/
    private String code;

    /**电话号*/
    private String phone;

    /**留言*/
    private String leaveWord;


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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLeaveWord() {
        return leaveWord;
    }

    public void setLeaveWord(String leaveWord) {
        this.leaveWord = leaveWord;
    }
}
