package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUDataGridResult;
import pojo.TaotaoResult;

/**
 * Created by Gao on 2018/1/23.
 */

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String HelloWord() {
        return "666888!";
    }

    @RequestMapping("/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) { //Integer包装类可以为null, int不能为空
        EUDataGridResult result = itemService.getItemList(page, rows);

        return result;
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    @ResponseBody
    private TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
        TaotaoResult result = itemService.createItem(item, desc, itemParams);
        return result;
    }

}
