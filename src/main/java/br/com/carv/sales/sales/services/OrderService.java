package br.com.carv.sales.sales.services;

import br.com.carv.sales.sales.dto.OrderDto;
import br.com.carv.sales.sales.entities.Order;
import br.com.carv.sales.sales.entities.StatusOrder;

import java.util.Optional;

public interface OrderService {

    Order saveOrder(OrderDto orderDto);

    Optional<Order> getOrderFull(Integer id);

    void updateStatus(Integer id, StatusOrder statusOrder);


}
