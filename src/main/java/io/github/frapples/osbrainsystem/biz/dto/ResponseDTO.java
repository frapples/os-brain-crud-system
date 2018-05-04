package io.github.frapples.osbrainsystem.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO<T> {

    public static final int SUCCESS_CODE = 200;
    public static final int FAILED_CODE = 500;

    private Integer code;
    private String msg;
    private T data;

    public static <T> ResponseDTO<T> ofSuccess(T data) {
        return new ResponseDTO<T>(SUCCESS_CODE, "执行成功", data);
    }

    public static <T> ResponseDTO<T> ofFailed() {
        return new ResponseDTO<T>(FAILED_CODE, "系统错误", null);
    }
}
