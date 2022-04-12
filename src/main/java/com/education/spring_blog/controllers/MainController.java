package com.education.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
// обрабатывается URl: адрес сайта / - главная страница
    @GetMapping("/")
    public String house(Model model) {
        model.addAttribute("title", "Главная страница"); // внутри шаблона обращаемся к параметру title
        return "house"; //вызывается HTML шаблон home
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "about"; //вызывается HTML шаблон about
    }
}
