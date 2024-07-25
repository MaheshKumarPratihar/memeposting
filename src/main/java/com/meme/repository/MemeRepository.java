package com.meme.repository;

import com.meme.data.Meme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemeRepository extends MongoRepository<Meme, String> {
    Page<Meme> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Optional<Meme> findByNameAndUrlAndCaption(String name, String url, String caption);
}
