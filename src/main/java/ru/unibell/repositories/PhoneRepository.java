package ru.unibell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.unibell.models.Client;
import ru.unibell.models.Phone;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    boolean existsPhoneByNumber(@Param("number") String number);

    List<Phone> findAllByClient(@Param("client") Client client);
}
