package io.github.frapples.osbrainsystem.web.controller;

import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/api/exercise-book")
public class ExerciseBookController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getAllBooks() {
        log.info("get all books");
        return questionService.getBooks();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getBookById(@PathVariable Integer id) {
        log.info("get book by id: {}", id);
        return questionService.getBookById(id);
    }

    @RequestMapping(value = "/{id}/question-id", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getQuestionIds(@PathVariable Integer id) {
        log.info("get question ids by id: {}", id);
        return questionService.getBookById(id);
    }

    @RequestMapping(value = "/{id}/question", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getQuestions(@PathVariable Integer id) {
        log.info("get questions by id: {}", id);
        return questionService.getBookById(id);
    }
}
