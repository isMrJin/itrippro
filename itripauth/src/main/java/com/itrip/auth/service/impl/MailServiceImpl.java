package com.itrip.auth.service.impl;

import com.itrip.auth.service.MailService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/10 18:45
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Resource
    private SimpleMailMessage activationMailMessage;
    @Resource
    private MailSender mailSender;
    @Override
    public void sendActivationMail(String mailTo, String activationCode) {
        activationMailMessage.setTo(mailTo);

        activationMailMessage.setText("用户您好，您的激活码是："+activationCode);
        mailSender.send(activationMailMessage);
    }
}
