package com.yan.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.entity.Type;
import com.yan.manage.mapper.TypeMapper;
import com.yan.manage.service.TypeService;
import com.yan.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam
 * @date 08/07/2020 - 4:48 pm
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public void addTypeInfo(TypeBO typeBO) {
        Type type = new Type();
        BeanUtils.copyProperties(typeBO,type);
        typeMapper.addType(type);
    }

    @Override
    public boolean delTypeInfo(int typeId) {
        int id = typeMapper.deleteType(typeId);
        return id > 0;
    }

    @Override
    public void updateTypeInfo(TypeBO typeBO) {
        Integer typeId = typeBO.getTypeId();
        Type type = new Type();

        BeanUtils.copyProperties(typeBO,type);
        type.setTypeId(typeBO.getTypeId());

        typeMapper.updateType(typeBO);
    }


    @Override
    public List<TypeBO> selTypeAll(int typeId) {
        List<Type> types = typeMapper.selectTypeAll();
        List<TypeBO> typesBO = new ArrayList<>();
        for (Type s : types) {
            TypeBO typeBO = new TypeBO();
            BeanUtils.copyProperties(s, typeBO);
            typesBO.add(typeBO);
        }
        return typesBO;
    }

    @Override
    public PagedGridResult selType(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Type> types = typeMapper.selectTypeAll();
        return setterPagedGrid(types,page);
    }

    @Override
    public TypeBO selTypeById(int typeId) {
        Type type = typeMapper.selTypeById(typeId);
        TypeBO typeBO = new TypeBO();
        BeanUtils.copyProperties(type, typeBO);
        return typeBO;
    }

    @Override
    public boolean queryTypeIsExist(String typeName) {
        Type type = typeMapper.selTypeByTypeName(typeName);
        return type == null ? false:true;
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
