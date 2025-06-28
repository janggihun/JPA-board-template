package com.study.test.domain.board;


import com.study.test.domain.board.dto.InsertBoardRequest;
import com.study.test.domain.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//table : board
@Getter
@Entity
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId; // 게시글 식별번호

    @Column(name = "board_title")
    private String boardTitle; //게시글 제목

    @Column(name = "board_content")
    private String boardContent; //게시글 내용

    @Column(name = "board_pw")
    private String boardPw; // 게시글 비밀번호

    @Column(name = "board_writer")
    private String boardWriter; // 게시글 작성자

    @Column(name = "board_view")
    private int boardView;// 조회수

    @Column(name = "board_insertDate")
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime boardInsertDate; //저장날짜

    @Column(name = "board_updateDate")
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime boardUpdateDate; //변경날짜

    @Column(name = "board_deleteFlag")
    @Enumerated(EnumType.STRING)
    private DeleteStatus boardDeleteFlag; // 삭제플러그 , enum


    public void createSelf(InsertBoardRequest insertBoardRequest) {

        this.boardTitle = insertBoardRequest.getBoardTitle();
        this.boardContent = insertBoardRequest.getBoardContent();
        this.boardPw = insertBoardRequest.getBoardPw();

        this.boardWriter = insertBoardRequest.getBoardWriter();
        this.boardView = 0;
        this.boardInsertDate = LocalDateTime.now();
        this.boardUpdateDate = LocalDateTime.now();
        this.boardDeleteFlag = DeleteStatus.ACTIVE;
    }


    @Getter
    public enum DeleteStatus {
        ACTIVE("정상"),
        DELETED("삭제됨");

        private final String description;

        DeleteStatus(String description) {
            this.description = description;
        }

    }

    //엔티티의 게시글 내용 변경
    public void changeContent(String content) {

        this.boardContent = content;
    }

    //딜리트 플레그 변경
    public void updateDelete() {

        this.boardDeleteFlag = DeleteStatus.DELETED;
    }

    public void updateView() {

        this.boardView += 1;

    }

    //댓글
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> commentList = new ArrayList<>();



}

