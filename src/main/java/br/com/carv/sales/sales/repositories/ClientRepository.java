package br.com.carv.sales.sales.repositories;

import br.com.carv.sales.sales.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT c FROM Client c WHERE c.nameClient LIKE :nameClient")
    List<Client> findByNameLike(@Param("nameClient") String nameClient);

    @Query(value = "SELECT c FROM Client c LEFT JOIN FETCH  c.orders WHERE c.idClient = :idClient")
    Client findClientFetchOrder(@Param("idClient") Client idClient);


}
