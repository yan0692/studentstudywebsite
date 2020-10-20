package com.yan.mainpage.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.yan.mainpage.entity.BO.*;
import com.yan.mainpage.entity.Recommends;
import com.yan.mainpage.entity.Type;
import com.yan.mainpage.service.BlogPageService;
import com.yan.manage.entity.Tag;
import com.yan.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sam
 * @date 22/07/2020 - 3:36 pm
 */
@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 360)
@RestController
@RequestMapping("/blogPage")
@Api(value = "博客主页面",tags = {"博客主页面接口"})
public class BlogPageController {
    @Autowired
    private BlogPageService blogPageService;
    @ApiOperation(value = "显示所有博客", notes = "显示所有博客", httpMethod = "GET")
    @GetMapping("/show")
    public Map show(){
        Map<String,Object> json = new HashMap<>();
        json.put("msg","success");
        json.put("code","0");
        json.put("data",blogPageService.selBlogPageAll());
        return json;
    }
    @ApiOperation(value = "显示所有分类内容", notes = "显示所有分类内容", httpMethod = "GET")
    @GetMapping("/showType")
    public JsonResult showType(){
        List<Type> typeList = blogPageService.selBlogTypeAll();

        return JsonResult.ok(typeList);
    }
    @ApiOperation(value = "显示所有标签内容",notes = "显示所有标签内容", httpMethod = "GET")
    @GetMapping("/showTag")
    public JsonResult showTag(){
        List<Tag> tags = blogPageService.selBlogTagAll();
        return JsonResult.ok(tags);
    }
    @ApiOperation(value = "显示所有推荐内容", notes = "显示所有推荐内容", httpMethod = "GET")
    @GetMapping("/showRecommend")
    public JsonResult showRecommend(){
        List<Recommends> recommends = blogPageService.selRecommends();
        return JsonResult.ok(recommends);
    }
    @ApiOperation(value = "显示博客内容", notes = "显示博客内容",httpMethod = "GET")
    @GetMapping("/showBlog")
    public JsonResult showBlog(@RequestParam String bid){
        BlogDetailBO blogDetailBO = blogPageService.showBlogDetail(Integer.parseInt(bid));
        return JsonResult.ok(blogDetailBO);
    }
    @ApiOperation(value = "更新博客观看次数", notes = "更新博客观看次数",httpMethod = "GET")
    @GetMapping("/addViewNum")
    public JsonResult addViewNum(@RequestParam int bid){
        blogPageService.addViewNum(bid);
        return JsonResult.ok();
    }
    @ApiOperation(value = "搜索博客", notes = "搜索博客", httpMethod = "GET")
    @GetMapping("/searchBlog")
    public JsonResult searchBlog(@RequestParam String blogSummary){
        List<BlogSearchBO> searchBlog = blogPageService.searchBlog(blogSummary);
        return JsonResult.ok(searchBlog);
    }
    @ApiOperation(value = "根据分类id搜索博客",notes = "根据分类id搜索博客",httpMethod = "GET")
    @GetMapping("/selBlogByTypeId")
    public JsonResult selBlogByTypeId(@RequestParam int typeId){
        List<BlogTypeBO> blogTypeBOS = blogPageService.selBlogByTypId(typeId);
        return JsonResult.ok(blogTypeBOS);
    }
    @ApiOperation(value = "根据标签id搜索博客",notes = "根据标签id搜索博客",httpMethod = "GET")
    @GetMapping("/selBlogByTagId")
    public JsonResult selBlogByTagId(@RequestParam int tagId){
        List<BlogTagBO> blogTagBOS = blogPageService.selBlogByTagId(tagId);
        return JsonResult.ok(blogTagBOS);
    }
}
