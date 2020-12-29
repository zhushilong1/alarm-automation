package com.youlu.manager.service;

import com.youlu.manager.dto.AdminUserDTO;
import com.youlu.manager.dto.AlertRuleDTO;
import com.youlu.manager.dto.UserSearchDTO;
import com.youlu.manager.pojo.AlertRule;
import com.youlu.manager.pojo.BaseAdminUser;
import com.youlu.manager.response.PageDataResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AlertRuleService {

    PageDataResult getUserList(AlertRuleDTO alertRuleDTO, Integer pageNum, Integer pageSize);

//    int deleteByPrimaryKey(Integer id);
//
//    int insert(AlertRule record);
//
//    int insertSelective(AlertRule record);
//
//    AlertRule selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(AlertRule record);
//
//    int updateByPrimaryKey(AlertRule record);


    Map<String,Object> updateUser(AlertRule record);

    Map<String, Object> addUser(AlertRule alertRule);

    Map<String, Object> delUser(Integer id,String status);

    Map<String, Object> recoverUser(Integer id,String status);
}
