package com.study.test.domain.board.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardRequest {


    @NotNull(message = "게시글이 없습니다.")
    private Long boardId;

    @NotBlank(message = "변경 내용이 없습니다.")
    private String content;


}
