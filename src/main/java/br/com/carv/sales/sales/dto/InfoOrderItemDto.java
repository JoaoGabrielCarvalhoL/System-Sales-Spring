package br.com.carv.sales.sales.dto;

import java.math.BigDecimal;

public class InfoOrderItemDto {

    private String descriptionProduct;
    private BigDecimal unitPriceProduct;
    private Integer amountProduct;

    public InfoOrderItemDto() {

    }


    public InfoOrderItemDto(String descriptionProduct, BigDecimal unitPriceProduct, Integer amountProduct) {
        this.descriptionProduct = descriptionProduct;
        this.unitPriceProduct = unitPriceProduct;
        this.amountProduct = amountProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public BigDecimal getUnitPriceProduct() {
        return unitPriceProduct;
    }

    public void setUnitPriceProduct(BigDecimal unitPriceProduct) {
        this.unitPriceProduct = unitPriceProduct;
    }

    public Integer getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(Integer amountProduct) {
        this.amountProduct = amountProduct;
    }


}
