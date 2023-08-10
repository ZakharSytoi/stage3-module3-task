package com.mjc.school.controller.commands.impl;

import com.mjc.school.controller.commands.Command;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.util.DataAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static com.mjc.school.controller.commands.CommandType.GET_NEWS_BY_PARAMS;
import static com.mjc.school.controller.commands.Constants.*;
import static com.mjc.school.controller.util.Utils.getTagsList;

@Component
public class GetNewsByParams implements Command {
    private final NewsController newsController;

    @Autowired
    public GetNewsByParams(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner scanner) {
        DataAggregator dta = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(GET_NEWS_BY_PARAMS.getOperation());

                System.out.println(TAG_NAME_ENTER);
                String tagName = scanner.nextLine();

                System.out.println(NEWS_TITLE_ENTER);
                String title = scanner.nextLine();

                System.out.println(NEWS_CONTENT_ENTER);
                String content = scanner.nextLine();

                System.out.println(AUTHOR_NAME_ENTER);
                String authorName = scanner.nextLine();
                List<Long> tagIds = getTagsList(scanner);
                dta = new DataAggregator(tagIds, tagName, authorName, title, content);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(newsController.readNewsByParams(dta.tagsIds(),
                dta.tagName(),
                dta.authorName(),
                dta.title(),
                dta.content()));
    }

    @Override
    public CommandType getCommandType() {
        return GET_NEWS_BY_PARAMS;
    }
}
