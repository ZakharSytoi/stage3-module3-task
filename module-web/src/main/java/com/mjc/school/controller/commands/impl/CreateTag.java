package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.controller.impl.TagController;
import com.mjc.school.service.dto.TagDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.CREATE_TAG;

@Component
public class CreateTag implements Command {
    private final TagController tagController;

    @Autowired
    public CreateTag(TagController tagController) {
        this.tagController = tagController;
    }

    @Override
    public void execute(Scanner scanner) {
        TagDtoRequest tagDto = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(CREATE_TAG.getOperation());
                System.out.println(Constants.TAG_NAME_ENTER);
                String name = scanner.nextLine();
                tagDto = new TagDtoRequest(null, name);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(tagController.create(tagDto));
    }

    @Override
    public CommandType getCommandType() {
        return CREATE_TAG;
    }
}
