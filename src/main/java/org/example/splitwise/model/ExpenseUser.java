package org.example.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseUser extends BaseModel{

    @ManyToOne
    private User user;
    private Double amount;

//    @ManyToOne
//    private Expense expense;
}
