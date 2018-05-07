package io.github.frapples.osbrainsystem.biz.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Strings;
import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.QuestionDO;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements Converter<Question, QuestionDO> {

    @Data
    public static class OptionItem {

        private String label;
        private String content;
    }

    private String content;
    private String answerContent;
    private String type;
    private List<OptionItem> choiseOption;

    @Override
    public Question convertFrom(QuestionDO DO) {
        Converter.defaultConvert(DO, this);
        if (!Strings.isNullOrEmpty(DO.getChoiseOption())) {
            List<OptionItem> option = JSON.parseObject(DO.getChoiseOption(), new TypeReference<List<OptionItem>>() {
            });
            this.setChoiseOption(option);
        }
        return this;
    }

}
