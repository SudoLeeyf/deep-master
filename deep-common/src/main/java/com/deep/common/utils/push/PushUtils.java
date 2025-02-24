package com.deep.common.utils.push;

import com.deep.common.utils.http.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author leeyf
 * @Date 2025/2/20 11:01
 */
public class PushUtils {
    public static Logger logger = LoggerFactory.getLogger(PushUtils.class);
    public static final String URL = "https://msg.umeng.com/api/send?sign=";
    /**
     * AppKey：				 67b6a11155c675225cb41ebf
     * Umeng Message Secret：102e292d7d6bdfe71bea26c76042687a
     * App Master Secret：   tqyjnd6dybkdgxm770pcldwtixecjfg7
     */
    private static final String APP_KEY = "67b6a11155c675225cb41ebf";
    private static final String APP_MASTER_SECRET = "tqyjnd6dybkdgxm770pcldwtixecjfg7";
    private static final String ALIAS_TYPE = "sft360_uname";
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * 发送APP消息
     * @param phonenumber 手机号
     * @param text 消息内容
     */
    public static void sendAlarm(String phonenumber, String text) {
        Map<String, Object> msg = new TreeMap<>();
        msg.put("appkey", APP_KEY);
        msg.put("timestamp", System.currentTimeMillis());
        msg.put("type", "customizedcast");
        msg.put("alias_type", ALIAS_TYPE);
        msg.put("alias", phonenumber);
        Map<String, Object> payload = new TreeMap<>();
        payload.put("display_type", "notification");
        Map<String, Object> body = new TreeMap<>();
        body.put("ticker", text);
        body.put("title", "报警推送");
        body.put("text", text);
        body.put("after_open", "go_custom");
        Map<String, Object> custom = new TreeMap<>();
        body.put("custom", custom);
        payload.put("body", body);
        msg.put("payload", payload);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String postBody = mapper.writeValueAsString(msg);
            String sign = sign(postBody);
            String url = URL + sign;
            executor.execute(() -> {
                String rs = "";
                try {
                    rs = HttpUtils.sendPost(url, postBody);
                    System.out.println("发送APP消息状态: " + rs);
                } catch (Exception e) {
                    logger.error("发送APP消息失败: ", e);
                }
            });
        } catch (Exception e) {
            logger.error("发送APP消息失败: ", e);
        }
    }

    /**
     * 签名
     */
    private static String sign(String postBody){
        String sb = "POST" +
                "https://msg.umeng.com/api/send" +
                postBody +
                APP_MASTER_SECRET;
        return DigestUtils.md5Hex(sb.getBytes());
    }

}
