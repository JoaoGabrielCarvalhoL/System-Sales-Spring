package br.com.carv.sales.sales.dto;

public class OrderItemDto {

    private Integer productId;
    private Integer amountProduct;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(Integer amountProduct) {
        this.amountProduct = amountProduct;
    }
}
