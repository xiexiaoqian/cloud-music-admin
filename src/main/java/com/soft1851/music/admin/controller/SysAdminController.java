package com.soft1851.music.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.entity.SysRole;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xxq
 * @since 2020-04-22
 */
@RestController
@RequestMapping(value = "/sysAdmin")
@Slf4j
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 登录
     *
     * @return String
     */
    @PostMapping("/login")
    public Map login(@RequestBody LoginDto loginDto) {
        Map<String, Object> map = new TreeMap<>();
        log.info(loginDto.toString());
        //判断登录结果
        boolean isLogin = sysAdminService.login(loginDto);
        log.info(String.valueOf(isLogin));
        if (isLogin) {
            //登录成功，取得admin的信息（包含所有角色）
            SysAdmin admin = sysAdminService.getAdminAndRolesByName(loginDto.getName());
            //roles是个list，可能会是多个
            List<SysRole> roles = admin.getRoles();
            String roleString = JSONObject.toJSONString(roles);
            log.info("管理员角色列表：" + roleString);
            //将该管理员的所有角色的集合roles存入token，在后面鉴权的时候从中查找，有效时间10分钟
            String token = JwtTokenUtil.getToken(admin.getId(), JSONObject.toJSONString(roles), new Date(System.currentTimeMillis() + 600L * 1000L));
            map.put("admin", admin);
            map.put("token", token);
        } else {
            map.put("msg", "登录失败");
        }
        return map;
    }
}