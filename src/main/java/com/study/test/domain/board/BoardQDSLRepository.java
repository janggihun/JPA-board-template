package com.study.test.domain.board;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.test.domain.board.dto.PageDto;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.util.StringUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.study.test.domain.board.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardQDSLRepository {
    private final JPAQueryFactory queryFactory;


    public long boardAllCount(PageDto pageDto) {

        return queryFactory
                .select(board)
                .from(board)
                .where(board.boardDeleteFlag.eq(Board.DeleteStatus.ACTIVE).and(check_search(pageDto)))
                .fetchCount();
    }

    public List<Board> readBoard(PageDto pageDto) {

        return queryFactory
                .select(board)
                .from(board)
                .where(board.boardDeleteFlag.eq(Board.DeleteStatus.ACTIVE).and(check_search(pageDto)))
                .orderBy(board.boardId.desc())
                .offset((pageDto.getNowPage() - 1) * pageDto.getCount())
                .limit(pageDto.getCount())
                .fetch();
    }

    public BooleanExpression check_search(PageDto pageDto) {

        if (pageDto == null) {

            return null;
        } else {
            if (StringUtils.hasText(pageDto.getSearch())) {

                return board.boardTitle.contains(pageDto.getSearch()).or(board.boardWriter.contains(pageDto.getSearch()));

            } else {
                return null;
            }
        }
    }


    public BooleanExpression check_title_Search(PageDto pageDto) {

        return StringUtils.hasText(pageDto.getSearch()) ? board.boardTitle.contains(pageDto.getSearch()) : null;


    }


    public BooleanExpression check_writer_search(PageDto pageDto) {

        return StringUtils.hasText(pageDto.getSearch()) ? board.boardWriter.contains(pageDto.getSearch()) : null;


    }


}
