package com.zhs.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class TtUser implements Serializable {


    private Integer id;

    @NotNull(message="用户名不能为空")
    @Length(min=1,max=12,message = "用户名长度在1-12之间")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;


    private Integer enable;

    @Pattern(regexp="^1(3|4|5|7|8)\\d{9}$",message="手机号码格式错误")
    private String phone;

    //@Pattern( regexp="(^\\\\d{18}$)|(^\\\\d{15}$)",message = "身份证号码不正确")
    private String idcard;

    @NotNull(message = "真实姓名不能为空")
    private String realname;

    private Integer sex;

    private String salt;

    //@Pattern( regexp="^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$",message = "邮箱格式不正确")
    private String email;

    private String address;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updatetime;

    private String ext1;

    private String ext2;


}