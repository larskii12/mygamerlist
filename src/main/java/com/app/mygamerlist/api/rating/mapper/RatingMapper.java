package com.app.mygamerlist.api.rating.mapper;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.character.model.CharacterDto;
import com.app.mygamerlist.api.rating.model.Rating;
import com.app.mygamerlist.api.rating.model.RatingDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    @Autowired
    ModelMapper modelMapper;

    public RatingDto toDto(Rating rating){
        return modelMapper.map(rating, RatingDto.class);
    }

    public Rating toEntity(RatingDto ratingDto){
        return modelMapper.map(ratingDto, Rating.class);
    }

}
