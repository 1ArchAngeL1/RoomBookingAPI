package com.example.meetingroom.Repository;

import com.example.meetingroom.Entity.Reservation;
import org.apache.catalina.Host;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Override
    <S extends Reservation> S save(S s);

    @Override
    Reservation getById(Long id);

    @Override
    List<Reservation> findAll();

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long id);

}
