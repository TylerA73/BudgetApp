package com.tyler.budget;

import javax.persistence.Column;
import java.math.BigDecimal;

public class Amount {

    private BigDecimal amount;
    private Category category;

    Amount(BigDecimal amount, Category category) {

        this.amount = amount;
        this.category = category;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
