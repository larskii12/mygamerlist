package com.app.mygamerlist.api.game.service;

import com.app.mygamerlist.MyGamerListApplication;
import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.model.Genre;
import com.app.mygamerlist.api.game.repository.GameRepository;
import com.app.mygamerlist.common.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;
import static com.app.mygamerlist.common.exception.NotFoundException.GAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {
        GameServiceTest.Config.class,
})
@SpringBootTest(classes = MyGamerListApplication.class)
@Transactional
@RunWith(SpringRunner.class)
public class GameServiceTest {

    @Configuration
    public static class Config {
        @Bean
        GameService gameService() {
            return new GameServiceImpl();
        }
    }

    @Autowired
    EntityManager entityManager;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @After
    public void teardown() {
        gameRepository.deleteAll();
        gameRepository.flush();
    }
    @Test
    public void findAll() {
        Game game1 = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();
        Long gameId1 = gameRepository.save(game1).getId();

        Game game2 = Game.builder()
                .title("Honkai Star Rail")
                .developer("Hoyoverse")
                .genre(Genre.STRATEGY)
                .build();
        Long gameId2 = gameRepository.save(game2).getId();

        Iterable<Game> gameListInDb = gameService.findAll();

        List<Game> result =
                StreamSupport.stream(gameListInDb.spliterator(), false)
                        .toList();

        assertThat(result, hasSize(2));
        assertThat(result.get(0).getId(), is(gameId1));
        assertThat(result.get(0).getTitle(), is("Genshin Impact"));
        assertThat(result.get(0).getDeveloper(), is("Hoyoverse"));
        assertThat(result.get(0).getGenre(), is(Genre.RPG));
        assertThat(result.get(1).getId(), is(gameId2));
        assertThat(result.get(1).getTitle(), is("Honkai Star Rail"));
        assertThat(result.get(1).getDeveloper(), is("Hoyoverse"));
        assertThat(result.get(1).getGenre(), is(Genre.STRATEGY));
    }

    @Test
    public void findGameByTitle() {
        Game game1 = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();
        Long gameId1 = gameRepository.save(game1).getId();

        Game game2 = Game.builder()
                .title("Honkai Star Rail")
                .developer("Hoyoverse")
                .genre(Genre.STRATEGY)
                .build();
        Long gameId2 = gameRepository.save(game2).getId();

        Iterable<Game> gameListInDb = gameService.findGameByTitle("Genshin Impact");

        List<Game> result =
                StreamSupport.stream(gameListInDb.spliterator(), false)
                        .toList();

        assertThat(result, hasSize(1));
        assertThat(result.get(0).getId(), is(gameId1));
        assertThat(result.get(0).getTitle(), is("Genshin Impact"));
        assertThat(result.get(0).getDeveloper(), is("Hoyoverse"));
        assertThat(result.get(0).getGenre(), is(Genre.RPG));
    }

    @Test
    public void findGameById() {
        Game game1 = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();
        Long gameId1 = gameRepository.save(game1).getId();

        Game game2 = Game.builder()
                .title("Honkai Star Rail")
                .developer("Hoyoverse")
                .genre(Genre.STRATEGY)
                .build();
        Long gameId2 = gameRepository.save(game2).getId();

        Game gameListInDb = gameService.findGameById(gameId2);

        assertThat(gameListInDb.getId(), is(gameId2));
        assertThat(gameListInDb.getTitle(), is("Honkai Star Rail"));
        assertThat(gameListInDb.getDeveloper(), is("Hoyoverse"));
        assertThat(gameListInDb.getGenre(), is(Genre.STRATEGY));
    }

    @Test
    public void saveGame() {
        Character character = Character.builder()
                .name("Kazuha")
                .description("Anemo Sword")
                .isEnemy(false)
                .build();

        Game game = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .characters(List.of(character))
                .build();

        Game savedGame = gameService.saveGame(game);

        assertThat(savedGame.getId(), is(savedGame.getId()));
        assertThat(savedGame.getTitle(), is("Genshin Impact"));
        assertThat(savedGame.getDeveloper(), is("Hoyoverse"));
        assertThat(savedGame.getGenre(), is(Genre.RPG));
        assertThat(savedGame.getCharacters(), hasSize(1));
        assertThat(savedGame.getCharacters().get(0).getName(), is("Kazuha"));
        assertThat(savedGame.getCharacters().get(0).getDescription(), is("Anemo Sword"));
        assertThat(savedGame.getCharacters().get(0).getIsEnemy(), is(false));
    }

    @Test
    public void deleteById() {
        Game game = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();
        Long deleteGameId = gameRepository.save(game).getId();
        assertThat(gameRepository.existsById(deleteGameId), is(true));

        gameService.deleteById(deleteGameId);
        assertThat(gameRepository.existsById(deleteGameId), is(false));
    }

    @Test
    public void deleteById_notFound() {
        Long nonExistingId = 50L;

        Exception exception = assertThrows(NotFoundException.class, ()
                -> gameService.deleteById(nonExistingId));

        assertThat(exception.getMessage(), is(GAME));
    }

    @Test
    public void updateGame() {
        Character character = Character.builder()
                .name("Kazuha")
                .description("Anemo Sword")
                .isEnemy(false)
                .build();

        Game game = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();
        game.setCharacters(Arrays.asList(character));

        Game oldGame = gameRepository.save(game);

        assertThat(oldGame.getId(), is(oldGame.getId()));
        assertThat(oldGame.getTitle(), is("Genshin Impact"));
        assertThat(oldGame.getDeveloper(), is("Hoyoverse"));
        assertThat(oldGame.getGenre(), is(Genre.RPG));
        assertThat(oldGame.getCharacters(), hasSize(1));
        assertThat(oldGame.getCharacters().get(0).getName(), is("Kazuha"));
        assertThat(oldGame.getCharacters().get(0).getDescription(), is("Anemo Sword"));
        assertThat(oldGame.getCharacters().get(0).getIsEnemy(), is(false));

        Game newGame = Game.builder()
                .title("Honkai Star Rail")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();
        Game newSavedGame = gameService.updateGame(oldGame.getId(), newGame);

        assertThat(newSavedGame.getId(), is(oldGame.getId()));
        assertThat(newSavedGame.getTitle(), is("Honkai Star Rail"));
        assertThat(newSavedGame.getDeveloper(), is("Hoyoverse"));
        assertThat(newSavedGame.getGenre(), is(Genre.RPG));
        assertThat(newSavedGame.getCharacters(), nullValue());
    }
}
