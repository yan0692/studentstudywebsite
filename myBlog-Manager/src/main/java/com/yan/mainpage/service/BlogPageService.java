package com.yan.mainpage.service;

import com.yan.mainpage.entity.BO.*;
import com.yan.mainpage.entity.Recommends;
import com.yan.mainpage.entity.Type;
import com.yan.manage.entity.Tag;

import java.util.List;

/**
 * @author Sam
 * @date 22/07/2020 - 3:02 pm
 */
public interface BlogPageService {
    /**
     * 展示所有文章
     *
     * @return
     */
    public List<BlogPageBO> selBlogPageAll();

    /**
     * 展示所有分类
     *
     * @return
     */
    public List<Type> selBlogTypeAll();

    /**
     * 展示所有标签
     *
     * @return
     */
    public List<Tag> selBlogTagAll();

    /**
     * 根据题目id展示博客文章内容
     *
     * @return
     */
    public BlogDetailBO showBlogDetail(int bid);

    /**
     * 展示最新推荐文章
     *
     * @return
     */
    public List<Recommends> selRecommends();

    /**
     * 根据id更新查看次数
     * @param bid
     */
    public void addViewNum(int bid);

    /**
     * 搜索博客
     * @param blogSummary
     * @return
     */
    public List<BlogSearchBO>searchBlog(String blogSummary);

    /**
     * 根据分类id查找博客
     * @param typeId
     * @return
     */
    public List<BlogTypeBO>selBlogByTypId(int typeId);

    /**
     * 根据标签查找博客
     * @param tagId
     * @return
     */
    public List<BlogTagBO>selBlogByTagId(int tagId);
}