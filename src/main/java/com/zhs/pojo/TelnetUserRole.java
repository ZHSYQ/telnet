package com.zhs.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TelnetUserRole implements Serializable {
    private Long id;

    private Long userId;

    private Long roleId;

    private Integer status;

    private Date createdTime;

    private Date updatedTime;




}