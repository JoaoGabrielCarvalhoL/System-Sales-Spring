package br.com.carv.sales.sales.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Description of Product field is mandatory.")
    private String descriptionProduct;

    @Column(precision = 9, scale = 2)
    @NotNull
    private BigDecimal unitPrice;

    public Product() {
    }

    public Product(Integer idProduct, String descriptionProduct, BigDecimal unitPrice) {
        this.idProduct = idProduct;
        this.descriptionProduct = descriptionProduct;
        this.unitPrice = unitPrice;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(idProduct, product.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", descriptionProduct='" + descriptionProduct + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
