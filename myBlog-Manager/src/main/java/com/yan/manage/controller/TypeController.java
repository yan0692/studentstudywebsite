package com.yan.manage.controller;

import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.entity.Type;
import com.yan.manage.service.TypeService;
import com.yan.utils.JsonResult;
import com.yan.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sam
 * @date 08/07/2020 - 5:03 pm
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 360)
@RequestMapping("/type")
@Api(value = "分类页面", tags = {"分类页面接口"})
public class TypeController {
    @Autowired
    private TypeService typeService;
    @ApiOperation(value = "查找所有分类", notes = "查找所有分类", httpMethod = "GET")
    @GetMapping("/show")
    public Map table(@PathVariable int typeId){
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "success");
        json.put("code", "0");
        json.put("data", typeService.selTypeAll(typeId));
        return json;
    }

    @ApiOperation(value = "获取分类", notes = "获取分类", httpMethod = "GET")
    @GetMapping("/get")
    public JsonResult get(@RequestParam int typeId ){
        TypeBO typeBO = typeService.selTypeById(typeId);
        return JsonResult.ok(typeBO);
    }
    @ApiOperation(value = "判断分类是否存在", notes = "判断分类是否存在", httpMethod = "GET")
    @GetMapping("/queryTypeIsExist")
    public JsonResult queryTypeIsExist(@RequestParam String typeName){
        if (StringUtils.isBlank(typeName)){
            return JsonResult.errorMsg("用户名不能为空");
        }
        boolean isExist = typeService.queryTypeIsExist(typeName);
        if (isExist){
            return JsonResult.errorMsg("分类名已存在，请重新输入");
        }
        return JsonResult.ok();
    }
    @ApiOperation(value = "添加分类", notes = "添加分类", httpMethod = "POST")
    @PostMapping("/add")
    public JsonResult add(@RequestBody TypeBO typeBO){
        typeService.addTypeInfo(typeBO);
        return JsonResult.ok();
    }
    @ApiOperation(value = "删除分类", notes = "删除分类", httpMethod = "POST")
    @PostMapping("/del")
    public JsonResult del(@RequestParam int typeId){
        boolean isExist = typeService.delTypeInfo(typeId);
        if (isExist){
            return JsonResult.ok();
        }else{
            return JsonResult.errorMsg("删除失败");
        }
    }
    @ApiOperation(value = "修改分类", notes = "修改分类", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody TypeBO typeBO){
        typeService.updateTypeInfo(typeBO);
        return JsonResult.ok();
    }
    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "GET")
    @GetMapping("/types")
    public JsonResult types(@RequestParam Integer page, Integer pageSize){
        if (page == null){
            page = 0;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        PagedGridResult pagedGridResult = typeService.selType(page, pageSize);
        return JsonResult.ok(pagedGridResult);
    }
}
