package com.study.test.domain.comment;


import com.study.test.domain.board.Board;
import com.study.test.domain.board.BoardJpaRepository;
import com.study.test.domain.comment.dto.ReplyCommentRequest;
import com.study.test.domain.comment.dto.SaveCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final BoardJpaRepository boardJpaRepository;

    private final CommentJpaRepository commentJpaRepository;

    public void save(SaveCommentRequest saveCommentRequest) {


        Board board = boardJpaRepository.findById(saveCommentRequest.getBoardId()).orElseThrow();

        Comment comment = new Comment();

        comment.inputBoard(board);

        comment.inputContent(saveCommentRequest.getCommentContent());

        commentJpaRepository.save(comment);


    }

    public void saveReply(ReplyCommentRequest replyCommentRequest) {


        Board board = boardJpaRepository.findById(replyCommentRequest.getBoardId()).orElseThrow();

        Comment comment = commentJpaRepository.findById(replyCommentRequest.getCommentId()).orElseThrow();


        Comment parnetComment = new Comment();
        parnetComment.inputBoard(board);
        parnetComment.inputContent(replyCommentRequest.getReplyContent());

        parnetComment.addChild(comment);

        commentJpaRepository.save(parnetComment);

    }
}
