package com.meme.service.meme;

import com.meme.data.Meme;
import com.meme.exchange.MemeDTO;
import com.meme.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemeServiceImpl implements MemeService{

    private final MemeRepository memeRepository;
    private final static int PAGE_NUMBER = 0;
    private final static int PAGE_SIZE = 10;

    @Override
    public String saveMeme(Meme meme) {
        return this.memeRepository.save(meme).getId();
    }

    @Override
    public List<MemeDTO> getTop100Memes() {
        PageRequest pageRequest = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        return this.memeRepository.findAllByOrderByCreatedAtDesc(pageRequest)
                .getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemeDTO getMemeById(String id) {
        Optional<Meme> meme = memeRepository.findById(id);
        return meme.map(this::convertToDTO).orElse(null);
    }

    @Override
    public boolean memeExists(Meme meme) {
        return this.memeRepository.findByNameAndUrlAndCaption(meme.getName(), meme.getUrl(), meme.getCaption())
                .isPresent();
    }

    @Override
    public void deleteMeme(String id) {
        this.memeRepository.deleteById(id);
    }

    private MemeDTO convertToDTO(Meme meme) {
        MemeDTO dto = new MemeDTO();
        dto.setId(meme.getId());
        dto.setName(meme.getName());
        dto.setUrl(meme.getUrl());
        dto.setCaption(meme.getCaption());
        return dto;
    }
}
