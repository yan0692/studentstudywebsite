package com.yan.mainpage.service.impl;

import com.yan.mainpage.entity.BO.*;
import com.yan.mainpage.entity.BlogPage;
import com.yan.mainpage.entity.Recommends;
import com.yan.mainpage.entity.Type;
import com.yan.mainpage.mapper.BlogPageMapper;
import com.yan.mainpage.service.BlogPageService;
import com.yan.manage.entity.Blog;
import com.yan.manage.entity.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam
 * @date 22/07/2020 - 3:15 pm
 */
@Service
public class BlogPageServiceImpl implements BlogPageService {
    @Autowired
    private BlogPageMapper blogPageMapper;

    @Override
    public List<BlogPageBO> selBlogPageAll() {
        List<BlogPage> blogPages = blogPageMapper.selBlogPage();
        List<BlogPageBO> blog = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (BlogPage b:blogPages) {
            BlogPageBO blogPageBO = new BlogPageBO();
            BeanUtils.copyProperties(b,blogPageBO);
            blogPageBO.setUpdatedTimeStr(sdf.format(b.getUpdatedTime()));
            blog.add(blogPageBO);
        }
        return blog;
    }

    @Override
    public List<Type> selBlogTypeAll() {
        return blogPageMapper.selBlogType();
    }

    @Override
    public List<Tag> selBlogTagAll() {
        return blogPageMapper.selBlogTag();
    }

    @Override
    public BlogDetailBO showBlogDetail(int bid) {
        BlogPage blogPage = blogPageMapper.selBlogDetailByTitle(bid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BlogDetailBO blogDetailBO = new BlogDetailBO();
        BeanUtils.copyProperties(blogPage,blogDetailBO);
        blogDetailBO.setUpdatedTimeStr(sdf.format(blogPage.getUpdatedTime()));
        return blogDetailBO;
    }

    @Override
    public List<Recommends> selRecommends() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Recommends> recommendsList = blogPageMapper.selRecommends();
        for(Recommends r : recommendsList){
            r.setUpdatedTimeStr(sdf.format(r.getUpdatedTime()));
        }
        return recommendsList;
    }

    @Override
    public void addViewNum(int bid) {
        blogPageMapper.addViewNumById(bid);
    }

    @Override
    public List<BlogSearchBO> searchBlog(String blogSummary) {
        List<BlogPage> blogPages = blogPageMapper.searchBlog(blogSummary);
        List<BlogSearchBO> search = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (BlogPage page : blogPages) {
            BlogSearchBO bo = new BlogSearchBO();
            BeanUtils.copyProperties(page,bo);
            bo.setUpdatedTimeStr(sdf.format(bo.getUpdatedTime()));
            search.add(bo);
        }
        return search;
    }

    @Override
    public List<BlogTypeBO> selBlogByTypId(int typeId) {
        List<BlogPage> blogPages = blogPageMapper.selBLogByTypeId(typeId);
        List<BlogTypeBO>BO = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(BlogPage p :  blogPages){
            BlogTypeBO typeBO = new BlogTypeBO();
            BeanUtils.copyProperties(p,typeBO);
            typeBO.setUpdatedTimeStr(sdf.format(typeBO.getUpdatedTime()));
            BO.add(typeBO);
        }
        return BO;
    }

    @Override
    public List<BlogTagBO> selBlogByTagId(int tagId) {
        List<BlogPage> pages = blogPageMapper.selBlogByTagId(tagId);
        List<BlogTagBO>bo = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(BlogPage p: pages){
            BlogTagBO tag = new BlogTagBO();
            BeanUtils.copyProperties(p,tag);
            tag.setUpdatedTimeStr(sdf.format(tag.getUpdatedTime()));
            bo.add(tag);
        }
        return bo;
    }
}
