package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.TagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.GET_ALL_TAGS;
@Component
public class GetAllTags implements Command {
    private final TagController tagController;

    @Autowired
    public GetAllTags(TagController tagController) {
        this.tagController = tagController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println(GET_ALL_TAGS.getOperation());
        tagController.readAll().forEach(System.out::println);

    }

    @Override
    public CommandType getCommandType() {
        return GET_ALL_TAGS;
    }
}
