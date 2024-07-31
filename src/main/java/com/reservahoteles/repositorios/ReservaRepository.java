package com.reservahoteles.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservahoteles.entidades.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
