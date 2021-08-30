package br.com.carv.sales.sales.dto;

import java.math.BigDecimal;
import java.util.List;

public class InfoOrderDto {

    private Integer idOrder;
    private String cpfClient;
    private String nameClient;
    private BigDecimal totalValue;
    private List<InfoOrderItemDto>  items;
    private String orderDate;
    private String statusOrder;

    public InfoOrderDto() {
        
    }

    public InfoOrderDto(Integer idOrder, String cpfClient, String nameClient, BigDecimal totalValue, List<InfoOrderItemDto> items, String orderDate) {
        this.idOrder = idOrder;
        this.cpfClient = cpfClient;
        this.nameClient = nameClient;
        this.totalValue = totalValue;
        this.items = items;
        this.orderDate = orderDate;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public List<InfoOrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<InfoOrderItemDto> items) {
        this.items = items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
