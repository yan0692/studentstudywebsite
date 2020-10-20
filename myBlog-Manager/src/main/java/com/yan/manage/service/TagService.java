package com.yan.manage.service;

import com.yan.manage.entity.BO.TagBO;
import com.yan.manage.entity.Tag;
import com.yan.utils.PagedGridResult;


import java.util.List;

/**
 * @author Sam
 * @date 08/07/2020 - 4:47 pm
 */
public interface TagService {
    /**
     * 增加标签
     * @param tagBO
     */
    public void addTagInfo(TagBO tagBO);

    /**
     * 删除标签
     * @param tagId
     * @return
     */
    public boolean delTagInfo(int tagId);

    /**
     * 修改标签
     * @param tagBO
     */
    public void updateTagInfo(TagBO tagBO);

    /**
     * 查找标签
     * @return
     */
    public List<TagBO> selTagAll();

    /**
     * 分页查找
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult selTag(Integer page, Integer pageSize);

    /**
     * 根据id查找分类
     * @param tagId
     * @return
     */
    public TagBO selTagByTagId(int tagId);

    /**
     * 判断标签名是否存在
     * @param tagName
     * @return
     */
    public boolean queryTagIsExist(String tagName);
}
