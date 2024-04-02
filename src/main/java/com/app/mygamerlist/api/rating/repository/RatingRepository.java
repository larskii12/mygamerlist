package com.app.mygamerlist.api.rating.repository;

import com.app.mygamerlist.api.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT * " +
            "FROM rating r JOIN game g ON r.entity_id = g.id" +
            "WHERE r.entity_id = ?1, r.type = 'GAME', and g.title = ?2")
    Iterable<Rating> findRatingsByGameIdAndTitle(Long gameId, String title);

    @Query("SELECT * " +
            "FROM rating r " +
            "WHERE r.entity_id = ?1 and r.type = 'GAME'")
    Iterable<Rating> findRatingsByGameId(Long gameId);

    @Query("SELECT * " +
            "FROM rating r JOIN game g ON r.entity_id = g.id" +
            "WHERE g.title = ?1 and r.type = 'GAME'")
    Iterable<Rating> findRatingsByGameTitle(String title);

    @Query("SELECT * " +
            "FROM rating r JOIN user u ON r.user_id = u.id" +
            "WHERE r.user_id = ?1, and u.username = ?2")
    Iterable<Rating> findRatingsByUserIdAndUsername(Long userId, String username);

    @Query("SELECT * " +
            "FROM rating r " +
            "WHERE r.user_id = ?1")
    Iterable<Rating> findRatingsByUserId(Long userId);

    @Query("SELECT * " +
            "FROM rating r JOIN user u ON r.user_id = u.id" +
            "WHERE u.username = ?1")
    Iterable<Rating> findRatingsByUsername(String username);

    @Query("SELECT * " +
            "FROM rating r JOIN character c ON r.entity_id = c.id" +
            "WHERE r.entity_id = ?1, r.type = 'CHARACTER', and c.name = ?2")
    Iterable<Rating> findRatingsByCharacterIdAndName(Long characterId, String name);

    @Query("SELECT * " +
            "FROM rating r " +
            "WHERE r.entity_id = ?1 and r.type = 'CHARACTER'")
    Iterable<Rating> findRatingsByCharacterId(Long characterId);

    @Query("SELECT * " +
            "FROM rating r JOIN character c ON r.entity_id = c.id" +
            "WHERE c.name = ?1 and r.type = 'CHARACTER'")
    Iterable<Rating> findRatingsByCharacterName(String name);
}
