package com.dainv.karma.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BillStatus")
public class BillStatus {

    @Id
    private int id;

    private String Name;

    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL)
    List<Bill> bills;

    public BillStatus() {
    }

    public BillStatus(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
