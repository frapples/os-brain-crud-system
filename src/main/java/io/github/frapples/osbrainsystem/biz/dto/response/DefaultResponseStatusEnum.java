package io.github.frapples.osbrainsystem.biz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DefaultResponseStatusEnum implements ResponseStatusEnumInterface {
    SUCCESS_STATUS("SUCCESS"),
    NOT_EXIST_STATUS("NOT_EXIST");

    private String status;
}

