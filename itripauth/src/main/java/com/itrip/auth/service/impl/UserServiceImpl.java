package com.itrip.auth.service.impl;

import com.itrip.auth.service.MailService;
import com.itrip.auth.service.UserService;
import com.itrip.beans.pojo.ItripUser;
import com.itrip.common.MD5;
import com.itrip.common.RedisAPI;
import com.itrip.dao.user.ItripUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/10 17:14
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private MailService mailService;
    @Resource
    private RedisAPI redisAPI;
    @Override
    public void itriptxCreateUser(ItripUser user) throws Exception {

        //1.添加用户信息
         itripUserMapper.insertItripUser(user);
        //2.生成激活码
        String activationCode = MD5.getMd5(new Date().toLocaleString(), 32);
        //3.发生邮件
        mailService.sendActivationMail(user.getUserCode(),activationCode);
        //4..激活码存入Redis
        redisAPI.set("activation:"+user.getUserCode(),30*2,activationCode);
    }

    @Override
    public boolean activate(String mail, String code) throws Exception {
        //1.验证激活码
        String value = redisAPI.get("activation:" + mail);
        if(value.equals(code)){
            //2.更新用户
            Map param = new HashMap();
            param.put("userCode",mail);
            List itripUserListByMap = itripUserMapper.getItripUserListByMap(param);
            if(itripUserListByMap.size()>0){
                ItripUser itripUser = (ItripUser) itripUserListByMap.get(0);
                //设置激活状态为1
                itripUser.setActivated(1);
                //自平台注册用户
                itripUser.setUserType(0);
                itripUser.setFlatID(itripUser.getId());
                itripUserMapper.updateItripUser(itripUser);
                return true;
            }
        }else {
            return false;
        }
        return false;
    }

    @Override
    public ItripUser findUserByUserCode(String userCode) throws Exception {
        Map param = new HashMap();
        param.put("userCode",userCode);
        List itripUserListByMap = itripUserMapper.getItripUserListByMap(param);
        if(itripUserListByMap.size()>0){
            return (ItripUser) itripUserListByMap.get(0);
        }else {
            return null;
        }
    }
}
