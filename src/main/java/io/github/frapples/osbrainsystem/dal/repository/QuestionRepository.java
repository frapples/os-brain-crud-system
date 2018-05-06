package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.Question;
import io.github.frapples.osbrainsystem.biz.model.QuestionTypeEnum;
import io.github.frapples.osbrainsystem.dal.dao.QuestionDO;
import io.github.frapples.osbrainsystem.dal.mapper.QuestionMapper;
import io.github.frapples.osbrainsystem.dal.utils.CrudRepositoryUtils;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository {

    @Autowired
    private QuestionMapper questionMapper;

    public boolean addQuestion(Question question) {
        return CrudRepositoryUtils.insert(questionMapper, question, QuestionDO.class);
    }


    public List<Question> getQuestions() {
        List<QuestionDO> dos = questionMapper.selectList(new EntityWrapper<>());
        List<Question> questions = ModelConverter.convert(dos, Question.class);
        return questions;
    }

    public Optional<Question> getQuestionById(Integer id) {
        return CrudRepositoryUtils.getItemByUnique(
            questionMapper,
            Question.class,
            QuestionDO.class,
            "id",
            id);
    }

    public boolean updateQuestion(Question question) {
        QuestionDO questionDO = ModelConverter.convert(question, QuestionDO.class);
        return questionMapper.updateById(questionDO) >= 1;
    }

    public EnumMap<QuestionTypeEnum, List<Question>> getQuestionGrouped() {
        EnumMap<QuestionTypeEnum, List<Question>> maps = Maps.newEnumMap(QuestionTypeEnum.class);

        for (QuestionTypeEnum type : QuestionTypeEnum.values()) {
            List<QuestionDO> dos = questionMapper.selectList(
                new EntityWrapper<QuestionDO>()
                    .eq("type", type.getType()));
            List<Question> result = ModelConverter.convert(dos, Question.class);
            maps.put(type, result);
        }

        return maps;
    }

}
