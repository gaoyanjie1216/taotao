package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.pojo.Item;

public interface ItemMapper {

	/**
	 * 查询全部商品列表
	 * @return
     */
	List<Item> getItemList();
}
