package io.github.frapples.osbrainsystem.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseStatusEnum {
    SUCCESS_STATUS("SUCCESS"),
    NOT_EXIST_STATUS("NOT_EXIST");

    private String status;
}

