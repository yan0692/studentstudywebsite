package com.yan.adminLogin.controller;

import com.yan.adminLogin.entity.Users;
import com.yan.adminLogin.service.UsersService;
import com.yan.utils.CookieUtils;
import com.yan.utils.CookieUtils;
import com.yan.utils.JsonResult;
import com.yan.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sam
 * @date 08/07/2020 - 1:53 pm
 */
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 360)
@Api(value = "管理员登录", tags = {"管理员登录接口"})
@RestController
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @ApiOperation(value = "判断账号是否存在", notes = "判断账号是否存在",httpMethod = "GET")
    @GetMapping("/usersNameIsExist")
    public JsonResult usersNameIsExist(@RequestParam String username){
        if (StringUtils.isBlank(username)){
            return JsonResult.errorMsg("用户名不能为空");
        }
        boolean isExist = usersService.queryUsersNameIsExist(username);
        if (isExist){
            return JsonResult.errorMsg("用户名不存在");
        }
        return JsonResult.ok();
    }

    @GetMapping("/get")
    public JsonResult d(@RequestParam HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("user".equals(cookie.getName())){
                return JsonResult.ok(cookie);
            }
        }
        return JsonResult.errorMsg("未登录");
    }

    @ApiOperation(value = "管理员登录", notes = "管理员登录",httpMethod = "POST")
    @PostMapping("/login")
    public JsonResult login(@RequestParam String username, String password, HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JsonResult.errorMsg("用户名或密码不能为空!");
        }
        Users users = usersService.loginAccount(username, password);
        if (users == null){
            return JsonResult.errorMsg("用户名或密码错误!");
        }
        users = setNullProperty(users);
        CookieUtils.setCookie(request,response,"user", "JsonUtils.objectToJson(users)",true);
        return JsonResult.ok();
    }
    @PostMapping("/upload")
    public Map<String,Object> upload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        try {
            byte[]data;
            data = file.getBytes();
            params.put("img",data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("message","上传成功");
        return result;
    }
    private Users setNullProperty(Users users){
        users.setUsername(null);
        users.setNickName(null);
        users.setEmail(null);
        users.setImage(null);
        users.setPassword(null);
        users.setType(null);
        users.setCreateTime(null);
        users.setUpdateTime(null);
        return users;
    }
}
