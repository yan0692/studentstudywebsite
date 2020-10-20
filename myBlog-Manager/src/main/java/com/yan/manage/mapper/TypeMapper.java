package com.yan.manage.mapper;

import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sam
 * @date 08/07/2020 - 4:33 pm
 */
@Mapper
@Repository
public interface TypeMapper {
    @Insert("insert into type (type_name) values (#{typeName})" )
    void addType(Type type);
    @Delete("delete from type where type_id = #{typeId}")
    int deleteType(int typeId);
    @Update("update type set type_name = #{typeName} where type_id = #{typeId}")
    void updateType(TypeBO typeBO);
    @Select("select type_id, type_name from type")
    List<Type>selectTypeAll();
    @Select("select type_id, type_name from type where type_id = #{typeId}")
    Type selTypeById(int typeId);
    @Select("select type_id,type_name from type where type_name = #{typeName}")
    Type selTypeByTypeName(String typeName);
}
