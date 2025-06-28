package com.study.test.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageDto {

    //단위 , 줄 , count개글씩
    private int count;

    //페이지 줄
    private int pageSize = 10;

    //전체 페이지수
    private int allPage;

    //현재페이지
    private int nowPage;

    //왼쪽(1,11,21,...)
    private int pre;

    //오른쪽(10,20,30,...)
    private int next;

    //이전
    private int leftArrow;

    //이후
    private int rightArrow;

//    //검색어
    private String search;
//
//    //검색방법 / 0 : 제목만 , 1 : 글작성자로
//    private int searchType;
//
//    //검색방법 /0  : 전체날짜 , 그외는 해당날짜들
//    private int beforeDate;

    //전체 페이징 로직
    public void create_page(float allRowCount, int nowPage) {
        //1. 전체 게시글수를 주입,내 페이지주입

        //전체 페이징 =  (전체 글갯수 / count)
        this.allPage = (int) Math.ceil(allRowCount / count);

        this.nowPage = nowPage;

        //2. 계산시작

        //나머지가 0일때는 -1 아닐대는 그대로
        int namegi = (this.nowPage % pageSize);
        if (namegi == 0) {
            this.pre = ((int) Math.floor(this.nowPage / pageSize) - 1) * pageSize + 1;
        } else {
            this.pre = (int) Math.floor(this.nowPage / pageSize) * pageSize + 1;
        }

        this.next = this.pre + pageSize - 1;

        //최종 페이지 갯수와 오른쪽 갯수를 비교해서 더 큰값을 next에 넣어준다.

        if (this.pre < this.next && this.next <= (this.pre + pageSize - 1)) {

            this.next = Math.min(this.next, this.allPage);
        }

        //pre값이 1보다 작으면 1로 고정한다.
        this.pre = Math.max(this.pre, 1);

        //이전 이후

        this.leftArrow = this.pre - 1;

        //leftArrow 첫번째페이징

        if (this.leftArrow <= 1) {
            this.leftArrow = 1;
        }

        //rightArrow 마지막페이징

        this.rightArrow = this.next + 1;

        if (this.allPage < this.rightArrow) {
            this.rightArrow = this.allPage;
        }

    }
}
