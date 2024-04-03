package com.app.mygamerlist.api.character.service;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.character.repository.CharacterRepository;
import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.repository.GameRepository;
import com.app.mygamerlist.common.exception.IdMismatchException;
import com.app.mygamerlist.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.mygamerlist.common.exception.NotFoundException.CHARACTER;
import static com.app.mygamerlist.common.exception.NotFoundException.GAME;

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
        if (!characterRepository.existsById(id)) {
            throw new NotFoundException(CHARACTER);
        }
        characterRepository.deleteById(id);
    }

    @Override
    public Character updateCharacter(Long id, Character character) {
        Optional<Character> characterFromDbOpt = characterRepository.findById(id);

        if (characterFromDbOpt.isEmpty()) {
            throw new NotFoundException(GAME);
        }

        Character characterFromDb = characterFromDbOpt.get();

        characterFromDb.setName(character.getName());
        characterFromDb.setDescription(character.getDescription());
        characterFromDb.setGame(character.getGame());
        characterRepository.findById(id)
                .orElseThrow();
        return characterRepository.save(character);
    }
}
