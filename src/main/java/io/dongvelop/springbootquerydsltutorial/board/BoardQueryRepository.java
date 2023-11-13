package io.dongvelop.springbootquerydsltutorial.board;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dongvelop.springbootquerydsltutorial.board.dto.response.BoardListResponse;
import io.dongvelop.springbootquerydsltutorial.common.SortType;
import io.dongvelop.springbootquerydsltutorial.like.QLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<BoardListResponse> getBoardList(final Pageable pageable, final SortType sortType) {

        final QBoard board = QBoard.board;
        final QLike like = QLike.like;

        /* 쿼리 조회 결과를 바로 DTO로 생성 */
        final ConstructorExpression<BoardListResponse> boardListResponse =
                Projections.constructor(
                        BoardListResponse.class,
                        board.id,
                        board.title,
                        board.views,
                        like.count(), // 집계 함수
                        board.createdAt
                );

        return queryFactory
                .query()
                .select(boardListResponse)
                .from(board)
                .leftJoin(like)
                .on(like.board.eq(board))
                .groupBy(board.id)
                .orderBy(ordering(board, like, sortType)) // 집계함수 사용했으니 그룹바이~
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private OrderSpecifier<?> ordering(final QBoard board, final QLike like, final SortType sortType) {

        return switch (sortType) {
            case VIEWS -> board.views.desc();
            case LATEST -> board.createdAt.desc();
            case LIKES -> like.count().desc();
            default -> board.id.desc();
        };
    }
}
