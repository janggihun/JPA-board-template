package com.study.test.domain.comment;


import com.study.test.domain.board.Board;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId; //댓글 식별번호

    @Column(name = "comment_content")
    private String commentContent; //댓글내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "comment_writer")
    private String commentWriter;

    @Column(name = "comment_insertDate")
    private LocalDateTime commentInsertDate;

    @Column(name = "comment_deleteFlag")
    @Enumerated(EnumType.STRING)
    private Board.DeleteStatus boardDeleteFlag;


    @Getter
    public enum DeleteStatus {
        ACTIVE("정상"),
        DELETED("삭제됨");

        private final String description;

        DeleteStatus(String description) {
            this.description = description;
        }

    }

    //board주입
    public void inputBoard(Board board) {

        this.board = board;

    }

    //댓글 내용 주입
    public void inputContent(String commentContent) {
        this.commentContent = commentContent;
        this.commentWriter = "비공개";
        this.commentInsertDate = LocalDateTime.now();
        this.boardDeleteFlag = Board.DeleteStatus.ACTIVE;

    }


    //댓글
    @ManyToOne
    @JoinColumn(name = "parent_id") // 외래키 이름
    private Comment parent;

    //대댓글
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childList = new ArrayList<>();


    public void addChild(Comment comment) {

        this.parent = comment;
        this.parent.commentWriter = "비공개";

        //여기에comment를 넣으면 이전의 나를 참조하는데 this를 넣으면 같은댓글을 참조함
        childList.add(this);


    }

}
