package com.test.controllers;

import com.test.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
    TestDAO testDAO;

    @Autowired
    public TestController(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    @GetMapping("/test")
    public String index(Model model) {
        model.addAttribute("tests", testDAO.index());
        return "/test";
    }

    @GetMapping("/test/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("test", testDAO.show(id));
        return "/show";
    }

    @PatchMapping("/test/{id}/release")
    public String release(@PathVariable("id") int id) {
        testDAO.release(id);
        return "redirect:/test/{id}";
    }

    @PatchMapping("/test/{id}/isAdult")
    public String isAdult(@PathVariable("id") int id) {
        testDAO.isAdult(id);
        return "redirect:/test/{id}";
    }
}
