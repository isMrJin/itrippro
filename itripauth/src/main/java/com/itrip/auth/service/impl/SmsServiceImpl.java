package com.itrip.auth.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.itrip.auth.service.SmsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/11 20:55
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {
    @Override
    public void send(String to, String templateId, String[] datas) throws Exception {
        //设置短信提供商的信息
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init("app.cloopen.com","8883");
        sdk.setAccount("8aaf07087ce03b67017d0edcdd160acf","7821f71548af4ffc9884a8cc4e7991ff");
        sdk.setAppId("8aaf07087ce03b67017d0edcde430ad6");
        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas);
        if("000000".equals(result.get("statusCode"))){
            System.out.println(to+"短信发送成功");
        }else {
            throw new Exception(result.get("statusCodde").toString()+result.get("statusMsg").toString());
        }
    }
}
