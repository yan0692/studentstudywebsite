package com.yan.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yan.manage.entity.BO.TagBO;
import com.yan.manage.entity.BO.TypeBO;
import com.yan.manage.entity.Tag;
import com.yan.manage.entity.Type;
import com.yan.manage.mapper.TagMapper;
import com.yan.manage.service.TagService;
import com.yan.utils.PagedGridResult;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam
 * @date 08/07/2020 - 4:48 pm
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public void addTagInfo(TagBO tagBO) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagBO,tag);
        tagMapper.addTag(tag);
    }

    @Override
    public boolean delTagInfo(int tagId) {
        int id = tagMapper.deleteTag(tagId);
        return id > 0;
    }

    @Override
    public void updateTagInfo(TagBO tagBO) {
        Integer tagId = tagBO.getTagId();
        Tag tag = new Tag();

        BeanUtils.copyProperties(tagBO,tag);
        tag.setTagId(tagBO.getTagId());

        tagMapper.updateTag(tagBO);
    }


    @Override
    public List<TagBO> selTagAll() {
        List<Tag> tags = tagMapper.selectTagAll();
        List<TagBO> tagsBO = new ArrayList<>();
        for (Tag t : tags) {
            TagBO tagBO = new TagBO();
            BeanUtils.copyProperties(t,tagBO);
            tagsBO.add(tagBO);
        }
        return tagsBO;
    }

    @Override
    public PagedGridResult selTag(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Tag> tags = tagMapper.selectTagAll();
        return setterPagedGrid(tags,page);
    }

    @Override
    public TagBO selTagByTagId(int tagId) {
        Tag tag = tagMapper.selTagByTagId(tagId);
        TagBO tagBO = new TagBO();
        BeanUtils.copyProperties(tag,tagBO);
        return tagBO;
    }

    @Override
    public boolean queryTagIsExist(String tagName) {
        Tag tag = tagMapper.selTagByTagName(tagName);
        return tag == null ? false : true;
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
