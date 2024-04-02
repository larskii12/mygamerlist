package com.app.mygamerlist.api.rating.model;


import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.common.jpa.entity.BasicIdEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating extends BasicIdEntity {

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RatingType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
