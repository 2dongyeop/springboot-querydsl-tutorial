package io.dongvelop.springbootquerydsltutorial.board;

import io.dongvelop.springbootquerydsltutorial.auth.AuthenticationUtil;
import io.dongvelop.springbootquerydsltutorial.board.dto.request.RegisterBoardRequest;
import io.dongvelop.springbootquerydsltutorial.board.dto.response.BoardListResponse;
import io.dongvelop.springbootquerydsltutorial.board.dto.response.BoardResponse;
import io.dongvelop.springbootquerydsltutorial.common.SortType;
import io.dongvelop.springbootquerydsltutorial.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AuthenticationUtil authenticationUtil;

    public Long registerBoard(final RegisterBoardRequest registerBoardRequest) {

        final Member member = authenticationUtil.getContext();

        final Board board = new Board(
                member,
                registerBoardRequest.title(),
                registerBoardRequest.content());
        log.info("board[{}]", board);

        return boardRepository.save(board).getId();
    }

    public BoardResponse readBoard(final Long boardId) {

        final Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            log.info("boardId[{}] not found", boardId);
            return new IllegalArgumentException();
        });
        log.info("board[{}]", board);

        board.increaseView();

        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getViews()
        );
    }

    public Slice<BoardListResponse> readBoardList(final SortType sortType, final Pageable pageable) {

        log.info("sortType[{}]", sortType);

        final Slice<Board> boardSlice = sort(sortType, pageable);

        return boardSlice.map(board -> new BoardListResponse(
                board.getId(),
                board.getTitle(),
                board.getViews(),
                board.getLikes().size(),
                board.getCreatedAt()
        ));
    }

    private Slice<Board> sort(final SortType sortType, final Pageable pageable) {
        return switch (sortType) {
            case VIEWS -> boardRepository.findAllByOrderByViewsDesc(pageable);
            case LIKES -> boardRepository.findAllByOrderByLikesDesc(pageable);
            default -> boardRepository.findAllByOrderByCreatedAtDesc(pageable);
        };
    }
}
