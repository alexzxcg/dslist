package com.devsuperior.dslist.repositories;

import com.devsuperior.dslist.entities.Belonging;
import com.devsuperior.dslist.entities.BelongingPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK> {

}