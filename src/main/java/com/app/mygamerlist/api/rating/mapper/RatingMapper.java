package com.app.mygamerlist.api.rating.mapper;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.character.model.CharacterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    @Autowired
    ModelMapper modelMapper;

    public CharacterDto toDto(Character character){
        return modelMapper.map(character, CharacterDto.class);
    }

    public Character toEntity(CharacterDto characterDto){
        return modelMapper.map(characterDto, Character.class);
    }

}
