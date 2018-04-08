package com.taotao.controller;

import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUDataGridResult;
import pojo.TaotaoResult;

/**
 * 内容管理Controller
 * Created by Gao on 2018/2/1.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     *  内容管理列表查询
     */
    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(long categoryId, int page, int rows) {
        EUDataGridResult content = contentService.getContentList(categoryId, page, rows);
        return content;
    }


    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content) {
        TaotaoResult result = contentService.insertContent(content);

        return result;
    }
}