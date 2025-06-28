package com.study.test.domain.board.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReadBoardResponse {

    //페이지 정보
    private PageDto pageDto;
    
    //게시글정보
    private List<ReadBoardDto> boardList;

    public ReadBoardResponse(PageDto pageDto, List<ReadBoardDto> boardList) {

        this.pageDto = pageDto;
        this.boardList = boardList;
    }
}
