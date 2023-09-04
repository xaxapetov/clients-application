package ru.unibell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.unibell.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsClientByName(@Param("name") String name);
}
