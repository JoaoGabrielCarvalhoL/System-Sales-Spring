package br.com.carv.sales.sales.services;


import br.com.carv.sales.sales.dto.OrderDto;
import br.com.carv.sales.sales.dto.OrderItemDto;
import br.com.carv.sales.sales.entities.*;
import br.com.carv.sales.sales.exceptions.RuleException;
import br.com.carv.sales.sales.repositories.ClientRepository;
import br.com.carv.sales.sales.repositories.OrderItemRepository;
import br.com.carv.sales.sales.repositories.OrderRepository;
import br.com.carv.sales.sales.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public Order saveOrder(OrderDto orderDto) {
        Integer idClient = orderDto.getClientId();
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(idClient).orElseThrow(() -> new RuleException("Client not found!")));

        Order order = new Order();
        order.setTotalValue(orderDto.getTotalValue());
        order.setOrderDate(LocalDate.now());
        order.setClient(client.get());
        order.setStatusOrder(StatusOrder.ACCOMPLISHED);

        List<OrderItem> items = convertItems(orderDto.getOrders(), order);
        orderRepository.save(order);
        orderItemRepository.saveAll(items);
        order.setOrderItems((Set<OrderItem>) items);
        return order;
    }

    @Override
    public Optional<Order> getOrderFull(Integer id) {
        return orderRepository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, StatusOrder statusOrder) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().setStatusOrder(statusOrder);
            orderRepository.save(order.get());
        } else {
            throw new RuntimeException("Order not found! Id: " + id);
        }

    }

    private List<OrderItem> convertItems(List<OrderItemDto> itemList, Order order) {
        if (itemList.isEmpty()) {
            throw new RuleException("It is not possible to place an order without items");
        }

        return itemList.stream().map( dto -> {

            Integer idProduct = dto.getProductId();
            Product product = productRepository.findById(idProduct).orElseThrow(() -> new RuleException("Product not found! Id Product: " + idProduct));

            OrderItem orderItem = new OrderItem();
            orderItem.setAmountProduct(dto.getAmountProduct());
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            return orderItem;
        }).collect(Collectors.toList());

    }
}
