package com.app.mygamerlist.api.user.model;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.rating.model.Rating;
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
public class User extends BasicIdEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany
    @JoinTable(
            name = "game_played",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> playedGames;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rating")
    @JsonManagedReference
    private List<Rating> ratings;

}
