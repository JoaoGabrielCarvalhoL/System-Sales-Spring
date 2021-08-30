package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.entities.Order;
import br.com.carv.sales.sales.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderControl {

    private final OrderRepository orderRepository;

    public OrderControl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findOrderById/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        Order orderToSaved = orderRepository.save(order);
        return ResponseEntity.ok(orderToSaved);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteOrderById/{id}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateOrderById/{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            orderRepository.save(order.get());
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllOrders")
    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

}
