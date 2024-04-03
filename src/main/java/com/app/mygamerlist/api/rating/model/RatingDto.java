package com.app.mygamerlist.api.rating.model;

import com.app.mygamerlist.api.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class RatingDto {

    private int rate;

    private String comment;

    private Long entityId;

    private RatingType type;

    private Long userId;
}
