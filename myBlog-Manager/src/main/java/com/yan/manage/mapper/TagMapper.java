package com.yan.manage.mapper;

import com.yan.manage.entity.BO.TagBO;
import com.yan.manage.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sam
 * @date 08/07/2020 - 4:44 pm
 */
@Mapper
@Repository
public interface TagMapper {
    @Insert("insert into tag (tag_name) values (#{tagName})" )
    void addTag(Tag tag);
    @Delete("delete from tag where tag_id = #{tagId}")
    int deleteTag(int tagId);
    @Update("update tag set tag_name = #{tagName} where tag_id = #{tagId}")
    void updateTag(TagBO tagBO);
    @Select("select tag_id, tag_name from tag")
    List<Tag> selectTagAll();
    @Select("select tag_id, tag_name from tag where tag_id = #{tagId}")
    Tag selTagByTagId(int tagId);
    @Select("select tag_id, tag_name from tag where tag_name = #{tagName}")
    Tag selTagByTagName(String tagName);
}
