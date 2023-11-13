package io.dongvelop.springbootquerydsltutorial.board.dto.response;

import java.time.LocalDateTime;

public record BoardListResponse(

        Long id,
        String title,
        Long views,
        Long likeCount,
        LocalDateTime createdAt
) {
}
