package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.dto.InfoOrderDto;
import br.com.carv.sales.sales.dto.InfoOrderItemDto;
import br.com.carv.sales.sales.dto.OrderDto;
import br.com.carv.sales.sales.dto.UpdateStatusOrderDto;
import br.com.carv.sales.sales.entities.Order;
import br.com.carv.sales.sales.entities.OrderItem;
import br.com.carv.sales.sales.entities.StatusOrder;
import br.com.carv.sales.sales.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveOrder(@RequestBody @Valid OrderDto orderDto) {
        Order order = orderService.saveOrder(orderDto);
        return order.getIdOrder();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/infoOrder/{id}")
    public InfoOrderDto getById(@PathVariable Integer id) {
        return orderService.getOrderFull(id).map(p -> convertOrderDto(p)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found" + id));

    }

    private InfoOrderDto convertOrderDto(Order order) {
        InfoOrderDto infoOrderDto = new InfoOrderDto();
        infoOrderDto.setIdOrder(order.getIdOrder());
        infoOrderDto.setOrderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        infoOrderDto.setCpfClient(null);
        infoOrderDto.setNameClient(order.getClient().getName());
        infoOrderDto.setTotalValue(order.getTotalValue());
        infoOrderDto.setStatusOrder(order.getStatusOrder().name());
        infoOrderDto.setItems(convertOrderItemDto((List<OrderItem>) order.getOrderItems()));
        return infoOrderDto;


    }

    private List<InfoOrderItemDto> convertOrderItemDto (List<OrderItem> itemList) {
        if(CollectionUtils.isEmpty(itemList)) {
            return Collections.emptyList();
        } else {
            InfoOrderItemDto infoOrderItemDto = new InfoOrderItemDto();
            infoOrderItemDto.setDescriptionProduct(itemList.get(0).getProduct().getDescriptionProduct());
            infoOrderItemDto.setAmountProduct(itemList.get(0).getAmountProduct());
            infoOrderItemDto.setUnitPriceProduct(itemList.get(0).getProduct().getUnitPrice());
            return (List<InfoOrderItemDto>) infoOrderItemDto;
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/updateStatus/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusOrderDto updateStatusOrderDto) {
        String newStatus = updateStatusOrderDto.getNewStatus();
        orderService.updateStatus(id, StatusOrder.valueOf(newStatus));
    }


}
