package com.devmonca.dslist.controllers;

import com.devmonca.dslist.dto.GameDTO;
import com.devmonca.dslist.dto.GameMinDTO;
import com.devmonca.dslist.entities.Game;
import com.devmonca.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll(){
        List<GameMinDTO> response = gameService.findAll();
        return response;
    }

    @GetMapping(value = "/{id}")
    public GameDTO findById(@PathVariable Long id){
        return gameService.findById(id);
    }


}
