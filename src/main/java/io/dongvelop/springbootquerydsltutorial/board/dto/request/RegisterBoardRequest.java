package io.dongvelop.springbootquerydsltutorial.board.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterBoardRequest(
        @NotBlank String title,
        @NotBlank String content
) {
}
