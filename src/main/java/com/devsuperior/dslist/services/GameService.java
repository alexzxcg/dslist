package com.devsuperior.dslist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
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
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
   	private BelongingRepository belongingRepository;

	@Autowired
	private GameListRepository gameListRepository;

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get();
		GameDTO dto= new GameDTO(result);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> result = gameRepository.findAll();
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}
	//Método que devolve um GameMinDTO buscando por ID em uma lista de games, os games que estão associados a ela//
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId) {
		List<GameMinProjection> result = gameRepository.searchsearchByList(listId);
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public Game createNewGame (Game obj){
		return gameRepository.save(obj);
	}

	public void removeGameFromList(Long gameId, Long gameListId) {
		// Utiliza o método findById do repositório para buscar os objetos Game e GameList
		Optional<Game> optionalGame = gameRepository.findById(gameId);
		Optional<GameList> optionalGameList = gameListRepository.findById(gameListId);

		if (optionalGame.isPresent() && optionalGameList.isPresent()) {
			Game wantedGame = optionalGame.get();
			GameList wantedGameList = optionalGameList.get();

			// Cria uma instância de BelongingPK usando os objetos encontrados
			BelongingPK belongingPK = new BelongingPK(wantedGame, wantedGameList);

			// Verifica se a associação existe antes de excluí-la
			Optional<Belonging> optionalBelonging = belongingRepository.findById(belongingPK);

			optionalBelonging.ifPresent(belongingRepository::delete);
		} else {
			// Tratar caso em que um ou ambos os objetos não foram encontrados
			throw new EntityNotFoundException("Game or GameList not found with provided IDs.");
		}
		
	}

}
