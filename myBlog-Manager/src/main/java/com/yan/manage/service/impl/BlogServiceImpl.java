package com.yan.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yan.manage.entity.BO.BlogBO;
import com.yan.manage.entity.Blog;
import com.yan.manage.entity.Tag;
import com.yan.manage.entity.Type;
import com.yan.manage.entity.VO.SearchBlogVO;
import com.yan.manage.mapper.BlogMapper;
import com.yan.manage.service.BlogService;
import com.yan.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Sam
 * @date 15/07/2020 - 2:44 pm
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Type> selTypeList() {
        return blogMapper.selTypeAll();
    }

    @Override
    public List<Tag> selTagList() {
        return blogMapper.selTagAll();
    }

    @Override
    public boolean delBlog(int bid) {
        int id = blogMapper.deleteBlog(bid);
        return id > 0;
    }

    @Override
    public void updateBlog(BlogBO blogBO) {
        Integer bid = blogBO.getBid();
        Blog blog = new Blog();

        BeanUtils.copyProperties(blogBO,blog);
        blog.setBid(blogBO.getBid());
        blogMapper.updateBlog(blogBO);
    }

    @Override
    public PagedGridResult selBlog(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Blog> blogs = blogMapper.selBlogAll();
        List<BlogBO>blog = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Blog b : blogs ) {
            BlogBO blogBO = new BlogBO();
            BeanUtils.copyProperties(b, blogBO);
            blogBO.setUpdatedTimeStr(sdf.format(b.getUpdatedTime()));
            blog.add(blogBO);
        }
        return setterPagedGrid(blog,page);
    }

    @Override
    public PagedGridResult searchBlog(String title, Integer blogTypeId, boolean recommend, Integer page, Integer pageSize) {
        int recommendNum = recommend ? 1 : 0;
        PageHelper.startPage(page,pageSize);
        List<SearchBlogVO> blogVOList = blogMapper.searchBlog(title, blogTypeId, recommendNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(SearchBlogVO searchBlogVO : blogVOList){
            searchBlogVO.setUpdatedTimeStr(sdf.format(searchBlogVO.getUpdatedTime()));
        }
        PagedGridResult pagedGridResult = setterPagedGrid(blogVOList, page);
        return pagedGridResult;
    }

    @Override
    public List<BlogBO> selBlogAll() {

        List<Blog> blogs = blogMapper.selBlogAll();
        List<BlogBO> blog = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Blog b : blogs) {
            BlogBO blogBO = new BlogBO();
            BeanUtils.copyProperties(b, blogBO);
            blogBO.setUpdatedTimeStr(sdf.format(b.getUpdatedTime()));
            blog.add(blogBO);
        }
        return blog;
    }

    @Override
    public BlogBO selBlogByBid(int bid) {
        Blog blog = blogMapper.selBlogById(bid);
        BlogBO blogBO = new BlogBO();
        BeanUtils.copyProperties(blog,blogBO);
        return blogBO;
    }


    @Override
    public void addBlog(BlogBO blogBO) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogBO,blog);
        blog.setCreatedTime(new Date());
        blog.setUpdatedTime(new Date());
        blogMapper.addBlog(blog);
    }

    @Override
    public boolean queryTitleIsExist(String title) {
        Blog blog = blogMapper.selBlogByTitle(title);
        return blog == null ? false : true;
    }
    private PagedGridResult setterPagedGrid(List<?> list,Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
