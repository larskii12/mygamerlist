package com.app.mygamerlist.api.game.controller;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.model.Genre;
import com.app.mygamerlist.api.game.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GameController.class)
public class GameControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    GameService gameService;

    @Test
    public void viewGameList() throws Exception {
        long gameId1 = 1L;
        long gameId2 = 2L;
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
                get("/api/games")
        );

        verify(gameService, times(1)).findAll();

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id", is(Long.toString(gameId1))))
                .andExpect(jsonPath("$.content.[0].title", is(game1.getTitle())))
                .andExpect(jsonPath("$.content.[0].developer", is(game1.getDeveloper())))
                .andExpect(jsonPath("$.content.[0].genre", is(game1.getGenre())));
    }
}
