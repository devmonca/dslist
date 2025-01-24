package com.devmonca.dslist.services;

import com.devmonca.dslist.dto.GameDTO;
import com.devmonca.dslist.dto.GameListDTO;
import com.devmonca.dslist.dto.GameMinDTO;
import com.devmonca.dslist.entities.Game;
import com.devmonca.dslist.entities.GameList;
import com.devmonca.dslist.repositories.GameListRepository;
import com.devmonca.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> response = gameListRepository.findAll();
        return response.stream().map(x -> new GameListDTO(x)).toList();
    }

}
