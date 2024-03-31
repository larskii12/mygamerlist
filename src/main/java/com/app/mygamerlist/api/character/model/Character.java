package com.app.mygamerlist.api.character.model;

import com.app.mygamerlist.api.game.model.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Boolean isEnemy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Game game;
}
