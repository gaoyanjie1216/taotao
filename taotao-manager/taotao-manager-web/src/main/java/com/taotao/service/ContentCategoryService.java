package com.taotao.service;

import pojo.EUTreeNode;
import pojo.TaotaoResult;

import java.util.List;

/**
 * Created by Gao on 2018/2/1.
 */
public interface ContentCategoryService {

    /**
     * 得到内容分类
     * @param parentId
     * @return
     */
    List<EUTreeNode> getCategoryList(Long parentId);

    /**
     * 添加分类
     * @param parentId
     * @param name
     * @return
     */
    TaotaoResult insertContentCategory(Long parentId, String name);

    /**
     * 更新分类名称
     * @param nodeId
     * @param name
     * @return
     */
    TaotaoResult updateContentCategory(Long nodeId, String name);

    /**
     * 根据删除分类
     * @return
     */
    TaotaoResult deleteContentCategory(Long id);

}
