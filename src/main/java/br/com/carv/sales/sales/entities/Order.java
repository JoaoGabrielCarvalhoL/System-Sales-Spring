package br.com.carv.sales.sales.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ordems")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal totalValue;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public Order() {

    }

    public Order(Integer idOrder, Client client, LocalDate orderDate, BigDecimal totalValue) {
        this.idOrder = idOrder;
        this.client = client;
        this.orderDate = orderDate;
        this.totalValue = totalValue;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", client=" + client +
                ", orderDate=" + orderDate +
                ", totalValue=" + totalValue +
                '}';
    }
}
