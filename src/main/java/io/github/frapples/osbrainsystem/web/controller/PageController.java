package io.github.frapples.osbrainsystem.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String route(@PathVariable String page, Model model) {
        log.info("Enter page {}", page);
        return page;
    }
}

