package org.example.splitwise.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Expense extends BaseModel {

    private String name;
    private double totalAmount;

    @ManyToOne
    private Group group;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    //who all paid in this expense - paidBy
    //who all owe some amount int the expense- owedBy

    @OneToMany
    List<ExpenseUser> paidBy;

    @OneToMany
    List<ExpenseUser> owedBy;
}
