
package com.taotao.service;

import com.taotao.pojo.TbItem;
import pojo.EUDataGridResult;
import pojo.TaotaoResult;


/**
 * Created by Gao on 2018/1/23.
 */

public interface ItemService {

    /**
     * 根据id获取商品信息
     *
     * @param itemId
     * @return
     */
    TbItem getItemById(Long itemId);

    /**
     * 得到商品列表，带分页
     *
     * @param page
     * @param rows
     * @return
     */

    public EUDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品
     * @param item
     * @param desc
     * @param itemParam
     * @return
     * @throws Exception
     */
    TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}

