package com.dainv.karma.model;

import com.dainv.karma.model.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column(columnDefinition = "nvarchar(200)")
    private String productName;


    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private int quantity;

    private int price;

    private boolean status;

    private String image;

    @Column(columnDefinition = "Text")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    public Product() {
    }


    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
