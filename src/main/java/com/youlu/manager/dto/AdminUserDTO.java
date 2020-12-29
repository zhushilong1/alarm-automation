package com.youlu.manager.dto;

;import lombok.Data;

@Data
public class AdminUserDTO {

    private Integer id;

    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String roleName;

    private String userPhone;


    private String regTime;


    private Integer userStatus;

}
