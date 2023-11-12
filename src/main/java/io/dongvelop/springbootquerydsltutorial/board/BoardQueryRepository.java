package io.dongvelop.springbootquerydsltutorial.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dongvelop.springbootquerydsltutorial.board.dto.response.BoardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Board> getBoardList(final Pageable pageable) {

        final QBoard board = QBoard.board;

        return queryFactory
                .selectFrom(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
