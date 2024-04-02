package com.app.mygamerlist.api.rating.model;

import com.app.mygamerlist.api.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class RatingDto {

    @Column(name = "value", nullable = false)
    private int value;

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
