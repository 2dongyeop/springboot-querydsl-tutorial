INSERT INTO member(name)
VALUES ('test1'),
       ('test2');

INSERT INTO board(title, content, views, writer_id) VALUES ('title', 'content', 0, 2);
INSERT INTO board(title, content, views, writer_id) VALUES ('title2', 'content2', 10, 2);
INSERT INTO board(title, content, views, writer_id) VALUES ('title3', 'content3', 100, 1);
INSERT INTO board(title, content, views, writer_id) VALUES ('title4', 'content4', 12, 1);
INSERT INTO board(title, content, views, writer_id) VALUES ('title5', 'content5', 120, 2);
INSERT INTO board(title, content, views, writer_id) VALUES ('title6', 'content6', 5, 1);
INSERT INTO board(title, content, views, writer_id) VALUES ('title7', 'content7', 20, 2);

INSERT INTO likes(board_id, member_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 2);