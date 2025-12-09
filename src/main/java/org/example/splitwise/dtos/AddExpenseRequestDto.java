package org.example.splitwise.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.model.ExpenseType;
import java.util.Map;

@Getter
@Setter
public class AddExpenseRequestDto {
    private String name;
    private double totalAmount;
    private ExpenseType expenseType;
    private Map<Long, Double> paidBy;
    private Map<Long, Double> owedBy;
    private String groupName;
}
