package com.app.mygamerlist.api.character.model;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.common.jpa.entity.BasicIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character extends BasicIdEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Boolean isEnemy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
}
