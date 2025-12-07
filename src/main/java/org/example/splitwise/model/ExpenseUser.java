package org.example.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ExpenseUser extends BaseModel{

    @ManyToOne
    private User user;
    private Double amount;
}
