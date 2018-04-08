package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gao on 2018/2/4.
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 前台首页展示大广告位图片列表
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = contentService.getContentList();
        model.addAttribute("ad1", adJson);

        return "index";
    }
}
