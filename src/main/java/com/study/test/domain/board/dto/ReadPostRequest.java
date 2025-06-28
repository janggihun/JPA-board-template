package com.study.test.domain.board.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReadPostRequest {


    @NotNull(message = "게시글이 없습니다.")
    private Long boardId;










}
