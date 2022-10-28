package com.itshendson.invoicesystem.invoice.model;

import java.math.BigDecimal;

public class ItemRecord {
    private long itemRecordId;
    private String ItemName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private Invoice invoice;

    public ItemRecord(int itemRecordId, String ItemName, int quantity, BigDecimal unitPrice, BigDecimal totalAmount, Invoice invoice) {
        this.itemRecordId = itemRecordId;
        this.ItemName = ItemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.invoice = invoice;
    }

    public long getItemRecordId() {
        return itemRecordId;
    }

    public void setItemRecordId(int itemRecordId) {
        this.itemRecordId = itemRecordId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
