package io.github.frapples.osbrainsystem.biz.dto.response.apistatus;

import io.github.frapples.osbrainsystem.biz.dto.response.ResponseStatusEnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskReplyStatusEnum implements ResponseStatusEnumInterface {

    TIME_NOT_MATCH_STATUS("TIME_NOT_MATCH");

    private String status;
}
