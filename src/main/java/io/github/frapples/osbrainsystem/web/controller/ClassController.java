package io.github.frapples.osbrainsystem.web.controller;

import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.SchoolClass;
import io.github.frapples.osbrainsystem.biz.service.AccountService;
import java.util.List;
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
public class ClassController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/class", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addClass(SchoolClass schoolClass) {
        log.info("add class with: {}", schoolClass);
        return accountService.addClass(schoolClass);
    }


    @RequestMapping(value = "/class", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<List<SchoolClass>> getClasses() {
        log.info("get classes");
        return accountService.getClasses();
    }

    @RequestMapping(value = "/class/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDTO updateClass(@PathVariable Integer id,
        @RequestParam String name,
        @RequestParam Integer startYear) {
        SchoolClass schoolClass = SchoolClass.builder()
            .id(id)
            .name(name)
            .startYear(startYear)
            .build();
        log.info("get class with: id: {}", id);
        schoolClass.setId(id);
        return accountService.updateClass(schoolClass);
    }

    @RequestMapping(value = "/class/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<SchoolClass> getClass(@PathVariable Integer id) {
        log.info("get class with: id: {}", id);
        return accountService.getClassById(id);
    }

}
