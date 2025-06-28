package com.study.test.domain.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 284809630L;

    public static final QBoard board = new QBoard("board");

    public final StringPath boardContent = createString("boardContent");

    public final EnumPath<Board.DeleteStatus> boardDeleteFlag = createEnum("boardDeleteFlag", Board.DeleteStatus.class);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> boardInsertDate = createDateTime("boardInsertDate", java.time.LocalDateTime.class);

    public final StringPath boardPw = createString("boardPw");

    public final StringPath boardTitle = createString("boardTitle");

    public final DateTimePath<java.time.LocalDateTime> boardUpdateDate = createDateTime("boardUpdateDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> boardView = createNumber("boardView", Integer.class);

    public final StringPath boardWriter = createString("boardWriter");

    public final ListPath<com.study.test.domain.comment.Comment, com.study.test.domain.comment.QComment> commentList = this.<com.study.test.domain.comment.Comment, com.study.test.domain.comment.QComment>createList("commentList", com.study.test.domain.comment.Comment.class, com.study.test.domain.comment.QComment.class, PathInits.DIRECT2);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

