package io.github.frapples.osbrainsystem.web.controller;

import io.github.frapples.osbrainsystem.biz.service.ExerciseRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/api/exercise-record")
public class ExerciseRecordController {

    @Autowired
    private ExerciseRecordService exerciseRecordService;

}
