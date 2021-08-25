package br.com.carv.sales.sales.repositories;

import br.com.carv.sales.sales.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
