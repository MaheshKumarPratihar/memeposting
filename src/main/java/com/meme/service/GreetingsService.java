package com.meme.service;


import com.meme.data.GreetingsEntity;
import com.meme.exchange.ResponseDto;
import com.meme.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(String id) {
    Optional<GreetingsEntity> entity = greetingsRepository.findByExtId(id);
      return entity.map(greetingsEntity -> new ResponseDto(greetingsEntity.getMessage()))
              .orElse(null);
  }
}
