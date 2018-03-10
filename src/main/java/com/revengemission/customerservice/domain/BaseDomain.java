
package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseDomain implements Serializable {

    private String id;
    private Date dateCreated;//创建时间
    private Date lastModified;//修改时间
    private Integer recordStatus;//状态
    private Integer version;//更改次数/每次修改+1
    private String remarks;
    private String additionalData;
}
