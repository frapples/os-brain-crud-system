package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.dto.ResponseStatusEnum;
import io.github.frapples.osbrainsystem.biz.model.ExerciseBook;
import io.github.frapples.osbrainsystem.biz.model.Question;
import io.github.frapples.osbrainsystem.biz.model.QuestionTypeEnum;
import io.github.frapples.osbrainsystem.dal.repository.ExerciseBookRepository;
import io.github.frapples.osbrainsystem.dal.repository.QuestionRepository;
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

    public ResponseDTO<List<ExerciseBook>> getBooks() {
        return ResponseDTO.ofSuccess(
            exerciseBookRepository.getBooks());
    }

    public ResponseDTO<ExerciseBook> getBookById(Integer id) {
        return exerciseBookRepository.getBookById(id)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(ResponseStatusEnum.NOT_EXIST_STATUS));
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
            return ResponseDTO.ofFailed(ResponseStatusEnum.NOT_EXIST_STATUS);
        }

        return questionRepository.getQuestionById(id)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(ResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO<List<Question>> getQuestions() {
        return ResponseDTO.ofSuccess(questionRepository.getQuestions());
    }

    public ResponseDTO<EnumMap<QuestionTypeEnum, List<Question>>> getQuestionGrouped() {
        return ResponseDTO.ofSuccess(
            questionRepository.getQuestionGrouped());
    }
}