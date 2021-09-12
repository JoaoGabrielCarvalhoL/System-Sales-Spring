package br.com.carv.sales.sales.dto;

import br.com.carv.sales.sales.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    @NotNull(message = "Id field is mandatory.")
    private Integer clientId;

    @NotNull(message = "Total value field is mandatory.")
    private BigDecimal totalValue;

    @NotEmptyList(message = "Order cannot be placed without items.")
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
