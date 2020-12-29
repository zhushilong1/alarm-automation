import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.jupiter.api.Test
    public void test4() throws IOException, InterruptedException {
        //        send_weChatMsg sw = new send_weChatMsg();
//        try {
//            String token = sw.getToken("ww6f2b44f2a24e3dcb","7n-xhR44ieQZ_F19xhLEd2HpFPuW_u6ifp1mSzMIL8U");
//            String postdata = sw.createpostdata("YL024737", "8776","text", 1000082, "content","This alert Email come from IapppayBJQA");
//            String resp = sw.post("utf-8", send_weChatMsg.CONTENT_TYPE,(new urlData()).getSendMessage_Url(), postdata, token);
//            System.out.println("获取到的token======>" + token);
//            System.out.println("请求数据======>" + postdata);
//            System.out.println("发送微信的响应数据======>" + resp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String  json = "{\"receiver\":\"jiekou\",\"status\":\"firing\",\"alerts\":[{\"status\":\"firing\",\"labels\":{\"alertname\":\"server_status\",\"appname\":\"监控主机106.87\",\"group\":\"linux\",\"instance\":\"172.16.106.87:9100\",\"job\":\"监控主机106.87\",\"name\":\"监控主机106.87\",\"severity\":\"critical\"},\"annotations\":{\"description\":\"172.16.106.87:9100: 主机异常中断\",\"summary\":\"机器 监控主机106.87: 异常\",\"value\":\"0\"},\"startsAt\":\"2020-12-08T07:47:29.658439261Z\",\"endsAt\":\"0001-01-01T00:00:00Z\",\"generatorURL\":\"http://jenkins:9090/graph?g0.expr=up%7Bgroup%3D%22linux%22%7D+%3D%3D+0\\u0026g0.tab=1\",\"fingerprint\":\"c16b9ada5208c8aa\"}],\"groupLabels\":{\"alertname\":\"server_status\"},\"commonLabels\":{\"alertname\":\"server_status\",\"appname\":\"监控主机106.87\",\"group\":\"linux\",\"instance\":\"172.16.106.87:9100\",\"job\":\"监控主机106.87\",\"name\":\"监控主机106.87\",\"severity\":\"critical\"},\"commonAnnotations\":{\"description\":\"172.16.106.87:9100: 主机异常中断\",\"summary\":\"机器 监控主机106.87: 异常\",\"value\":\"0\"},\"externalURL\":\"http://jenkins:9093\",\"version\":\"4\",\"groupKey\":\"{}/{severity=~\\\"^(?:critical)$\\\"}:{alertname=\\\"server_status\\\"}\",\"truncatedAlerts\":0}";
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        JsonRootBean alerts = JSON.toJavaObject(jsonObject, JsonRootBean.class);
//        List<Alerts> alerts1 = alerts.getAlerts();
//        for (Alerts alerts2 : alerts1) {
//            Annotations annotations = alerts2.getAnnotations();
//            String summary = annotations.getSummary()+"\n"+annotations.getDescription();
//            System.out.println(summary);
//        }

    }


    public static Map<String,Object> test(String ip,int port,String username,String password) throws IOException {
        //指明连接主机的IP地址
        Connection conn = new Connection(ip,port);
        conn.connect();
        boolean isconn = conn.authenticateWithPassword(username, password);
        Map<String,Object> map = new HashMap<>();
        map.put("boolean",isconn);
        map.put("conn",conn);
        return map;
    }

    public static void main(String[] args) throws IOException {

        Map<String, Object> root = test("116.62.42.252", 11022, "root", "nhYjdk05vYO4p^I");
        boolean isconn = (boolean)root.get("boolean");
        Connection conn = (Connection)root.get("conn");
        Session ssh = null;
        try {
            if (!isconn) {
                System.out.println("用户名称或者是密码不正确");
            }else {
                System.out.println("已经连接OK");

                //以下是linux的示例
                //将本地conf/server_start.sh传输到远程主机的/opt/pg944/目录下
//                SCPClient clt = conn.createSCPClient();
//                clt.put("conf/server_start.sh", "/opt/pg944/");

                //执行命令
                ssh = conn.openSession();
                ssh.execCommand("sh ceshi.sh");
//                ssh.execCommand("cd /usr/lib/systemd/system");
//                ssh.execCommand("systemctl start node_exporter.service");
                //只允许使用一行命令，即ssh对象只能使用一次execCommand这个方法，多次使用则会出现异常.
                //使用多个命令用分号隔开
//                ssh.execCommand("sshpass -p rFUWOV$ss#or ssh root@172.16.106.87; ls");
//                ssh.execCommand("cd /data;ls");

                /* 执行windows系统命令的示例
                Session sess = conn.openSession();
                sess.execCommand("ipconfig");
                */

                //将Terminal屏幕上的文字全部打印出来
                InputStream is = new StreamGobbler(ssh.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    String line = brs.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            //连接的Session和Connection对象都需要关闭
            if(ssh!=null) {
                ssh.close();
            }
            if(conn!=null) {
                conn.close();
            }
        }
    }
}
