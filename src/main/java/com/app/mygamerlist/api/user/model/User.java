package com.app.mygamerlist.api.user.model;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.common.jpa.entity.BasicIdEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasicIdEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "game_played",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> playedGames;
}
