package com.study.test.domain.board.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadBoardReqeust {

    @NotNull(message = "페이지가 없습니다.")
    private int nowPage;


}
