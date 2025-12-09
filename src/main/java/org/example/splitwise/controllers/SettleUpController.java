package org.example.splitwise.controllers;

import org.example.splitwise.model.Expense;
import org.example.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/settleUp")
public class SettleUpController {

    private final SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    @GetMapping(path = "/group/{groupId}")
    public List<Expense> settleUpGroup(@PathVariable(name = "groupId") Long groupId){
            return settleUpService.settleUpGroup(groupId);
    }

    @GetMapping(path = "/group/{groupId}/user/{userId}")
    public List<Expense> settleUpGroupForUser(@PathVariable(name = "groupId") Long groupId,
                                              @PathVariable(name = "userId") Long userId){
        return settleUpService.settleUpUserInGroup(groupId,userId);
    }

    @GetMapping(path = "/user/{userId}")
    public List<Expense> settleUpUser(@PathVariable(name = "userId") Long userId){
        return settleUpService.settleUpUser(userId);
    }
}
