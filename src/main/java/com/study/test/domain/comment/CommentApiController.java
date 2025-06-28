package com.study.test.domain.comment;


import com.study.test.Common.CommonResponse;
import com.study.test.domain.comment.dto.ReplyCommentRequest;
import com.study.test.domain.comment.dto.SaveCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/comment")
public class CommentApiController {


    private final CommentService commentService;


    @PostMapping("/save")
    public CommonResponse save(@RequestBody @Validated SaveCommentRequest saveCommentRequest) {

        commentService.save(saveCommentRequest);

        return CommonResponse.success("저장성공");
    }


    @PostMapping("/saveReply")
    public CommonResponse saveReply(@RequestBody @Validated ReplyCommentRequest replyCommentRequest) {

        commentService.saveReply(replyCommentRequest);

        return CommonResponse.success("저장성공");
    }

}
