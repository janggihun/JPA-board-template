package com.study.test.domain.board.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateViewRequest {

    @NotNull(message = "게시글이 존재하지 않습니다.")
    private Long boardId;


}
