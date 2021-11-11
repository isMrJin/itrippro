package com.itrip.auth.controller;

import com.itrip.auth.service.UserService;
import com.itrip.beans.dto.Dto;
import com.itrip.beans.pojo.ItripUser;
import com.itrip.beans.vo.userinfo.ItripUserVO;
import com.itrip.common.DtoUtil;
import com.itrip.common.ErrorCode;
import com.itrip.common.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/11 18:39
 */
@Controller
@RequestMapping("/api")
public class UserController {
    @Resource
    private UserService userService;

    /**
    * @Description: 邮箱注册
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-21:17
    */
    @RequestMapping(value = "/doregister",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody Dto doRegister(@RequestBody ItripUserVO vo) throws Exception {
        //1.邮箱验证
       if(this.validEmail(vo.getUserCode())){
          return DtoUtil.returnFail("请使用正确的邮箱地址", ErrorCode.AUTH_ILLEGAL_USERCODE );
       }
        ItripUser itripUser = new ItripUser();
        itripUser.setUserCode(vo.getUserCode());
        itripUser.setUserName(vo.getUserName());
        try{
           if(null!=userService.findUserByUserCode(vo.getUserCode())){
               return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_USER_ALREADY_EXISTS );
           }else{
            //2.调用业务层createUser
               //MD5加密密码
               itripUser.setUserPassword(MD5.getMd5(vo.getUserPassword(),32));
               userService.itriptxCreateUser(itripUser);
               return DtoUtil.returnSuccess();
           }
        }catch (Exception e){
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),ErrorCode.AUTH_UNKNOWN);
        }
    }
    /**
    * @Description: 验证邮箱
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-21:27
    */
    @RequestMapping(value = "/activate",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody Dto activate(@RequestParam String user,@RequestParam String code){
        try{
            if(userService.activate(user,code)){
                return DtoUtil.returnSuccess("激活成功");
            }else{
                return DtoUtil.returnSuccess("激活失败");
            }
        }catch (Exception e){
            return DtoUtil.returnFail("激活失败",ErrorCode.AUTH_UNKNOWN);
        }
    }


    /**
     * @Description: 手机注册
     * @Author: Mr.ShenJinChao
     * @Date: 2021/11/11-21:17
     */
    @RequestMapping(value = "/registerbyphone",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody Dto doRegisterByPhone(@RequestBody ItripUserVO vo) throws Exception {
        //1.手机号验证
        if(validPhone(vo.getUserCode())){
            return DtoUtil.returnFail("请使用正确的手机号",ErrorCode.AUTH_ILLEGAL_USERCODE);
        }
        //2.调用service业务
        ItripUser itripUser = new ItripUser();
        itripUser.setUserCode(vo.getUserCode());
        itripUser.setUserName(vo.getUserName());
        try{
            if(null!=userService.findUserByUserCode(vo.getUserCode())){
                return DtoUtil.returnFail("用户已存在", ErrorCode.AUTH_USER_ALREADY_EXISTS );
            }else{
                //MD5加密密码
                itripUser.setUserPassword(MD5.getMd5(vo.getUserPassword(),32));
                userService.itriptxCreateByPhone(itripUser);
                return DtoUtil.returnSuccess();
            }
        }catch (Exception e){
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),ErrorCode.AUTH_UNKNOWN);
        }
    }

    /**
     * @Description: 验证手机号
     * @Author: Mr.ShenJinChao
     * @Date: 2021/11/11-21:27
     */
    @RequestMapping(value = "/validatephone",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody Dto validatePhone(@RequestParam String user,@RequestParam String code){
        try{
            if(userService.validatePhone(user,code)){
                return DtoUtil.returnSuccess("验证成功");
            }else{
                return DtoUtil.returnSuccess("验证失败");
            }
        }catch (Exception e){
            return DtoUtil.returnFail("验证失败",ErrorCode.AUTH_UNKNOWN);
        }
    }

    //    合法E-mail地址：
    //    1. 必须包含一个并且只有一个符号“@”
    //    2. 第一个字符不得是“@”或者“.”
    //    3. 不允许出现“@.”或者.@
    //    4. 结尾不得是字符“@”或者“.”
    //    5. 允许“@”前的字符中出现“＋”
    //    6. 不允许“＋”在最前面，或者“＋@”
    public boolean validEmail(String email){
        String regex="^(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        return Pattern.compile(regex).matcher(email).find();
    }
    
    /** 
    * @Description: 验证手机号格式
    * @Author: Mr.ShenJinChao
    * @Date: 2021/11/11-21:24
    */
    public boolean validPhone(String phone){
        String regex = "/^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$/";
        return Pattern.compile(regex).matcher(phone).find();
    }


}
