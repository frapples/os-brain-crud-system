package io.github.frapples.osbrainsystem.biz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionTypeEnum {
    SINGLE_CHOICE("SINGLE_CHOICE"),
    MULTI_CHOICE("MULTI_CHOICE"),
    FILL_BLANK("FILL_BLANK"),
    TEXT("TEXT");
    private String type;
}
