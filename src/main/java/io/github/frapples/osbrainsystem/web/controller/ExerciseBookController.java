package io.github.frapples.osbrainsystem.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.ExerciseBook;
import io.github.frapples.osbrainsystem.biz.service.QuestionService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addBook(ExerciseBook exerciseBook) {
        log.info("Add book: {}", exerciseBook);
        return questionService.addBook(exerciseBook);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDTO addBook(@PathVariable Integer id, @RequestParam String name, @RequestParam String comment) {
        ExerciseBook exerciseBook = ExerciseBook.builder()
            .id(id)
            .name(name)
            .comment(comment)
            .build();
        log.info("Edit book: {}", id, exerciseBook);
        return questionService.updateBook(exerciseBook);
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
        return questionService.getQuestionsIdByBookId(id);
    }

    @RequestMapping(value = "/{id}/question", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getQuestions(@PathVariable Integer id) {
        log.info("get questions by id: {}", id);
        return questionService.getBookById(id);
    }

    @RequestMapping(value = "/{bookId}/question/{questionId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDTO deleteBooksQuestion(@PathVariable Integer bookId, @PathVariable Integer questionId) {
        log.info("delete book's question by book id: {}, question id", bookId, questionId);
        return questionService.deleteBooksQuestion(bookId, questionId);
    }

    @RequestMapping(value = "/{id}/question/grouped", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getQuestionsGrouped(@PathVariable Integer id) {
        log.info("get questions grouped by id: {}", id);
        return questionService.getBookQuestionsGrouped(id);
    }

    @RequestMapping(value = "/{id}/question", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDTO updateQuestions(@PathVariable Integer id, @RequestParam("questionIds") String quesionIdsJson) {
        log.info("update questions by id: {}, question ids: {}", id, quesionIdsJson);
        List<Integer> questionIds = JSON.parseObject(quesionIdsJson, new TypeReference<List<Integer>>(){});
        return questionService.updateBookQuestions(id, questionIds);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDTO deleteBook(@PathVariable Integer id) {
        log.info("delete book by id: {}", id);
        return questionService.deleteBook(id);
    }

    @RequestMapping(value = "/userspace/by-phone/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<List<Map<String, Object>>> getBooksByUsersPhone(@PathVariable String phone) {
        log.info("get questions books by phone: {}", phone);
        return questionService.getBooksByUsersPhone(phone);
    }
}
