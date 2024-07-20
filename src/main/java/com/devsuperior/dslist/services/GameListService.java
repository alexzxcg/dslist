package com.devsuperior.dslist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.Belonging;
import com.devsuperior.dslist.entities.BelongingPK;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.BelongingRepository;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private BelongingRepository belongingRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public GameListDTO findById(Long id){
		GameList result = gameListRepository.findById(id).get();
		GameListDTO dto= new GameListDTO(result);
		return dto;
	}
	
	/* Método que atualiza dentro do meu banco de dados as posições dos games na minha lista de games
	   com base na lista instanciada na memória*/
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchsearchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}

	@Transactional(readOnly = true)
	public GameList createGameList (GameList obj){
		return gameListRepository.save(obj);
	}

	public void addNewGameInGameList(Long gameId, Long gameListId, int position) {
		Optional<Game> optionalGame = gameRepository.findById(gameId);
		Optional<GameList> optionalGameList = gameListRepository.findById(gameListId);

		if (optionalGame.isPresent() && optionalGameList.isPresent()) {
			Game wantedGame = optionalGame.get();
			GameList wantedGameList = optionalGameList.get();

			Belonging belonging = new Belonging();
			belonging.setPosition(position);
			belonging.setId(new BelongingPK(wantedGame, wantedGameList));

			belongingRepository.save(belonging);
		} else {
			// Tratar caso em que um ou ambos os objetos não foram encontrados
			throw new EntityNotFoundException("Game or GameList not found with provided IDs.");
		}
	}

}
