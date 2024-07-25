package com.meme.service.meme;

import com.meme.data.Meme;
import com.meme.exchange.MemeDTO;

import java.util.List;


public interface MemeService {

    String saveMeme(Meme meme);
    List<MemeDTO> getTop100Memes();
    MemeDTO getMemeById(String id);
    boolean memeExists(Meme meme);
    void deleteMeme(String id);
}
