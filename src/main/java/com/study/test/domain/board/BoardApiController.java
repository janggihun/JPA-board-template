package com.study.test.domain.board;


import com.study.test.Common.CommonResponse;
import com.study.test.domain.board.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/board")
public class BoardApiController {

    private final BoardService boardService;

    /*
     * 게시글 저장
     * */
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody @Validated InsertBoardRequest insertBoardRequest) {

        log.info("insertBoardRequest : {}", insertBoardRequest);


        boardService.insert(insertBoardRequest);

        return CommonResponse.success("저장 성공");
    }

    /*
     * 게시글 리스트 취득
     * */
    @PostMapping("/read")
    public CommonResponse read(@RequestBody @Validated PageDto PageDto) {

//        log.info("readBoardReqeust : {}", PageDto);
        ;

        return CommonResponse.success(boardService.read(PageDto));
    }

    /*
     * 게시글 1건취득
     * */
    @PostMapping("/readPost")
    public CommonResponse readPost(@RequestBody @Validated ReadPostRequest readPostRequest) {

        return CommonResponse.success(boardService.readPost(readPostRequest));
    }

    /*
     * 비밀번호 확인
     * */
    @PostMapping("/checkPw")
    public CommonResponse checkPw(@RequestBody @Validated UpdateBoardCheckPwRequest request) {

        return CommonResponse.success(boardService.checkPw(request));
    }

    /*
     * 게시글 변경
     * */
    @PostMapping("/update")
    public CommonResponse update(@RequestBody @Validated UpdateBoardRequest request) {

        return CommonResponse.success(boardService.update(request));
    }
    /*
     * 게시글 삭제
     * deleteFlag 값을 변경
     * */
    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody @Validated DeletePostReqeust request) {

        return CommonResponse.success(boardService.delete(request));
    }


    /*
     * 게시글 변경
     * */
    @PostMapping("/updateView")
    public CommonResponse updateView(@RequestBody @Validated UpdateViewRequest request) {

        return CommonResponse.success(boardService.updateView(request));
    }

}
