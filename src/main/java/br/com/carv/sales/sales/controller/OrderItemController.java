package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.entities.OrderItem;
import br.com.carv.sales.sales.repositories.OrderItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findOrderItemById/{id}")
    public ResponseEntity<OrderItem> findOrderItemById(@PathVariable Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);

        if (orderItem.isPresent()) {
            return ResponseEntity.ok(orderItem.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveOrderItem")
    public ResponseEntity<OrderItem> saveOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem orderItemToSave = orderItemRepository.save(orderItem);
        return ResponseEntity.ok(orderItemToSave);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteOrderItem/{id}")
    public ResponseEntity<OrderItem> deleteOrderItem(@PathVariable Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);

        if (orderItem.isPresent()) {
            orderItemRepository.delete(orderItem.get());
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateOrderItem/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);

        if (orderItem.isPresent()) {
            orderItemRepository.save(orderItem.get());
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllOrderItem")
    public List<OrderItem> findAllOrderItem() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems;
    }


}
