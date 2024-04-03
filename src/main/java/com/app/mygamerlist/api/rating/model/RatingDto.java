package com.app.mygamerlist.api.rating.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class RatingDto {

    private int rate;

    private String comment;

    private Long entityId;

    private RatingType type;

    private Long userId;
}
