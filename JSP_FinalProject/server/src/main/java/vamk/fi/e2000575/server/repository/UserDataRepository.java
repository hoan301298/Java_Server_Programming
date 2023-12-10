package vamk.fi.e2000575.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vamk.fi.e2000575.server.entity.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    Optional<UserData> findByUsername(String userName);
}
