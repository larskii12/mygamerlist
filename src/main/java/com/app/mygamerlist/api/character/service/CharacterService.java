package com.app.mygamerlist.api.character.service;

import com.app.mygamerlist.api.character.model.Character;

import java.util.List;

public interface CharacterService {

    Iterable<Character> findAll();

    Character findCharacterById(Long id);

    Character saveCharacter(Character character);

    void deleteById(Long id);

    Character updateCharacter(Long id, Character character);
}
