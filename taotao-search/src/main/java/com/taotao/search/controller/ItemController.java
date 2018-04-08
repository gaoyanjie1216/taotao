package com.taotao.search.controller;

import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaotaoResult;

import java.io.IOException;

/**
 * Created by Gao on 2018/2/27.
 */
@Controller
@RequestMapping(value = "/item", produces = "application/json;charset=UTF-8")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 导入商品数据到索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItems() throws SolrServerException, IOException {
        TaotaoResult result = itemService.importAllItems();

        return result;
    }
}