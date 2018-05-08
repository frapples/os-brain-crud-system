package io.github.frapples.osbrainsystem.biz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO<T> {

    public static final int NORMAL_CODE = 200;
    public static final int ERROR_CODE = 500;

    private Integer code;
    private String status;
    private T data;

    public static <T> ResponseDTO<T> ofSuccess(T data) {
        return new ResponseDTO<T>(NORMAL_CODE, ResponseStatusEnum.SUCCESS_STATUS.getStatus(), data);
    }

    public static <T> ResponseDTO<T> ofFailed(ResponseStatusEnum status) {
        return new ResponseDTO<T>(NORMAL_CODE, status.getStatus(), null);
    }

    public static <T> ResponseDTO<T> ofError() {
        return new ResponseDTO<T>(ERROR_CODE, "SYSTEM_ERROR", null);
    }
}
