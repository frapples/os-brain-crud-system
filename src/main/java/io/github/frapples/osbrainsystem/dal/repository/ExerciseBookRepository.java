package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.ExerciseBook;
import io.github.frapples.osbrainsystem.biz.model.Question;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseBbookQuestionRelationDO;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseBookDO;
import io.github.frapples.osbrainsystem.dal.mapper.ExerciseBookMapper;
import io.github.frapples.osbrainsystem.dal.mapper.ExerciseBookQuestionRelationMapper;
import io.github.frapples.osbrainsystem.dal.mapper.QuestionMapper;
import io.github.frapples.osbrainsystem.dal.utils.CrudRepositoryUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseBookRepository {

    @Autowired
    private ExerciseBookMapper exerciseBookMapper;

    @Autowired
    private ExerciseBookQuestionRelationMapper exerciseBookQuestionRelationMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<ExerciseBook> getBooks() {
        List<ExerciseBookDO> dos = exerciseBookMapper.selectList(new EntityWrapper<>());
        List<ExerciseBook> books = ModelConverter.convert(dos, ExerciseBook.class);
        return books;
    }


    public Optional<ExerciseBook> getBookById(Integer id) {
        return CrudRepositoryUtils.getItemByUnique(
            exerciseBookMapper,
            ExerciseBook.class,
            ExerciseBookDO.class,
            "id",
            id);
    }

    public List<Integer> getQuestionsIdByBookId(Integer id) {
        if (id == null) {
            return Lists.newArrayList();
        } else {
            return exerciseBookQuestionRelationMapper.selectList(
                new EntityWrapper<ExerciseBbookQuestionRelationDO>()
                    .eq("exercise_book_id", id)).stream()
                .map(ExerciseBbookQuestionRelationDO::getExerciseBookId)
                .collect(Collectors.toList());
        }
    }


    public List<Question> getQuestionsByBookId(Integer id) {
        List<Integer> ids = getQuestionsIdByBookId(id);
        return ids.stream()
            .map(questionMapper::selectById)
            .map((q) -> ModelConverter.convert(q, Question.class))
            .collect(Collectors.toList());
    }

}
