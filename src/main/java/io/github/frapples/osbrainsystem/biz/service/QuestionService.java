package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.dto.response.DefaultResponseStatusEnum;
import io.github.frapples.osbrainsystem.biz.model.ExerciseBook;
import io.github.frapples.osbrainsystem.biz.model.Question;
import io.github.frapples.osbrainsystem.biz.model.QuestionTypeEnum;
import io.github.frapples.osbrainsystem.dal.repository.ExerciseBookRepository;
import io.github.frapples.osbrainsystem.dal.repository.QuestionRepository;
import io.github.frapples.osbrainsystem.dal.repository.TaskRepository;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private ExerciseBookRepository exerciseBookRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TaskRepository taskRepository;

    public ResponseDTO<List<ExerciseBook>> getBooks() {
        return ResponseDTO.ofSuccess(
            exerciseBookRepository.getBooks());
    }

    public ResponseDTO<ExerciseBook> getBookById(Integer id) {
        return exerciseBookRepository.getBookById(id)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(DefaultResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO<List<Integer>> getQuestionsIdByBookId(Integer id) {
        return ResponseDTO.ofSuccess(
            exerciseBookRepository.getQuestionsIdByBookId(id));
    }

    public ResponseDTO<List<Question>> getQuestionsByBookId(Integer id) {
        return ResponseDTO.ofSuccess(
            exerciseBookRepository.getQuestionsByBookId(id));
    }


    public ResponseDTO<Object> addQuestion(Question question) {
        boolean success = questionRepository.addQuestion(question);
        return ResponseDTO.ofSuccess(success);
    }


    public ResponseDTO<Question> getQuestion(Integer id) {
        if (id == null) {
            return ResponseDTO.ofFailed(DefaultResponseStatusEnum.NOT_EXIST_STATUS);
        }

        return questionRepository.getQuestionById(id)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(DefaultResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO<List<Question>> getQuestions() {
        return ResponseDTO.ofSuccess(questionRepository.getQuestions());
    }

    public ResponseDTO<EnumMap<QuestionTypeEnum, List<Question>>> getQuestionGrouped() {
        return ResponseDTO.ofSuccess(
            questionRepository.getQuestionGrouped());
    }

    public ResponseDTO addBook(ExerciseBook exerciseBook) {
        return ResponseDTO.ofSuccess(exerciseBookRepository.addBook(exerciseBook));
    }

    public ResponseDTO deleteBook(Integer id) {
        return ResponseDTO.ofSuccess(exerciseBookRepository.deleteBook(id));
    }

    public ResponseDTO updateBook(ExerciseBook exerciseBook) {
        return ResponseDTO.ofSuccess(exerciseBookRepository.updateBook(exerciseBook));
    }

    public ResponseDTO updateBookQuestions(Integer bookId, List<Integer> questionIds) {
        if (bookId == null) {
            return ResponseDTO.ofSuccess(null);
        }

        List<Integer> ids = exerciseBookRepository.getQuestionsIdByBookId(bookId);
        for (int id: ids) {
            if (!questionIds.contains(id)) {
                exerciseBookRepository.deleteBookQuestion(bookId, id);
            }
        }

        for (int id : questionIds) {
            if (!ids.contains(id)) {
                exerciseBookRepository.addBookQuestion(bookId, id);
            }
        }

        return ResponseDTO.ofSuccess(null);
    }

    public ResponseDTO getBookQuestionsGrouped(Integer id) {
        return ResponseDTO.ofSuccess(
            exerciseBookRepository.getBookQuestionsGrouped(id));
    }

    public ResponseDTO deleteBooksQuestion(Integer bookId, Integer questionId) {
        return ResponseDTO.ofSuccess(
            exerciseBookRepository.deleteBookQuestion(bookId, questionId));
    }

    public ResponseDTO<List<ExerciseBook>> getBooksByUsersPhone(String phone) {
        return ResponseDTO.ofSuccess(
            taskRepository.getbooksByUsersPhone(phone));
    }
}
