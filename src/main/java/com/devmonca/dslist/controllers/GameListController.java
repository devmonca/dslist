package com.devmonca.dslist.controllers;

import com.devmonca.dslist.dto.GameDTO;
import com.devmonca.dslist.dto.GameListDTO;
import com.devmonca.dslist.dto.GameMinDTO;
import com.devmonca.dslist.dto.ReplacementDTO;
import com.devmonca.dslist.services.GameListService;
import com.devmonca.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        List<GameListDTO> response = gameListService.findAll();
        return response;
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        List<GameMinDTO> response = gameService.findByList(listId);
        return response;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void moveList(@PathVariable Long listId, @RequestBody ReplacementDTO body){
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }

}
