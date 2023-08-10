package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.util.Utils;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.controller.impl.TagController;
import com.mjc.school.service.dto.TagDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.UPDATE_TAG;

@Component
public class UpdateTag implements Command {
    private final TagController tagController;

    @Autowired
    public UpdateTag(TagController tagController) {
        this.tagController = tagController;
    }

    @Override
    public void execute(Scanner scanner) {
        TagDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(UPDATE_TAG.getOperation());
                System.out.println(Constants.TAG_ID_ENTER);
                Long authorId = Utils.getKeyboardNumber(scanner, Constants.TAG_ID);
                System.out.println(Constants.TAG_NAME_ENTER);
                String name = scanner.nextLine();
                dtoRequest = new TagDtoRequest(authorId, name);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(tagController.update(dtoRequest));

    }

    @Override
    public CommandType getCommandType() {
        return UPDATE_TAG;
    }

}
