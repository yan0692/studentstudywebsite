package com.yan.mainpage.mapper;

import com.yan.mainpage.entity.BO.BlogPageBO;
import com.yan.mainpage.entity.BlogPage;
import com.yan.mainpage.entity.Recommends;
import com.yan.mainpage.entity.Type;
import com.yan.manage.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sam
 * @date 22/07/2020 - 2:43 pm
 */
@Mapper
@Repository
public interface BlogPageMapper {
    /**
     * 显示博客内容列表
     * @return
     */
    List<BlogPage>selBlogPage();

    /**
     * 显示分类列表
     * @return
     */
    List<Type>selBlogType();

    /**
     * 显示标签列表
     * @return
     */
    List<Tag>selBlogTag();

    /**
     * 根据id查询博客文章详情
     * @param bid
     * @return
     */
    BlogPage selBlogDetailByTitle(int bid);

    /**
     * 显示最新推荐列表
     * @return
     */
    List<Recommends>selRecommends();
    @Update("update blog b set viewnum = viewnum + 1 where b.id = #{bid}")
    /**
     * 更新博客
     */
    void addViewNumById(int bid);

    /**
     * 搜索博客
     * @param
     * @param blogSummary
     * @return
     */
    List<BlogPage>searchBlog( @Param("blogSummary")String blogSummary);

    /**
     * 根据分类id搜索博客
     * @param typeId
     * @return
     */
    List<BlogPage>selBLogByTypeId(int typeId);

    /**
     * 根据标签id查找博客
     * @param tagId
     * @return
     */
    List<BlogPage>selBlogByTagId(int tagId);
}
