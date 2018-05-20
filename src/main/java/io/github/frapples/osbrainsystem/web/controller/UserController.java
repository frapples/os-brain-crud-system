package io.github.frapples.osbrainsystem.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.github.frapples.osbrainsystem.dal.query.UserFilterQuery;
import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.biz.service.AccountService;
import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseDTO<Page<User>> getUsers(@RequestParam Integer page, @RequestParam Integer size
        , UserFilterQuery userFilterQuery) {
        return accountService.getUsers(page, size, userFilterQuery);
    }

    @RequestMapping(value = "/user/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<User> getUser(@PathVariable String studentId) {
        log.info("get student with: student id: {}", studentId);
        return accountService.getUser(studentId);
    }

    @RequestMapping(value = "/user/by-phone/{phone}/validation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO login(@PathVariable String phone) {
        log.info("user login first with: phone: {}", phone);
        return accountService.userLogin(phone);
    }

    @RequestMapping(value = "/user/by-phone/{phone}/first-validation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO loginFisrt(@PathVariable String phone,
        @RequestParam String name, @RequestParam String studentId, @RequestParam(required = false) Integer classId) {
        log.info("user login with: phone: {}, name: {}, classId: {}, studentId: {}",
            phone, name, classId, studentId);
        return accountService.userLoginFirst(name, phone, studentId, classId);
    }
}
