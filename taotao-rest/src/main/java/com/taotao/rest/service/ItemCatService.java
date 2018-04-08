package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;

/**
 * Created by Gao on 2018/1/30.
 */
public interface ItemCatService {

    /**
     * portal调用rest,得到商品分类列表
     * @return
     */
    CatResult getItemCatList();
}
