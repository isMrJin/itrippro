package com.itrip.auth.service;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/11 20:54
 */
public interface SmsService {
    /**
    * @Description:发送验证码
    * @Param:  发送人，发送模板，发送数据
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-20:55
    */
    public void send(String to,String templateId,String [] datas) throws Exception;
}
