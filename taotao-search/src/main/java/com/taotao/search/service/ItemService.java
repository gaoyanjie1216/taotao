package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrServerException;
import pojo.TaotaoResult;

import java.io.IOException;

/**
 * Created by Gao on 2018/2/27.
 */
public interface ItemService {

    /**
     * 导入商品数据到索引库
     * @return
     */
    TaotaoResult importAllItems() throws SolrServerException, IOException;
}
