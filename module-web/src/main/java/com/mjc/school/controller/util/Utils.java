package com.mjc.school.controller.util;

import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.controller.commands.Constants;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static com.mjc.school.controller.commands.Constants.TAG_ID;
import static com.mjc.school.controller.commands.Constants.TAG_ID_ENTER;

public class Utils {

    public static void printMainMenu() {
        System.out.println(Constants.NUMBER_OPERATION_ENTER);
        for (CommandType command : CommandType.values()) {
            System.out.println(command.getOperationWithNumber());
        }
    }

    public static long getKeyboardNumber(Scanner scanner, String param) {
        long enter;
        try {
            enter = scanner.nextLong();
            scanner.nextLine();
        } catch (Exception ex) {
            scanner.nextLine();
            throw new ValidatorException(
                    String.format(ServiceErrorCode.VALIDATE_INT_VALUE.getMessage(), param));
        }
        return enter;
    }

    public static List<Long> getTagsList(Scanner scanner){
        LinkedList<Long> tagIds = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            System.out.println(TAG_ID_ENTER);
            System.out.println("enter 0 to stop");
            Long id = getKeyboardNumber(scanner, TAG_ID);
            if(!id.equals(0L)){
                tagIds.add(id);
            }else break;
        }
        return tagIds;
    }




}

