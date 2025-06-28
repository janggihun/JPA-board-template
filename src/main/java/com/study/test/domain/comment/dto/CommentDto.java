package com.study.test.domain.comment.dto;


import com.study.test.domain.board.Board;
import com.study.test.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {


    private Long commentId; //댓글 식별번호


    private String commentContent; //댓글내용

    private String commentWriter; // 댓글 작성자

    private Long boardId; //댓글번호

    private long parentId;

    public void createCommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentContent = comment.getCommentContent();
        this.boardId = comment.getBoard().getBoardId();
        this.commentWriter = comment.getCommentWriter();

        if(comment.getParent() != null) {


            this.parentId = comment.getParent().getCommentId() ;
        }
        else{

            this.parentId =0L;
        }


    }

}
