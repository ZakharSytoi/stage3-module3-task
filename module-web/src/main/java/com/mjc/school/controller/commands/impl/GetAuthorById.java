package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.AuthorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.controller.util.Utils.getKeyboardNumber;
import static com.mjc.school.controller.commands.CommandType.GET_AUTHOR_BY_ID;
import static com.mjc.school.controller.commands.CommandType.GET_AUTHOR_BY_NEWS_ID;
import static com.mjc.school.controller.commands.Constants.*;

@Component
public class GetAuthorById implements Command {

    private final AuthorController authorController;

    @Autowired
    public GetAuthorById(AuthorController authorController) {
        this.authorController = authorController;
    }


    @Override
    public void execute(Scanner scanner) {
        System.out.println(GET_AUTHOR_BY_NEWS_ID.getOperation());
        System.out.println(NEWS_ID_ENTER);
        System.out.println(authorController.readAuthorByNewsId(getKeyboardNumber(scanner, AUTHOR_ID)));
    }

    @Override
    public CommandType getCommandType() {
        return GET_AUTHOR_BY_ID;
    }
}
