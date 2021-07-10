package com.example.meetingroom.Repository;

import com.example.meetingroom.Entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Repository;

@Repository
@Eager
public interface UserRepository extends JpaRepository<User,String> {

    @Override
    public <S extends User> S save(S s);

    @Override
    public User getById(String id);

    @Override
    public void deleteById(String s);

    @Override
    public boolean existsById(String id);
}
