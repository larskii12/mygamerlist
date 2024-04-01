package com.app.mygamerlist.api.rating.model;


import com.app.mygamerlist.common.jpa.entity.BasicIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating extends BasicIdEntity {

    @Column(nullable = false)
    private int value;

    @Column
    private String comment;

    @Column(nullable = false)
    private Long entityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RatingType ratingType;
}
