package com.reservahoteles.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservahoteles.entidades.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
