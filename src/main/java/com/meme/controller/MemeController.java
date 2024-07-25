package com.meme.controller;

import com.meme.data.Meme;
import com.meme.exchange.MemeDTO;
import com.meme.service.meme.MemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(MemeController.MEMES_API_ENDPOINT)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemeController {
    public static final String MEMES_API_ENDPOINT = "/memes/";

    private final MemeService memeService;

    @PostMapping
    public ResponseEntity<String> createMeme(@RequestBody Meme meme){
        if(meme == null || meme.getName() == null || meme.getUrl() == null || meme.getCaption() == null){
            return ResponseEntity.badRequest().build();
        }
        if(this.memeService.memeExists(meme)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String id = this.memeService.saveMeme(meme);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<MemeDTO>> getLatest100Memes(){
        List<MemeDTO> memes = this.memeService.getTop100Memes();
        if(memes == null){
            memes = new ArrayList<>();
        }
        return ResponseEntity.status(HttpStatus.OK).body(memes);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get meme by ID", description = "Returns a single meme")
    public ResponseEntity<MemeDTO> getMeme(@Parameter(description = "ID of the user to be obtained") @PathVariable("id") String id){
        MemeDTO meme = this.memeService.getMemeById(id);
        if(meme == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(meme);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MemeDTO> deleteMemeById(@PathVariable("id") String id){
        this.memeService.deleteMeme(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
