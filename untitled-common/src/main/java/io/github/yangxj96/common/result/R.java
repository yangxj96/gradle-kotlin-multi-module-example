package io.github.yangxj96.common.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class R implements Serializable {

    private Integer code;

    private String msg;

    private transient Object data;

    public static R success(String msg) {
        return R
                .builder()
                .code(RStatus.SUCCESS.getCode())
                .msg(msg)
                .build();
    }

    public static R success(Object data) {
        return R
                .builder()
                .code(RStatus.SUCCESS.getCode())
                .msg(RStatus.SUCCESS.getMsg())
                .data(data)
                .build();
    }

}
