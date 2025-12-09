package org.example.splitwise.controllers;

import org.example.splitwise.dtos.CreateGroupRequestDto;
import org.example.splitwise.model.Group;
import org.example.splitwise.repository.GroupRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/group")
public class GroupController {

    // Add a post mapping for creating a group

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @PostMapping(path = "create")
    public Long createGroup(@RequestBody CreateGroupRequestDto request){
//       groupRepository.save(Group.builder()
//               .name(request.getName())
//               .isSimplifiedDebtOn(request.)
//               .users(request.getUserIds())
//               .build();
        return 1L;
    }
}
