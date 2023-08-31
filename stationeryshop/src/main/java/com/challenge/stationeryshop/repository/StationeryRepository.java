package com.challenge.stationeryshop.repository;

import com.challenge.stationeryshop.model.StationeryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationeryRepository extends JpaRepository<StationeryModel, Long> {

    @Query(value = "SELECT * FROM TB_STATIONERIES WHERE name ILIKE %:nome%", nativeQuery = true)
    List<StationeryModel> findByNome(@Param("nome") String nome);
}
