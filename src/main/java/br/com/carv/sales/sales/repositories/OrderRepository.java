package br.com.carv.sales.sales.repositories;

import br.com.carv.sales.sales.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select p from Order  p left  join fetch p.orderItems where  p.idOrder = :id")
    Optional<Order> findByIdFetchItems(@Param("id")Integer id);
}
