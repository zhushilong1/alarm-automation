package com.youlu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youlu.manager.common.utils.DateUtils;
import com.youlu.manager.dao.AlertRuleMapper;
import com.youlu.manager.dto.AdminUserDTO;
import com.youlu.manager.dto.AlertRuleDTO;
import com.youlu.manager.dto.UserSearchDTO;
import com.youlu.manager.pojo.AlertRule;
import com.youlu.manager.pojo.BaseAdminUser;
import com.youlu.manager.response.PageDataResult;
import com.youlu.manager.service.AlertRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhushilong
 */
@Service
public class AlertRuleServiceImpl implements AlertRuleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AlertRuleMapper alertRuleMapper;

    @Override
    public PageDataResult getUserList(AlertRuleDTO alertRuleDTO, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<AlertRule> baseAdminUsers = alertRuleMapper.getUserList(alertRuleDTO);
        PageHelper.startPage(pageNum, pageSize);
        if(baseAdminUsers.size() != 0){
            PageInfo<AlertRule> pageInfo = new PageInfo<>(baseAdminUsers);
            pageDataResult.setList(baseAdminUsers);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> updateUser(AlertRule alertRule) {
        Map<String,Object> data = new HashMap();
        alertRule.setUpdateTime(DateUtils.getCurrentDate());
        int result = alertRuleMapper.updateByPrimaryKeySelective(alertRule);
        if(result == 0){
            data.put("code",0);
            data.put("msg","修改失败！");
            logger.error("规则[修改]，结果=修改失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","修改成功！");
        logger.info("规则[修改]，结果=修改成功！");
        return data;
    }

    @Override
    public Map<String, Object> addUser(AlertRule alertRule) {
        Map<String,Object> data = new HashMap();
        alertRule.setCreateTime(DateUtils.getCurrentDate());
        int result = alertRuleMapper.insertSelective(alertRule);
        if(result == 0){
            data.put("code",0);
            data.put("msg","新增失败！");
            logger.error("规则[新增]，结果=新增失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","新增成功！");
        logger.info("规则[新增]，结果=新增成功！");
        return data;
    }

    @Override
    public Map<String, Object> delUser(Integer id, String status) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除用户
            int result = alertRuleMapper.updateUserStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除规则失败");
                logger.error("删除规则失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除规则成功");
            logger.info("删除规则成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除规则异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> recoverUser(Integer id, String status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = alertRuleMapper.updateUserStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","恢复规则失败");
            }
            data.put("code",1);
            data.put("msg","恢复规则成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("恢复规则异常！", e);
        }
        return data;
    }


//    @Override
//    public int deleteByPrimaryKey(Integer id) {
//        return alertRuleMapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public int insert(AlertRule record) {
//        return alertRuleMapper.insert(record);
//    }
//
//    @Override
//    public int insertSelective(AlertRule record) {
//        return alertRuleMapper.insertSelective(record);
//    }
//
//    @Override
//    public AlertRule selectByPrimaryKey(Integer id) {
//        return alertRuleMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(AlertRule record) {
//        return alertRuleMapper.updateByPrimaryKeySelective(record);
//    }
//
//    @Override
//    public int updateByPrimaryKey(AlertRule record) {
//        return alertRuleMapper.updateByPrimaryKey(record);
//    }
}
