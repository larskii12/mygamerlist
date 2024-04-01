package com.app.mygamerlist.api.game.model;

import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.common.jpa.entity.BasicIdEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BasicIdEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    private String developer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<Character> characters;

    @ManyToMany(mappedBy = "playedGames")
    private Set<User> users;
}
