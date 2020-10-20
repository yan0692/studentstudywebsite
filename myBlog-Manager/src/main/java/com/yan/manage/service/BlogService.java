package com.yan.manage.service;

import com.yan.manage.entity.BO.BlogBO;
import com.yan.manage.entity.Blog;
import com.yan.manage.entity.Tag;
import com.yan.manage.entity.Type;
import com.yan.utils.PagedGridResult;

import java.util.Date;
import java.util.List;

/**
 * @author Sam
 * @date 15/07/2020 - 2:42 pm
 */
public interface BlogService {
    /**
     * 查找博客
     * @return
     */
    public List<BlogBO> selBlogAll();

    /**
     * 根据id查找博客
     * @param bid
     * @return
     */
    public BlogBO selBlogByBid(int bid);

    /**
     * 添加博客
     */
    public void addBlog(BlogBO blogBO);

    /**
     * 判断标题是否存在
     * @param title
     * @return
     */
    public boolean queryTitleIsExist(String title);

    /**
     * 获取分类
     * @return
     */
    List<Type> selTypeList();
    /**
     * 获取标签
     */
    List<Tag> selTagList();

    /**
     * 根据id删除博客
     * @param bid
     * @return
     */
    public boolean delBlog(int bid);

    /**
     * 修改博客
     * @param blogBO
     */
    public void updateBlog(BlogBO blogBO);

    /**
     * 查找博客（分页）
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult selBlog(Integer page, Integer pageSize);

    /**
     * 搜索博客
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchBlog(String title,
                                      Integer blogTypeId,
                                      boolean recommend,
                                      Integer page,
                                      Integer pageSize);
}
