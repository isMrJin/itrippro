package com.itrip.auth.service;

import com.itrip.beans.pojo.ItripUser;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/10 17:11
 */
public interface UserService {

    /**
    * @Description: 创建用户
    * @Param:  ItripUser
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/10-17:13
    */
    public void itriptxCreateUser(ItripUser user) throws Exception;
    
    /** 
    * @Description: 验证用户激活信息
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-19:15
    */
    public boolean activate(String mail,String code) throws  Exception;
    
    /** 
    * @Description: 查询该用户
    * @Param:  
    * @return:  
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-19:18
    */
    public ItripUser findUserByUserCode(String userCode)throws Exception;
    /** 
    * @Description: 手机号注册用户
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-20:52
    */
    public void itriptxCreateByPhone(ItripUser itripUser) throws Exception;

    /**
    * @Description: 短信验证
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-21:07
    */
    public boolean validatePhone(String phoneNum,String code) throws Exception;
}
