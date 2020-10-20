package com.yan.manage.mapper;

import com.yan.manage.entity.BO.BlogBO;
import com.yan.manage.entity.Blog;
import com.yan.manage.entity.Tag;
import com.yan.manage.entity.Type;
import com.yan.manage.entity.VO.SearchBlogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Sam
 * @date 15/07/2020 - 1:35 pm
 */
@Mapper
@Repository
public interface BlogMapper {

    List<Blog> selBlogAll();

    Blog selBlogById(int bid);

    Blog selBlogByTitle(String title);

    void addBlog(Blog blog);

    int deleteBlog(int bid);

    void updateBlog(BlogBO blogBO);

    @Select("select a.type_id,a.type_name from type a")
    List<Type> selTypeAll();
    @Select("select tag_id,tag_name from tag")
    List<Tag> selTagAll();

    List<SearchBlogVO> searchBlog(@Param("title") String title,
                                  @Param("blogTypeId") int blogTypeId,
                                  @Param("recommend") int recommend);
}
