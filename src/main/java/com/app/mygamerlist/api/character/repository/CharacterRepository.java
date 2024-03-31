package com.app.mygamerlist.api.character.repository;

import com.app.mygamerlist.api.character.model.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {
}
