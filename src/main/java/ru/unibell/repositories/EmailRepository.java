package ru.unibell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.unibell.models.Client;
import ru.unibell.models.Email;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    boolean existsEmailByName(@Param("name") String name);

    List<Email> findAllByClient(@Param("client") Client client);
}
