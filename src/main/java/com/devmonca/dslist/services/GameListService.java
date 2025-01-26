package com.devmonca.dslist.services;

import com.devmonca.dslist.dto.GameDTO;
import com.devmonca.dslist.dto.GameListDTO;
import com.devmonca.dslist.dto.GameMinDTO;
import com.devmonca.dslist.entities.Game;
import com.devmonca.dslist.entities.GameList;
import com.devmonca.dslist.projection.GameMinProjection;
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

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> response = gameListRepository.findAll();
        return response.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex){

        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection objRemove = list.remove(sourceIndex);
        list.add(destinationIndex, objRemove);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i<=max; i++){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }

}
