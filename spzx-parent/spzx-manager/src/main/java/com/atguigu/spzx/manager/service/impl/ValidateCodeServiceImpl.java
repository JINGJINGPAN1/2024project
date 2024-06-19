package com.atguigu.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public ValidateCodeVo generateValidateCode(){

        // 1. 通过工具生成图片验证码
        // hutool

        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String codeValue = circleCaptcha.getCode(); // 4位验证码的值
        String imageBase64 = circleCaptcha.getImageBase64(); //返回图片验证码， base64编码

        // 2. 把验证码存储到redis里面， 设置redis的key ： uuid redis的value：验证码的值
        // 设置过期时间
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user: validate" + key,
                                        codeValue,
                                       5,
                                        TimeUnit.MINUTES);

        //3 返回validateCodeVO对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key); // redis 存储的key
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        return validateCodeVo;
    }
}
