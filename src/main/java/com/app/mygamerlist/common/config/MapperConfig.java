package com.app.mygamerlist.common.config;

import com.app.mygamerlist.api.rating.model.Rating;
import com.app.mygamerlist.api.rating.model.RatingDto;
import com.app.mygamerlist.api.user.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
