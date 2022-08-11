package app.repository.user;

import app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select distinct u from User u " +
            "left join fetch u.role r left join fetch r.permissions " +
            "left join fetch r.users where u.id = :id")
    Optional<User> findById(Long id);

    @Query(value = "select distinct u from User u " +
            "left join fetch u.role r left join fetch r.permissions " +
            "left join fetch r.users where u.email = :email")
    Optional<User> findByEmail(String email);

}
