package cn.mauth.crm.common.bean;

import java.io.Serializable;
import java.util.Set;

public class UserBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id=0L;

    private Set<Long> ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }
}
