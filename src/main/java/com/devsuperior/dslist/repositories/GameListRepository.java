package com.devsuperior.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{
	
	@Modifying
	@Query(nativeQuery = true, 
	value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	
	//Função que dado um ID da lista de games e dado um ID de um game, atualiza a sua posição dentro da lista de games//
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);

}
