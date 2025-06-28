package com.study.test.domain.board.dto;


import com.study.test.domain.board.Board;
import com.study.test.domain.comment.Comment;
import com.study.test.domain.comment.dto.CommentDto;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReadPostResponse {

    private Long boardId; // 게시글 식별번호

    private String boardTitle; //게시글 제목

    private String boardContent; //게시글 내용

    private String boardWriter; // 게시글 작성자

    private int boardView;// 조회수

    private String boardInsertDate; //저장날짜

    private String boardUpdateDate; //변경날짜

    private List<CommentDto> commentList; // 댓글 내용

    public void inputSelf(Board board, List<CommentDto> commentList) {


        String boardInsertDate = board.getBoardInsertDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String boardUpdateDate = board.getBoardUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        this.boardId = board.getBoardId();

        this.boardTitle = board.getBoardTitle();

        this.boardContent = board.getBoardContent();

        this.boardWriter = board.getBoardWriter();

        this.boardView = board.getBoardView();

        this.boardInsertDate = boardInsertDate;

        this.boardUpdateDate = boardUpdateDate;

        this.commentList = commentList;

    }

}
