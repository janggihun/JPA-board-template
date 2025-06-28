package com.study.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {


    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/board")
    public String board() {

        return "board";
    }

    @GetMapping("/create")
    public String insert() {

        return "create";
    }

    @GetMapping("/read/{boardId}")
    public String read(@PathVariable(name = "boardId") Long boardId, Model model) {

        model.addAttribute("boardId", boardId);

        return "read";
    }
}
