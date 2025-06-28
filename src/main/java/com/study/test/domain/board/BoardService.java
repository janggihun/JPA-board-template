package com.study.test.domain.board;


import com.study.test.domain.board.dto.*;
import com.study.test.domain.comment.Comment;
import com.study.test.domain.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    //jpa
    private final BoardJpaRepository boardJpaRepository;
    //쿼리dsl
    private final BoardQDSLRepository boardQDSLRepository;
    //암호화
    private final PasswordEncoder passwordEncoder;

    //게시글 저장
    public String insert(InsertBoardRequest insertBoardRequest) {

        String pw = insertBoardRequest.getBoardPw();

        //비밀번호 암호화
        insertBoardRequest.setBoardPw(passwordEncoder.encode(pw));


        Board board = new Board();
        board.createSelf(insertBoardRequest);

        boardJpaRepository.save(board);
        return "성공";
    }

    //게시글 취득
    public ReadBoardResponse read(PageDto pageDto) {


        //전체 게시글 겟수 조회

        long totalCount = boardQDSLRepository.boardAllCount(pageDto);
        pageDto.create_page(totalCount, pageDto.getNowPage());

        List<Board> boardList = boardQDSLRepository.readBoard(pageDto);

        List<ReadBoardDto> responseList = new ArrayList<>();
        //가공
        for (Board b : boardList) {

            ReadBoardDto rb = new ReadBoardDto(b);
            responseList.add(rb);

        }

        //response

        ReadBoardResponse readBoardResponse = new ReadBoardResponse(pageDto, responseList);

        return readBoardResponse;
    }

    public ReadPostResponse readPost(ReadPostRequest readPostRequest) {

        ReadPostResponse readPostResponse = new ReadPostResponse();


        Board board = boardJpaRepository.findByBoardIdAndBoardDeleteFlag(readPostRequest.getBoardId(), Board.DeleteStatus.ACTIVE);

        List<Comment> commentList = board.getCommentList();

        List<CommentDto>  commentDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentDto commentDto = new CommentDto();
            commentDto.createCommentDto(comment);
            commentDtoList.add(commentDto);

        }
        commentDtoList.sort((a, b) -> Long.compare(b.getCommentId(), a.getCommentId()));

        readPostResponse.inputSelf(board,commentDtoList);

        return readPostResponse;

    }

    public String checkPw(UpdateBoardCheckPwRequest request) {


        Board board = boardJpaRepository.findById(request.getBoardId()).orElseThrow();

        if (passwordEncoder.matches(request.getBoardPw(), board.getBoardPw())) {

            return "비밀번호 일치";
        } else {

            throw new BadCredentialsException("비밀번호가 다릅니다. 확인 부탁드립니다.");
        }


    }

    @Transactional
    public String update(UpdateBoardRequest request) {

        Board board = boardJpaRepository.findById(request.getBoardId()).orElseThrow();

        board.changeContent(request.getContent());

        return "변경 성공";
    }


    @Transactional
    public String delete(DeletePostReqeust request) {

        Board board = boardJpaRepository.findById(request.getBoardId()).orElseThrow();

        if (passwordEncoder.matches(request.getBoardPw(), board.getBoardPw())) {
            board.updateDelete();

            return "삭제 성공";
        } else {

            throw new BadCredentialsException("비밀번호가 다릅니다. 확인 부탁드립니다.");
        }

    }

    @Transactional
    public String updateView(UpdateViewRequest request) {

        Board board = boardJpaRepository.findById(request.getBoardId()).orElseThrow();

        board.updateView();

        return "조회수 증가";
    }
}
