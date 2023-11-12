package io.dongvelop.springbootquerydsltutorial.board;

import io.dongvelop.springbootquerydsltutorial.like.Like;
import io.dongvelop.springbootquerydsltutorial.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Long views;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private final Set<Like> likes = new HashSet<>();

    public Board(final Member member, final String title, final String content) {

        this.setMember(member);
        this.title = title;
        this.content = content;
        this.views = 0L;
        this.createdAt = LocalDateTime.now();
    }

    private void setMember(final Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void increaseView() {
        this.views++;
    }
}
