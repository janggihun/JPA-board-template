package com.study.test.Common;


import lombok.Getter;

@Getter
public class CommonResponse<T> {

    private Status status;
    private T data;


    public static <T> CommonResponse success(T data) {

        CommonResponse<T> commonResponse = new CommonResponse<>();

        commonResponse.status = Status.SUCCESS;
        commonResponse.data = data;
        return commonResponse;


    }

    public static <T> CommonResponse error(T data) {

        CommonResponse<T> commonResponse = new CommonResponse<>();

        commonResponse.status = Status.ERROR;
        commonResponse.data = data;
        return commonResponse;


    }

    public enum Status {
        SUCCESS, ERROR
    }


}
