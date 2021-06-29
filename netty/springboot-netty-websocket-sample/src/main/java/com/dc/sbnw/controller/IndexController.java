package com.dc.sbnw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 首页控制器
 *
 * @author duchao
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "小米");
        model.addAttribute("age", "18");
        return "index";
    }
}
