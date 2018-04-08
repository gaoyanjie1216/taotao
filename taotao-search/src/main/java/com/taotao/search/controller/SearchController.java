package com.taotao.search.controller;

import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaotaoResult;
import utils.ExceptionUtil;

/**
 * Created by Gao on 2018/2/27.
 */
@Controller
@RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
public class SearchController {

    @Autowired
    private SearchService searchService;

    //http://localhost:8083/search/query?q=*:*&page=2
    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ResponseBody
    public TaotaoResult search(@RequestParam("q") String queryString,
                               @RequestParam(defaultValue="1") Integer page,
                               @RequestParam(defaultValue="60") Integer rows) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return TaotaoResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
            //queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        return TaotaoResult.ok(searchResult);
    }
}