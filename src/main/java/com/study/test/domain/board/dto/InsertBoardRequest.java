package com.study.test.domain.board.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InsertBoardRequest {

    @NotBlank(message = "작성자가 없습니다.")
    String boardWriter;
    @NotBlank(message = "제목이 없습니다.")
    String boardTitle;
    @NotBlank(message = "내용이 없습니다.")
    String boardContent;
    @NotBlank(message = "비밀번호가 없습니다.")
    String boardPw;
}
