package com.taotao.service;

import pojo.EUTreeNode;

import java.util.List;

/**
 * Created by Gao on 2018/1/25.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);
}
