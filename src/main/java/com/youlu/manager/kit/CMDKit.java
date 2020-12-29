package com.youlu.manager.kit;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CMDKit {

    public static Map<String,Object> test(String ip, int port, String username, String password) throws IOException {
        //指明连接主机的IP地址
        Connection conn = new Connection(ip,port);
        conn.connect();
        boolean isconn = conn.authenticateWithPassword(username, password);
        Map<String,Object> map = new HashMap<>();
        map.put("boolean",isconn);
        map.put("conn",conn);
        return map;
    }

    public static void cmd(String cmd,String ip) throws IOException {
//        Map<String, Object> root = test("116.62.42.252", 11022, "root", "nhYjdk05vYO4p^I");
        Map<String, Object> root = test(ip, 22, "root", "l5*!D!wvYOgV$kYC");
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
//                ssh.execCommand("sh ceshi.sh");
//                ssh.execCommand(cmd);
//                ssh.execCommand("cd /usr/lib/systemd/system");
//                ssh.execCommand("systemctl start node_exporter.service");
                //只允许使用一行命令，即ssh对象只能使用一次execCommand这个方法，多次使用则会出现异常.
                //使用多个命令用分号隔开
                ssh.execCommand(cmd);
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
