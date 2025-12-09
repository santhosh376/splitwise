package org.example.splitwise.strategy;

import org.example.splitwise.model.Expense;
import org.example.splitwise.model.ExpenseUser;
import org.example.splitwise.model.User;

import java.util.*;

public class SimplifiedSettleUpStrategy {

    public static List<Expense> settleUp(List<Expense> expenses){

        //1. find all the users who paid or owed from the expenses
        //2. Divide the user into groups - heaps (priority queue)
               //priority queue 1 holds all the +ve amount
               //priority queue 2 holds all the -ve amount
        //3. Pop out topmost elements from both the heaps. And create an  expense
        //4. Insert back the left out one
        //5. Keep doing this until heap is empty
        Map<User, Double> totalPaidOrOwed = new HashMap<>();

        // Calculate the net amount to be paid or owed
        for(Expense expense : expenses){
           for(ExpenseUser expenseUser : expense.getPaidBy()) {
               totalPaidOrOwed.put(
                       expenseUser.getUser(), totalPaidOrOwed.getOrDefault(expenseUser.getUser(), 0.0)
                               + expenseUser.getAmount());

           }
           for(ExpenseUser expenseUser : expense.getOwedBy()){
                totalPaidOrOwed.put(
                        expenseUser.getUser(), totalPaidOrOwed.getOrDefault(expenseUser.getUser(),0.0)
                                - expenseUser.getAmount());

           }
        }

        // A : 1000
        // B : -500
        // C : -500

        PriorityQueue<ExpenseUser> paidBy = new PriorityQueue<>(new PqComparator());
        PriorityQueue<ExpenseUser> owedBy = new PriorityQueue<>(new PqComparator());

        for(Map.Entry<User, Double> userDoubleEntry : totalPaidOrOwed.entrySet()){
            if(userDoubleEntry.getValue() >= 0){
                paidBy.add(new ExpenseUser(userDoubleEntry.getKey(),userDoubleEntry.getValue()));
            }else{
                owedBy.add(new ExpenseUser(userDoubleEntry.getKey(),-1 * userDoubleEntry.getValue()));
            }
        }

        List<Expense> expenseList = new ArrayList<>();

        while(!paidBy.isEmpty()){
            ExpenseUser paidByUser = paidBy.poll();
            ExpenseUser owedByUser = owedBy.poll();

            if(paidByUser.getAmount() > owedByUser.getAmount()){
                expenseList.add(Expense.builder()
                        .owedBy(List.of(new ExpenseUser(paidByUser.getUser(),owedByUser.getAmount()))) //A (receives amount)
                        .paidBy(List.of(owedByUser))  // B(pays amount)
                        .build());
                paidBy.add(new ExpenseUser(paidByUser.getUser(), paidByUser.getAmount()- owedByUser.getAmount()));
            }else {
                expenseList.add(Expense.builder()
                        .owedBy(List.of(paidByUser)) //B (receives amount)
                        .paidBy(List.of(new ExpenseUser(owedByUser.getUser(), paidByUser.getAmount()))) // A(pays amount)
                        .build());
                owedBy.add(new ExpenseUser(owedByUser.getUser(), owedByUser.getAmount() - paidByUser.getAmount()));
            }
        }
        return expenseList;
    }

    public static class PqComparator implements Comparator<ExpenseUser>{

        @Override
        public int compare(ExpenseUser u1,ExpenseUser u2){
            if(u1.getAmount() != u2.getAmount()) {
                if (u1.getAmount() < u2.getAmount()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 1;
        }

    }
}
