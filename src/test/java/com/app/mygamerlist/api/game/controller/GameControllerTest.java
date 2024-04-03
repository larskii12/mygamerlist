package com.app.mygamerlist.api.game.controller;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.model.Genre;
import com.app.mygamerlist.api.game.service.GameService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.app.mygamerlist.util.TestUtil.readJsonFile;
import static java.nio.file.Files.readAllBytes;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GameController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class GameControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    GameService gameService;

    private static final String API_ROOT = "http://localhost:8081";

    @Autowired
    private ResourceLoader resourceLoader = null;

    @Test
    public void viewGameList() throws Exception {
        int gameId1 = 1;
        int gameId2 = 2;
        Game game1 = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();

        game1.setId(gameId1);

        Game game2 = Game.builder()
                .title("Honkai Star Rail")
                .developer("Hoyoverse")
                .genre(Genre.STRATEGY)
                .build();

        game2.setId(gameId2);

        List<Game> gameList = asList(game1, game2);

        when(gameService.findAll()).thenReturn(gameList);

        ResultActions resultActions = mockMvc.perform(
                get(API_ROOT + "/api/games")
        );

        verify(gameService, times(1)).findAll();

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(gameId1)))
                .andExpect(jsonPath("$.[0].title", is(game1.getTitle())))
                .andExpect(jsonPath("$.[0].developer", is(game1.getDeveloper())))
                .andExpect(jsonPath("$.[0].genre", is(game1.getGenre().toString())))
                .andExpect(jsonPath("$.[1].id", is(gameId2)))
                .andExpect(jsonPath("$.[1].title", is(game2.getTitle())))
                .andExpect(jsonPath("$.[1].developer", is(game2.getDeveloper())))
                .andExpect(jsonPath("$.[1].genre", is(game2.getGenre().toString())));
    }

    @Test
    public void findOne() throws Exception {
        int gameId = 1;
        Game game = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .build();

        game.setId(gameId);

        when(gameService.findGameById((long) gameId)).thenReturn(game);

        ResultActions resultActions = mockMvc.perform(
                get(API_ROOT + "/api/games/" + gameId)
        );

        verify(gameService, times(1)).findGameById((long) gameId);

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(gameId)))
                .andExpect(jsonPath("$.title", is(game.getTitle())))
                .andExpect(jsonPath("$.developer", is(game.getDeveloper())))
                .andExpect(jsonPath("$.genre", is(game.getGenre().toString())));
    }

    @Test
    public void createGame() throws Exception {
        Character character = Character.builder()
                .name("Kazuha")
                .description("Anemo Sword")
                .isEnemy(false)
                .build();
        character.setId(1);

        Game game = Game.builder()
                .title("Genshin Impact")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .characters(List.of(character))
                .build();

        game.setId(1);

        ArgumentCaptor<Game> gameArgumentCaptor = ArgumentCaptor.forClass(Game.class);

        when(gameService.saveGame(gameArgumentCaptor.capture())).thenReturn(game);

        ResultActions resultActions = mockMvc.perform(
                post(API_ROOT + "/api/games")
                        .content(readJsonFile(resourceLoader, "classpath:GameControllerTest.json"))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        verify(gameService, times(1)).saveGame(any());

        Game gameCaptured = gameArgumentCaptor.getValue();
        assertThat(gameCaptured.getId(), is(1L));
        assertThat(gameCaptured.getTitle(), is("Genshin Impact"));
        assertThat(gameCaptured.getDeveloper(), is("Hoyoverse"));
        assertThat(gameCaptured.getGenre(), is(Genre.RPG));
        assertThat(gameCaptured.getCharacters(), hasSize(1));
        assertThat(gameCaptured.getCharacters().get(0).getId(), is(1L));
        assertThat(gameCaptured.getCharacters().get(0).getName(), is("Kazuha"));
        assertThat(gameCaptured.getCharacters().get(0).getDescription(), is("Anemo Sword"));
        assertThat(gameCaptured.getCharacters().get(0).getIsEnemy(), is(false));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(game.getTitle())))
                .andExpect(jsonPath("$.developer", is(game.getDeveloper())))
                .andExpect(jsonPath("$.genre", is(game.getGenre().toString())));

    }

    @Test
    public void deleteGame() throws Exception {

        doNothing().when(gameService)
                .deleteById(2L);

        mockMvc.perform(
                delete("/api/v1/programs/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        verify(gameService, times(1))
                .deleteById(2L);
    }

    @Test
    public void updateGame() throws Exception {
        Character character = Character.builder()
                .name("Jean")
                .description("Anemo Sword")
                .isEnemy(false)
                .build();
        character.setId(1);

        Game game = Game.builder()
                .title("Honkai Star Rail")
                .developer("Hoyoverse")
                .genre(Genre.RPG)
                .characters(List.of(character))
                .build();

        game.setId(1);

        ArgumentCaptor<Game> gameArgumentCaptor = ArgumentCaptor.forClass(Game.class);

        when(gameService.updateGame(eq((long) 1), gameArgumentCaptor.capture())).thenReturn(game);

        ResultActions resultActions = mockMvc.perform(
                put(API_ROOT + "/api/games/{id}", 1)
                        .content(readJsonFile(resourceLoader, "classpath:GameControllerTest.json"))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        verify(gameService, times(1)).updateGame(eq((long) 1), any());

        Game gameCaptured = gameArgumentCaptor.getValue();
        assertThat(gameCaptured.getId(), is(1L));
        assertThat(gameCaptured.getTitle(), is("Genshin Impact"));
        assertThat(gameCaptured.getDeveloper(), is("Hoyoverse"));
        assertThat(gameCaptured.getGenre(), is(Genre.RPG));
        assertThat(gameCaptured.getCharacters(), hasSize(1));
        assertThat(gameCaptured.getCharacters().get(0).getId(), is(1L));
        assertThat(gameCaptured.getCharacters().get(0).getName(), is("Kazuha"));
        assertThat(gameCaptured.getCharacters().get(0).getDescription(), is("Anemo Sword"));
        assertThat(gameCaptured.getCharacters().get(0).getIsEnemy(), is(false));


        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(game.getTitle())))
                .andExpect(jsonPath("$.developer", is(game.getDeveloper())))
                .andExpect(jsonPath("$.genre", is(game.getGenre().toString())))
                .andExpect(jsonPath("$.characters.[0].id", is(1)))
                .andExpect(jsonPath("$.characters.[0].name", is(character.getName())));
    }

}
