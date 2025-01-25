package com.devmonca.dslist.services;

import com.devmonca.dslist.dto.GameDTO;
import com.devmonca.dslist.dto.GameMinDTO;
import com.devmonca.dslist.entities.Game;
import com.devmonca.dslist.projection.GameMinProjection;
import com.devmonca.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> response = gameRepository.findAll();
        return response.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game response = gameRepository.findById(id).get();
        return new GameDTO(response);
    }


    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> response = gameRepository.searchByList(listId);
        return response.stream().map(x-> new GameMinDTO(x)).toList();
    }

}
