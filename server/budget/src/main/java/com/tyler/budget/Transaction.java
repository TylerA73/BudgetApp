package com.tyler.budget;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "transaction_description")
    @JsonProperty("description")
    private String description;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.DATE)
    @JsonProperty("date")
    private Date date;

    @Column(name = "transaction_amount")
    @JsonProperty("amount")
    private float amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonProperty("category")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

