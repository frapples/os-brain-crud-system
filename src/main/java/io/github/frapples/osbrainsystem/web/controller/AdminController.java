package io.github.frapples.osbrainsystem.web.controller;

import com.google.common.base.Preconditions;
import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.Admin;
import io.github.frapples.osbrainsystem.biz.service.AdminService;
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
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<Object> addAdmin(Admin admin) {
        Preconditions.checkArgument(false);
        /* TODO validate */

        log.info("add admin with: {}", admin);
        return adminService.addAdmin(admin);
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<Admin> getAdmin(@PathVariable Integer id) {
        Preconditions.checkArgument(false);
        /* TODO validate */

        log.info("get admin with: {}", id);
        return adminService.getAdmin(id);
    }


    @RequestMapping(value = "/admin/{name}/validation", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDTO check(@PathVariable String name, @RequestParam String password) {
        log.info("get admin with: name:{}, password: {}", name, password);
        return adminService.check(name, password);
    }

}
