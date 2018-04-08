package com.taotao.controller;

import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUTreeNode;
import pojo.TaotaoResult;

import java.util.List;

/**
 * 内容分类管理
 * Created by Gao on 2018/2/1.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * //获取内容分类列表
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);

        return list;
    }

    /**
     * 根据parentId和name添加分类
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name) {
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);

        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    //或者Long id, 前台传过来参数，参数名不能变
    public TaotaoResult updateContentCategory(@RequestParam("id") Long nodeId,
                                              @RequestParam("name") String name) {
        TaotaoResult result = contentCategoryService.updateContentCategory(nodeId, name);

        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(@RequestParam("id") Long id) {
        TaotaoResult taotaoResult = contentCategoryService.deleteContentCategory(id);
        return taotaoResult;
    }

}
