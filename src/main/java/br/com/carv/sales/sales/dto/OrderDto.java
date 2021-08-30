package br.com.carv.sales.sales.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Integer clientId;
    private BigDecimal totalValue;
    private List<OrderItemDto> orders;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public List<OrderItemDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemDto> orders) {
        this.orders = orders;
    }
}
