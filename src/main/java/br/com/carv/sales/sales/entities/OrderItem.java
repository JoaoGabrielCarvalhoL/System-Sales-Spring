package br.com.carv.sales.sales.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrderItem;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer amountProduct;

    public OrderItem() {

    }

    public OrderItem(Integer idOrderItem, Order order, Product product, Integer amountProduct) {
        this.idOrderItem = idOrderItem;
        this.order = order;
        this.product = product;
        this.amountProduct = amountProduct;
    }

    public Integer getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(Integer idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product prodcut) {
        this.product = prodcut;
    }

    public Integer getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(Integer amountProduct) {
        this.amountProduct = amountProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(idOrderItem, orderItem.idOrderItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderItem);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "idOrderItem=" + idOrderItem +
                ", order=" + order +
                ", product=" + product +
                ", amountProduct=" + amountProduct +
                '}';
    }
}
