package com.taotao.service;

import com.taotao.pojo.TbContent;
import pojo.EUDataGridResult;
import pojo.TaotaoResult;

/**
 * Created by Gao on 2018/2/1.
 */
public interface ContentService {

    TaotaoResult insertContent(TbContent content);

    /**
     * 内容管理列表查询
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    EUDataGridResult getContentList(long categoryId, int page, int rows);
}
