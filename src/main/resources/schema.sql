CREATE TABLE IF NOT EXISTS member
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS board
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(255)            NOT NULL,
    content    VARCHAR(255)            NOT NULL,
    views      INT CHECK (views >= 0)  NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL,
    writer_id  BIGINT NOT NULL,
    CONSTRAINT FK_board_member_id FOREIGN KEY (writer_id) REFERENCES member (id)
);

CREATE TABLE IF NOT EXISTS likes
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    board_id   BIGINT NOT NULL,
    member_id  BIGINT NOT NULL,

    CONSTRAINT FK_likes_board_id  FOREIGN KEY (board_id) REFERENCES board (id),
    CONSTRAINT FK_likes_member_id FOREIGN KEY (member_id) REFERENCES member (id)
);