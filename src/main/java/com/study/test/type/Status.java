package com.study.test.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {


    //데이터 삭제 / 사용
    APPLY("사용중"),
    DELETE("삭제");
    
    //또 다른 내용 추가시


    private final String description; // 설명


}
