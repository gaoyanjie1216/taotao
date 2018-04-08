package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Gao on 2018/1/29.
 */
@Controller
public class IndexController {

   /* @RequestMapping("/index")
    public String showIndex() {

        return "index";
    }*/

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String HelloWord() {
        System.out.println(325235252);
        return "666888!";
    }

}
