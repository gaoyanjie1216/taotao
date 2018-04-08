package com.taotao.rest.controller;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类列表
 * Title: ItemCatController
 * Created by Gao on 2018/1/30.
 */
@Controller
@RequestMapping("/rest")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String HelloWord() {
        System.out.print(322325432);
        return "666888!";
    }

    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);

        return mappingJacksonValue;
    }
}
