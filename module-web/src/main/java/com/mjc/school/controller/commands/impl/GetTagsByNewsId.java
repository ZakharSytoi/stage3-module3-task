package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.TagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.util.Utils.getKeyboardNumber;
import static com.mjc.school.controller.commands.CommandType.GET_TAGS_BY_NEWS_ID;
import static com.mjc.school.controller.commands.Constants.*;

@Component
public class GetTagsByNewsId implements Command {
    private final TagController tagController;

    @Autowired
    public GetTagsByNewsId(TagController tagController){
        this.tagController = tagController;
    }
    @Override
    public void execute(Scanner scanner) {
        System.out.println(GET_TAGS_BY_NEWS_ID.getOperation());
        System.out.println(NEWS_ID_ENTER);
        System.out.println(tagController.readById(getKeyboardNumber(scanner, TAG_ID)));
    }

    @Override
    public CommandType getCommandType() {
        return GET_TAGS_BY_NEWS_ID;
    }
}
