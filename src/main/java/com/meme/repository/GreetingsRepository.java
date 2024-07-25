package com.meme.repository;


import com.meme.data.GreetingsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GreetingsRepository extends MongoRepository<GreetingsEntity, String> {
  Optional<GreetingsEntity> findByExtId(String extId);
}
