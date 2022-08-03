package app.repository.user;

import app.model.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select distinct r from Role r left join fetch r.permissions left join fetch r.users where r.name = :name")
    Optional<Role> findByName(String name);
}
