package com.app.mygamerlist.api.rating.repository;

import com.app.mygamerlist.api.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT * " +
            "FROM rating r " +
            "WHERE r.entity_id = :gameId and r.type = 'GAME'",
            nativeQuery = true)
    List<Rating> findRatingsByGameId(Long gameId);


    @Query(value = "SELECT * " +
            "FROM rating r " +
            "WHERE r.user_id = :userId",
            nativeQuery = true)
    List<Rating> findRatingsByUserId(Long userId);


    @Query(value = "SELECT * " +
            "FROM rating r " +
            "WHERE r.entity_id = :characterId and r.type = 'CHARACTER'",
            nativeQuery = true)
    List<Rating> findRatingsByCharacterId(Long characterId);

}
