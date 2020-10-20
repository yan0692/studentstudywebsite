package com.yan.manage.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.yan.manage.entity.BO.TagBO;
import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.service.TagService;
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
 * @date 14/07/2020 - 3:14 pm
 */
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 360)
@RestController
@RequestMapping("/tag")
@Api(value = "标签操作页面", tags = {"标签相关操作接口"})
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "判断标签是否存在", notes = "判断标签是否存在",httpMethod = "GET")
    @GetMapping("/queryTagIsExist")
    public JsonResult queryTagIsExist(@RequestParam String tagName){
        if (StringUtils.isBlank(tagName)){
            return JsonResult.errorMsg("标签名不能为空");
        }
        boolean isExist = tagService.queryTagIsExist(tagName);
        if (isExist){
            return JsonResult.errorMsg("标签名已存在，请重新输入");
        }
        return JsonResult.ok();
    }
    @ApiOperation(value = "获取分类", notes = "获取分类", httpMethod = "GET")
    @GetMapping("/get")
    public JsonResult get(@RequestParam int tagId ){
        TagBO tagBO = tagService.selTagByTagId(tagId);
        return JsonResult.ok(tagBO);
    }
    @ApiOperation(value = "增加标签", notes = "增加标签", httpMethod = "POST")
    @PostMapping("/add")
    public JsonResult add(@RequestBody TagBO tagBO){
        tagService.addTagInfo(tagBO);
        return JsonResult.ok();
    }
    @ApiOperation(value = "删除标签", notes = "删除标签", httpMethod = "POST")
    @PostMapping("/del")
    public JsonResult del(@RequestParam int tagId){
        boolean isExist = tagService.delTagInfo(tagId);
        if (isExist){
            return JsonResult.ok("删除成功");
        }else{
            return JsonResult.errorMsg("删除失败");
        }
    }
    @ApiOperation(value = "修改标签", notes = "修改标签", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody TagBO tagBO) {
        tagService.updateTagInfo(tagBO);
        return JsonResult.ok();
    }
    @ApiOperation(value = "获取所有标签", notes = "获取所有标签", httpMethod = "GET")
    @GetMapping("/show")
    public Map table(){
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "success");
        json.put("code", "0");
        json.put("data", tagService.selTagAll());
        return json;
    }
    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "GET")
    @GetMapping("/tags")
    public JsonResult tags(@RequestParam Integer page, Integer pageSize){
        if (page == null){
            page = 0;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        PagedGridResult pagedGridResult = tagService.selTag(page, pageSize);
        return JsonResult.ok(pagedGridResult);
    }
}
