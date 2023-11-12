package io.dongvelop.springbootquerydsltutorial.like;

import io.dongvelop.springbootquerydsltutorial.board.Board;
import io.dongvelop.springbootquerydsltutorial.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    public Like(final Board board, final Member member) {
        this.setBoard(board);
        this.setMember(member);
    }

    private void setBoard(final Board board) {
        this.board = board;
        board.getLikes().add(this);
    }

    private void setMember(final Member member) {
        this.member = member;
        member.getLikes().add(this);
    }
}
