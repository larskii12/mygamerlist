package com.app.mygamerlist.api.character.model;

import com.app.mygamerlist.api.game.model.Game;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
public class CharacterDto {

    private String name;

    private String description;

    private Boolean isEnemy;

    private Long gameId;
}
