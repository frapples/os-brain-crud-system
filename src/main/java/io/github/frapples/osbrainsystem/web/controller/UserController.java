package io.github.frapples.osbrainsystem.web.controller;

import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.biz.service.AccountService;
import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@Slf4j
public class UserController {

   @Autowired
   private AccountService accountService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<Object> addUser(User user) {
        log.info("add student with: {}", user);
        return accountService.addUser(user);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<List<User>> getUsers() {
        return accountService.getUsers();
    }

    @RequestMapping(value = "/user/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<User> getUser(@PathVariable String studentId) {
        log.info("get student with: student id: {}", studentId);
        return accountService.getUser(studentId);
    }
}
