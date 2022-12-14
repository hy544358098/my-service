package com.crc.business.oa.model.vo.request;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 业务demo vo
 *
 * @outhor LUWEIMIAO1
 * @create 2019-06-14 17:53
 */
public class BusinessVo implements Serializable {

    private static final long serialVersionUID= -8682427724266898815L;

    private Long id;
    private String name;
    private Date createDate;
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
