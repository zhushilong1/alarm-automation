package com.youlu.manager.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youlu.manager.json.Alerts;
import com.youlu.manager.json.Annotations;
import com.youlu.manager.json.JsonRootBean;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhushilong
 */
public class SendWeChatMsg {
        private CloseableHttpClient httpClient;
        private HttpPost httpPost;
        private HttpGet httpGet;
        public static final String CONTENT_TYPE = "Content-Type";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        private static Gson gson = new Gson();


        /**
         * 微信授权请求，GET类型，获取授权响应，用于其他方法截取token
         * @param url
         * @return String 授权响应内容
         * @throws IOException
         */
        protected String toAuth(String url) throws IOException {

            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String resp;
            try {
                HttpEntity entity = response.getEntity();
                resp = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            LoggerFactory.getLogger(getClass()).info(" resp:{}", resp);
            return resp;
        }

        /**
         */
        public String getToken(String corpid, String corpsecret) throws IOException {
            SendWeChatMsg sw = new SendWeChatMsg();
            urlData uData = new urlData();
            uData.setGet_Token_Url(corpid,corpsecret);
            String resp = sw.toAuth(uData.getGet_Token_Url());

            Map<String, Object> map = gson.fromJson(resp,
                    new TypeToken<Map<String, Object>>() {
                    }.getType());
            return map.get("access_token").toString();
        }


        /**
         * @Title:创建微信发送请求post数据
         * @return String
         */
        public String createpostdata(String touser, String toparty, String msgtype,
                                     int application_id, String contentKey, String contentValue) {
            weChatData wcd = new weChatData();
            wcd.setTouser(touser);
            wcd.setToparty(toparty);
            wcd.setAgentid(application_id);
            wcd.setMsgtype(msgtype);
            Map<Object, Object> content = new HashMap<Object, Object>();
            content.put(contentKey,contentValue+"\n--------\n"+df.format(new Date()));
            wcd.setText(content);
            return gson.toJson(wcd);
        }

        /**
         * @Title  创建微信发送请求post实体
         * @return String
         */
        public String post(String charset, String contentType, String url,
                           String data,String token) throws IOException {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            httpPost = new HttpPost(url+token);
            httpPost.setHeader(CONTENT_TYPE, contentType);
            httpPost.setEntity(new StringEntity(data, charset));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String resp;
            try {
                HttpEntity entity = response.getEntity();
                resp = EntityUtils.toString(entity, charset);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            LoggerFactory.getLogger(getClass()).info(
                    "call [{}], param:{}, resp:{}", url, data, resp);
            return resp;
        }
}

