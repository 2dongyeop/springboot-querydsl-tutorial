package io.dongvelop.springbootquerydsltutorial.board.dto.response;

public record BoardResponse(
        Long id,
        String title,
        String content,
        Long views) {
}
