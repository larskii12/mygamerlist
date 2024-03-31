package com.app.mygamerlist.api.character.service;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.character.repository.CharacterRepository;
import com.app.mygamerlist.api.game.repository.GameRepository;
import com.app.mygamerlist.common.exception.IdMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;


    @Override
    public Iterable<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character findCharacterById(Long id) {
        return characterRepository.findById(id).orElseThrow();
    }

    @Override
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public void deleteById(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public Character updateCharacter(Long id, Character character) {
        if (character.getId() != id) {
            throw new IdMismatchException();
        }
        characterRepository.findById(id)
                .orElseThrow();
        return characterRepository.save(character);
    }
}
