package com.study.test.domain.comment.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommentRequest {


    @NotNull(message = "게시글이 없습니다.")
    private Long boardId;

    @NotBlank(message = "댓글 내용이 없습니다.")
    private String commentContent;


}
