package com.youlu.manager.service;

import com.youlu.manager.pojo.BaseAdminUser;
import com.youlu.manager.dto.UserSearchDTO;
import com.youlu.manager.response.PageDataResult;

import java.util.Map;


public interface AdminUserService {

    PageDataResult getUserList(UserSearchDTO userSearch, Integer pageNum, Integer pageSize);

    Map<String,Object> addUser(BaseAdminUser user);

    Map<String,Object> updateUser(BaseAdminUser user);

    BaseAdminUser getUserById(Integer id);

    BaseAdminUser findByUserName(String userName);

    int updatePwd(String userName,String password);

    Map<String, Object> delUser(Integer id,Integer status);

    Map<String, Object> recoverUser(Integer id,Integer status);
}
