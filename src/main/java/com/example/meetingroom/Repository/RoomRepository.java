package com.example.meetingroom.Repository;

import com.example.meetingroom.Entity.Room;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    <S extends Room> S save(S s);

    @Override
    Room getById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long id);
}
