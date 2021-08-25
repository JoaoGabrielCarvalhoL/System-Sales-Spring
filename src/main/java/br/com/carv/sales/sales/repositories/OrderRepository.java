package br.com.carv.sales.sales.repositories;

import br.com.carv.sales.sales.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
