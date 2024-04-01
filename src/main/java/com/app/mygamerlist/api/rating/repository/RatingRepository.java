package com.app.mygamerlist.api.rating.repository;

import com.app.mygamerlist.api.rating.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
}
