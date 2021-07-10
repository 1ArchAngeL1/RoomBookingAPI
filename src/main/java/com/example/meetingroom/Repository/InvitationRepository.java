package com.example.meetingroom.Repository;

import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    @Override
    <S extends Invitation> S save(S s);

    @Override
    Invitation getById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    List<Invitation> findAll();

    List<Invitation> findAllByUser(User user);
}
