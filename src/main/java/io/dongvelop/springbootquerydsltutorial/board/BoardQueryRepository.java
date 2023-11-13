package io.dongvelop.springbootquerydsltutorial.board;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dongvelop.springbootquerydsltutorial.common.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Board> getBoardList(final Pageable pageable, final SortType sortType) {

        final QBoard board = QBoard.board;

        return queryFactory
                .selectFrom(board)
                .orderBy(ordering(board, sortType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private OrderSpecifier<?> ordering(final QBoard board, final SortType sortType) {

        return switch (sortType) {
            case VIEWS -> board.views.desc();
            case LATEST -> board.createdAt.desc();
            default -> board.id.desc();
        };
    }
}
