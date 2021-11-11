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

    public boolean activate(String mail,String code) throws  Exception;
}
