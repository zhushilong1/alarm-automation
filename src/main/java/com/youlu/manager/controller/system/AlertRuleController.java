package com.youlu.manager.controller.system;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youlu.manager.dto.AlertRuleDTO;
import com.youlu.manager.json.Alerts;
import com.youlu.manager.json.Annotations;
import com.youlu.manager.json.JsonRootBean;
import com.youlu.manager.kit.CMDKit;
import com.youlu.manager.message.SendWeChatMsg;
import com.youlu.manager.message.urlData;
import com.youlu.manager.pojo.AlertRule;
import com.youlu.manager.response.PageDataResult;
import com.youlu.manager.service.AlertRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhushilong
 */
@Controller
@RequestMapping("rule")
public class AlertRuleController {

    public static final String CORPID = "ww6f2b44f2a24e3dcb";
    public static final String CORPSECRET = "7n-xhR44ieQZ_F19xhLEd2HpFPuW_u6ifp1mSzMIL8U";
    public static final String TOUSER = "YL024737";
    public static final String TOPARTY = "8776";
    public static final int AGENTID = 1000082;

    public static final String OK = "ok";
    public static final String ZHONGDUAN = "主机异常中断";
    public static final String CIPAN = "磁盘使用率异常";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AlertRuleService alertRuleService;

    /**
     *
     * 功能描述: 跳到系统用户列表
     *
     */
    @RequestMapping("alertRule")
    public String userManage() {
        return "/rule/rule";
    }


    @RequestMapping(value = "alertRuleLists",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public void getUserLists(@RequestBody(required = false) String json) throws IOException {
        System.out.println("json = " + json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JsonRootBean alerts = JSON.toJavaObject(jsonObject, JsonRootBean.class);
        List<Alerts> alerts1 = alerts.getAlerts();
        String summary = "";
        String  ss = "";
        String description = "";
        for (Alerts alerts2 : alerts1) {
            Annotations annotations = alerts2.getAnnotations();
            summary = annotations.getSummary()+"\n"+annotations.getDescription();
            ss = annotations.getSummary()+"\n\n 已恢复";
            description = annotations.getDescription();
        }
        SendWeChatMsg sw = new SendWeChatMsg();
        String token = sw.getToken(CORPID,CORPSECRET);
        String postdata = sw.createpostdata(TOUSER, TOPARTY,"text", AGENTID, "content",summary);
        String resp = sw.post("utf-8", SendWeChatMsg.CONTENT_TYPE,(new urlData()).getSendMessage_Url(), postdata, token);

        JSONObject jsonObject1 = JSONObject.parseObject(resp);
        String errmsg =(String) jsonObject1.get("errmsg");
        System.out.println("resp = " + resp);
        if(OK.equals(errmsg)){
            if(summary.indexOf(ZHONGDUAN)!=-1){
                CMDKit.cmd("cd /usr/lib/systemd/system; systemctl start node_exporter.service",getIps(description));
            }else if(summary.indexOf(CIPAN)!=-1){
                CMDKit.cmd("sh /data/script/Clear_hisfile.sh; sh /data/script/Clear_logs.sh",getIps(description));
            }
            String postdata1 = sw.createpostdata(TOUSER, TOPARTY,"text", AGENTID, "content",ss);
            String resp1 = sw.post("utf-8", SendWeChatMsg.CONTENT_TYPE,(new urlData()).getSendMessage_Url(), postdata1, token);
            System.out.println("resp1 = " + resp1);
        }
    }
    /**
     * 正则提前字符串中的IP地址
     * @param ipString
     * @return
     */
    public static String getIps(String ipString){
        String regEx="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        List<String> ips = new ArrayList<String>();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ipString);
        String result = "";
        while (m.find()) {
           result = m.group();
        }
        return result;
    }

    /**
     *
     * 功能描述: 分页查询用户列表
     *
     */
    @RequestMapping(value = "alertRuleList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ AlertRuleDTO alertRuleDTO) {
        /*logger.info("分页查询用户列表！搜索条件：userSearch：" + userSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = alertRuleService.getUserList(alertRuleDTO, pageNum ,pageSize);
            logger.info("规则列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("规则列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 新增和更新规则
     *
     */
    @RequestMapping(value = "/setRule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setUser(AlertRule alertRule) {
        logger.info("设置规则[新增或更新]！alertRule:" + alertRule);
        Map<String,Object> data = new HashMap();
        if(alertRule.getId() == null){
            data = alertRuleService.addUser(alertRule);
        }else{
            data = alertRuleService.updateUser(alertRule);
        }
        return data;
    }


    /**
     *
     * 功能描述: 删除/恢复 规则
     *
     */
    @RequestMapping(value = "/updateRule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserStatus(@RequestParam("id") Integer id,@RequestParam("status") String status) {
        logger.info("删除/恢复规则！id:" + id+" status:"+status);
        Map<String, Object> data = new HashMap<>();
        if("0".equals(status)){
            //删除规则
            data = alertRuleService.delUser(id,status);
        }else{
            //恢复规则
            data = alertRuleService.recoverUser(id,status);
        }
        return data;
    }


    public static void main(String[] args) {
        HashMap<String, Object> params = new HashMap<>();
        HashMap<Object, Object> services = new HashMap<>();
        services.put("host","192.168.10.102");
        services.put("port","7040");
        ArrayList<Object> list = new ArrayList<>();
        list.add(services);
        params.put("services",list);
        params.put("dplrcdMemo","测试发布");
        params.put("dplrcdVersion","123");
        System.out.println(JSONUtils.toJSONString(params));
        System.out.println(params);
    }


}
