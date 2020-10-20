package com.yan.manage.controller;

import com.yan.manage.entity.BO.BlogBO;
import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.entity.Tag;
import com.yan.manage.entity.Type;
import com.yan.manage.service.BlogService;
import com.yan.utils.JsonResult;
import com.yan.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.expression.ValueListExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sam
 * @date 15/07/2020 - 2:50 pm
 */
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 360)
@RestController
@RequestMapping("/blog")
@Api(value = "博客页面", tags = {"博客页面接口"})
public class BlogController {
    @Autowired
    private BlogService blogService;
    @ApiOperation(value = "判断标题名是否存在", notes = "判断标题名是否存在", httpMethod = "GET")
    @GetMapping("/queryTitleIsExist")
    public JsonResult queryTitleIsExist(@RequestParam String title){
        if (StringUtils.isBlank(title)){
            return JsonResult.errorMsg("标题名不为空");
        }
        boolean isExist = blogService.queryTitleIsExist(title);
        if (isExist){
            return JsonResult.errorMsg("标题名已经存在，请重新输入");
        }
        return JsonResult.ok();
    }
    @ApiOperation(value = "获取博客", notes = "获取博客", httpMethod = "GET")
    @GetMapping("/get")
    public JsonResult get(@RequestParam int bid ){
        BlogBO blogBO = blogService.selBlogByBid(bid);
        return JsonResult.ok(blogBO);
    }
    @ApiOperation(value = "显示所有博客",notes = "显示所有博客", httpMethod = "GET")
    @GetMapping("/show")
    public Map table(){
        Map<String,Object>json = new HashMap<>();
        json.put("msg","success");
        json.put("code","0");
        json.put("data",blogService.selBlogAll());
        return json;
    }
    @ApiOperation(value = "添加博客", notes = "添加博客", httpMethod = "POST")
    @PostMapping("/add")
    public JsonResult add(@RequestBody BlogBO blogBO){
        blogService.addBlog(blogBO);
        return JsonResult.ok();
    }

    @ApiOperation(value = "显示分类",notes = "显示分类", httpMethod = "GET")
    @GetMapping("/typeList")
    public JsonResult getTypeList(){
        List<Type> typeList = blogService.selTypeList();
        return JsonResult.ok(typeList);
    }
    @ApiOperation(value = "显示标签",notes = "显示标签", httpMethod = "GET")
    @GetMapping("/tagList")
    public JsonResult getTagList(){
        List<Tag> tagList = blogService.selTagList();
        return JsonResult.ok(tagList);
    }
    @ApiOperation(value = "删除博客", notes = "删除博客", httpMethod = "POST")
    @PostMapping("/del")
    public JsonResult del(@RequestParam int bid){
        boolean isExist = blogService.delBlog(bid);
        if (isExist){
            return JsonResult.ok();
        }else{
            return JsonResult.errorMsg("删除失败");
        }
    }
    @ApiOperation(value = "修改博客", notes = "修改博客", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody BlogBO blogBO){
        blogService.updateBlog(blogBO);
        return JsonResult.ok();
    }
    @ApiOperation(value = "分页查找博客", notes = "分页查找博客", httpMethod = "GET")
    @GetMapping("/blogs")
    public JsonResult blogs(@RequestParam Integer page, Integer pageSize){
        if (page == null){
            page = 0;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        PagedGridResult pagedGridResult = blogService.selBlog(page, pageSize);
        return JsonResult.ok(pagedGridResult);
    }
    @ApiOperation(value = "搜索博客", notes = "搜索博客", httpMethod = "GET")
    @GetMapping("/search")
    public JsonResult search(@RequestParam String title,
                             @RequestParam Integer blogTypeId,
                             @RequestParam boolean recommend,
                             @RequestParam int page,
                             @RequestParam int pageSize){

        if(page == 0){
            page = 1;
        }
        if(pageSize == 0){
            pageSize = 5;
        }
        if(blogTypeId == null){
            blogTypeId = 0;
        }

        PagedGridResult pagedGridResult = blogService.searchBlog(title, blogTypeId, recommend, page, pageSize);
        List<?> list = pagedGridResult.getRows();
        if(list.size() == 0){
            return JsonResult.errorMsg("搜索结果为空");
        }
        return JsonResult.ok(pagedGridResult);
    }
}
