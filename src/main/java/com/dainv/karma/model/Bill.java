package com.dainv.karma.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "customerId", referencedColumnName = "customerId", foreignKey = @ForeignKey(name = "FK_BILL_CUSTOMER"))
    @ManyToOne()
    private Customer customer;

    private Date orderDate;

    private Date receivedDate;

    private int totalMoney;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    private String phone;

    @OneToMany(mappedBy = "bill",cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Bill_detail> bill_details;

    // 0- delivery, 1-received, 2- cancelled
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Bill_detail> getBill_details() {
        return bill_details;
    }

    public void setBill_details(List<Bill_detail> bill_details) {
        this.bill_details = bill_details;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", receivedDate=" + receivedDate +
                ", totalMoney=" + totalMoney +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
