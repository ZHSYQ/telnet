package com.zhs.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class TelnetRole implements Serializable {
    private Long id;

    private String name;

    private String desc;

    private Integer status;

    private Date createdTime;

    private Date updatedTime;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelnetRole sysRole = (TelnetRole) o;
        return Objects.equals(id, sysRole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}