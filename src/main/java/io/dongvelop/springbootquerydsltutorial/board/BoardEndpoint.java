package io.dongvelop.springbootquerydsltutorial.board;

import io.dongvelop.springbootquerydsltutorial.board.dto.request.RegisterBoardRequest;
import io.dongvelop.springbootquerydsltutorial.board.dto.response.BoardListResponse;
import io.dongvelop.springbootquerydsltutorial.board.dto.response.BoardResponse;
import io.dongvelop.springbootquerydsltutorial.common.SortType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardEndpoint {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> registerBoard(@RequestBody @Valid final RegisterBoardRequest registerBoardRequest) {

        log.info("registerBoardRequest[{}]", registerBoardRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(boardService.registerBoard(registerBoardRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> readBoard(@PathVariable("id") final Long boardId) {

        log.info("boardId[{}]", boardId);
        return ResponseEntity.ok(boardService.readBoard(boardId));
    }

    @GetMapping
    public ResponseEntity<Slice<BoardListResponse>> readBoardList(@RequestParam(defaultValue = "default") final String sortBy,
                                                                  @PageableDefault(size = 3) final Pageable pageable) {

        log.info("sortBy[{}]", sortBy);
        return ResponseEntity.ok(boardService.readBoardList(SortType.valueOf(sortBy.toUpperCase()), pageable));
    }
}
