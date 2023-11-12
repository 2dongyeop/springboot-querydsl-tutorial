package io.dongvelop.springbootquerydsltutorial.member;

import io.dongvelop.springbootquerydsltutorial.board.Board;
import io.dongvelop.springbootquerydsltutorial.like.Like;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private final Set<Board> boards = new HashSet<>();

    @OneToMany(mappedBy = "member")
    private final Set<Like> likes = new HashSet<>();

    public Member(final String name) {
        this.name = name;
    }
}
