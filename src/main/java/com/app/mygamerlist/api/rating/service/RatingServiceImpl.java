package com.app.mygamerlist.api.rating.service;

import com.app.mygamerlist.api.rating.model.Rating;
import com.app.mygamerlist.api.rating.repository.RatingRepository;
import com.app.mygamerlist.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.mygamerlist.common.exception.NotFoundException.GAME;
import static java.util.Objects.isNull;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Iterable<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Iterable<Rating> findAllRatingsByGame(Long gameId, String title) {
        if (isNull(gameId) && isNull(title)) {
            return findAll();
        } else if (isNull(title)) {
            return ratingRepository.findRatingsByGameId(gameId);
        } else if (isNull(gameId)) {
            return ratingRepository.findRatingsByGameTitle(title);
        } else {
            return ratingRepository.findRatingsByGameIdAndTitle(gameId, title);
        }
    }

    @Override
    public Iterable<Rating> findAllRatingsByCharacter(Long characterId, String name) {
        if (isNull(characterId) && isNull(name)) {
            return findAll();
        } else if (isNull(name)) {
            return ratingRepository.findRatingsByCharacterId(characterId);
        } else if (isNull(characterId)) {
            return ratingRepository.findRatingsByCharacterName(name);
        } else {
            return ratingRepository.findRatingsByCharacterIdAndName(characterId, name);
        }
    }

    @Override
    public Iterable<Rating> findAllRatingsByUser(Long userId, String username) {
        if (isNull(userId) && isNull(username)) {
            return findAll();
        } else if (isNull(username)) {
            return ratingRepository.findRatingsByUserId(userId);
        } else if (isNull(userId)) {
            return ratingRepository.findRatingsByUsername(username);
        } else {
            return ratingRepository.findRatingsByUserIdAndUsername(userId, username);
        }
    }

    @Override
    public Rating findRatingById(Long id) {
        return ratingRepository.findById(id).orElseThrow();
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long id, Rating rating) {
        Optional<Rating> ratingFromDbOpt = ratingRepository.findById(id);

        if (ratingFromDbOpt.isEmpty()) {
            throw new NotFoundException(GAME);
        }

        Rating ratingFromDb = ratingFromDbOpt.get();

        ratingFromDb.setRate(rating.getRate());
        ratingFromDb.setComment(rating.getComment());
        ratingFromDb.setType(rating.getType());
        ratingFromDb.setEntityId(rating.getEntityId());
        ratingRepository.findById(id)
                .orElseThrow();
        return ratingRepository.save(ratingFromDb);
    }

    @Override
    public void deleteById(Long id) {
        if (!ratingRepository.existsById(id)) {
            throw new NotFoundException(GAME);
        }
        ratingRepository.deleteById(id);
    }
}
