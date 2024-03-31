package com.app.mygamerlist.api.character.controller;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.character.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public Iterable<Character> findAll() {
        return characterService.findAll();
    }

    @GetMapping("/{id}")
    public Character findOne(@PathVariable Long id) {
        return characterService.findCharacterById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Character createCharacter(@RequestBody Character character) {
        return characterService.saveCharacter(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable Long id) {
        characterService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Character updateCharacter(@RequestBody Character character, @PathVariable Long id) {
        return characterService.updateCharacter(id, character);
    }
}
