package com.itrip.auth.service;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/10 18:35
 */
public interface MailService {
    /** 
    * @Description: 发送邮件
    * @Param:  发送目标地址，激活码
    * @return:  
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/10-18:36
    */
    public void sendActivationMail(String mailTo,String activationCode );
}
