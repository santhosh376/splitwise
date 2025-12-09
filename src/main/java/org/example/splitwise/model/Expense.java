package org.example.splitwise.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Expense extends BaseModel {

    private String name;
    private double totalAmount;

    @ManyToOne
    private Group group;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    //who all paid in this expense - paidBy
    //who all owe some amount int the expense- owedBy

    @OneToMany //(mappedBy = "expenseUser")
    List<ExpenseUser> paidBy;

    @OneToMany //(mappedBy = "expenseUser")
    List<ExpenseUser> owedBy;
}
