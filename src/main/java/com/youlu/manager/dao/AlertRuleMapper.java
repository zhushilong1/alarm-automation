package com.youlu.manager.dao;

import com.youlu.manager.dto.AdminUserDTO;
import com.youlu.manager.dto.AlertRuleDTO;
import com.youlu.manager.dto.UserSearchDTO;
import com.youlu.manager.pojo.AlertRule;
import com.youlu.manager.pojo.BaseAdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * @author zhushilong
 */
@Repository
public interface AlertRuleMapper extends MyMapper<AlertRule> {

    List<AlertRule> getUserList(AlertRuleDTO alertRuleDTO);

    int updateUserStatus(@Param("id") Integer id,@Param("status") String status);
//    int deleteByPrimaryKey(Integer id);
//
//    @Override
//    int insert(AlertRule record);
//
//    int insertSelective(AlertRule record);
//
//    AlertRule selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(AlertRule record);
//
//    int updateByPrimaryKey(AlertRule record);
}