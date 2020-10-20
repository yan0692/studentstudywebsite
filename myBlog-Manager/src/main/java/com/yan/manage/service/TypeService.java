package com.yan.manage.service;

import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.entity.Type;
import com.yan.utils.PagedGridResult;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author Sam
 * @date 08/07/2020 - 4:47 pm
 */
public interface TypeService {
    /**
     * 增加分类
     * @param typeBO
     */
    public void addTypeInfo(TypeBO typeBO);
    /**
     * 删除分类
     */
    public boolean delTypeInfo(int typeId);
    /**
     * 修改分类
     */
    public void updateTypeInfo(TypeBO typeBO);
    /**
     * 查找所有分类
     */
    public List<TypeBO> selTypeAll(int typeId);

    /**
     * 查找所有分类（分页）
     */
    public PagedGridResult selType(Integer page, Integer pageSize);


    /**
     * 通过id查找分类
     * @return
     */
    public TypeBO selTypeById(int typeId);

    /**
     * 判断分类是否已经存在
     */
    public boolean queryTypeIsExist(String typeName);

}
