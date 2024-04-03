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
            "FROM rating r JOIN game g ON r.entity_id = g.id " +
            "WHERE r.entity_id = ?1, r.type = 'GAME', and g.title = ?2",
            nativeQuery = true)
    List<Rating> findRatingsByGameIdAndTitle(Long gameId, String title);

    @Query(value = "SELECT * " +
            "FROM rating r " +
            "WHERE r.entity_id = ?1 and r.type = 'GAME'",
            nativeQuery = true)
    List<Rating> findRatingsByGameId(Long gameId);

    @Query(value = "SELECT * " +
            "FROM rating r JOIN game g ON r.entity_id = g.id " +
            "WHERE g.title = ?1 and r.type = 'GAME'",
            nativeQuery = true)
    List<Rating> findRatingsByGameTitle(String title);

    @Query(value = "SELECT * " +
            "FROM rating r JOIN users u ON r.user_id = u.id " +
            "WHERE r.user_id = ?1, and u.username = ?2",
            nativeQuery = true)
    List<Rating> findRatingsByUserIdAndUsername(Long userId, String username);

    @Query(value = "SELECT * " +
            "FROM rating r " +
            "WHERE r.user_id = ?1",
            nativeQuery = true)
    List<Rating> findRatingsByUserId(Long userId);

    @Query(value = "SELECT * " +
            "FROM rating r JOIN users u ON r.user_id = u.id " +
            "WHERE u.username = ?1",
            nativeQuery = true)
    List<Rating> findRatingsByUsername(String username);

    @Query(value = "SELECT * " +
            "FROM rating r JOIN characters c ON r.entity_id = c.id " +
            "WHERE r.entity_id = ?1, r.type = 'CHARACTER' and c.name = ?2",
            nativeQuery = true)
    List<Rating> findRatingsByCharacterIdAndName(Long characterId, String name);

    @Query(value = "SELECT * " +
            "FROM rating r " +
            "WHERE r.entity_id = ?1 and r.type = 'CHARACTER'",
            nativeQuery = true)
    List<Rating> findRatingsByCharacterId(Long characterId);

    @Query(value = "SELECT * " +
            "FROM rating r JOIN characters c ON r.entity_id = c.id " +
            "WHERE c.name = ?1 and r.type = 'CHARACTER'",
            nativeQuery = true)
    List<Rating> findRatingsByCharacterName(String name);
}
