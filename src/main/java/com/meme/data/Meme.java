package com.meme.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "memes")
public class Meme {
    @Id
    private String id;
    private String name;
    private String url;
    private String caption;

    @CreatedDate
    private LocalDateTime createdAt;
}
