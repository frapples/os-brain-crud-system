package io.github.frapples.osbrainsystem.web.controller;

import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.Question;
import io.github.frapples.osbrainsystem.biz.model.QuestionTypeEnum;
import io.github.frapples.osbrainsystem.biz.service.QuestionService;
import java.util.EnumMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<Object> addQuestion(Question question) {
        log.info("add question with: {}", question);
        return questionService.addQuestion(question);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<List<Question>> getQuestions(@PathVariable Integer id) {
        log.info("get question with: {}", id);
        return questionService.getQuestions();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<Question> getQuestion(@PathVariable Integer id) {
        log.info("get question with: {}", id);
        return questionService.getQuestion(id);
    }

    @RequestMapping(value = "/grouped", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<EnumMap<QuestionTypeEnum, List<Question>>> getQuestionGrouped() {
        return questionService.getQuestionGrouped();
    }

}
