package com.dainv.karma.model;

import javax.persistence.*;

@Entity
@Table(name = "bill_detai")
public class Bill_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BILL_BILLDETAIL"))
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", foreignKey = @ForeignKey(name = "FK_PRODUCT_BILLDETAIL"))
    private Product product;

    private int quantity;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bill_detail{" +
                "id=" + id +
                ", bill=" + bill +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
