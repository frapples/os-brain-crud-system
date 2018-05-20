package io.github.frapples.osbrainsystem.biz.dto.response.apistatus;


import io.github.frapples.osbrainsystem.biz.dto.response.ResponseStatusEnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserLoginStatusEnum implements ResponseStatusEnumInterface {
    USER_EXISTS_STATUS("USER_EXISTS"),
    USER_NOT_EXISTS_STATUS("USER_NOT_EXISTS"),
    STUDENTID_EXISTS_STATUS("STUDENTID_EXISTS");

    private String status;
}
