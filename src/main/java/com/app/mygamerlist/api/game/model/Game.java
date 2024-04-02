package com.app.mygamerlist.api.game.model;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.common.jpa.entity.BasicIdEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BasicIdEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "developer", nullable = false)
    private String developer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @JsonManagedReference
    private List<Character> characters;

    @ManyToMany(mappedBy = "playedGames")
    private Set<User> users;
}
