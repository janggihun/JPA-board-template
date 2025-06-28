package com.study.test.domain.board.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletePostReqeust {

    @NotNull(message = "게시글이 없습니다.")
    private Long boardId;

    @NotBlank(message = "비밀번호를 확인해주세요")
    private String boardPw;



}
