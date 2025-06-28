package com.study.test.domain.board;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, Long> {




    Board findByBoardIdAndBoardDeleteFlag(@NotNull(message = "게시글이 없습니다.") Long boardId, Board.DeleteStatus deleteStatus);

}
