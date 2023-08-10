package com.mjc.school.controller.commands;

public enum CommandType {
    GET_ALL_NEWS(1, "Get all news."),
    GET_NEWS_BY_ID(2, "Get news by id."),
    CREATE_NEWS(3, "Create news."),
    UPDATE_NEWS(4, "Update news."),
    DELETE_NEWS_BY_ID(5, "Delete news."),
    GET_ALL_AUTHORS(6, "Get all authors."),
    GET_AUTHOR_BY_ID(7, "Get author by id."),
    CREATE_AUTHOR(8, "Create author."),
    UPDATE_AUTHOR(9, "Update author."),
    DELETE_AUTHOR_BY_ID(10, "Delete author."),
    GET_ALL_TAGS(11, "Get all tags."),
    GET_TAG_BY_ID(12, "Get tag by id."),
    CREATE_TAG(13, "Create tag."),
    UPDATE_TAG(14, "Update tag."),
    DELETE_TAG_BY_ID(15, "Delete tag."),
    GET_TAGS_BY_NEWS_ID(16, "Get tags by news id."),
    GET_AUTHOR_BY_NEWS_ID(17, "Get author by news id."),
    GET_NEWS_BY_PARAMS(18, "Get news by params"),
    EXIT(0, "Exit.");

    private final Integer operationNumber;
    private final String operation;

    CommandType(Integer operationNumber, String operation) {
        this.operationNumber = operationNumber;
        this.operation = operation;
    }

    public String getOperation() {
        return Constants.OPERATION + operation;
    }

    public String getOperationWithNumber() {
        return operationNumber + " - " + operation;
    }
}
