package com.mjc.school.service.aspects;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidatorException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;
import static com.mjc.school.service.exceptions.ServiceErrorCode.VALIDATE_STRING_LENGTH;

@Component
@Aspect
public class ValidationAspect {
    private static final String AUTHOR_ID = "Author id";
    private static final String AUTHOR_NAME = "Author name";
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 3;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;
    private static final String NEWS_ID = "News id";
    private static final String NEWS_CONTENT = "News content";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final String TAG_ID = "Tag id";
    private static final String TAG_NAME = "Tag name";
    private static final Integer TAG_NAME_MIN_LENGTH = 3;
    private static final Integer TAG_NAME_MAX_LENGTH = 15;

    static AuthorRepository authorRepository;
    static TagRepository tagRepository;

    @Autowired
    ValidationAspect(AuthorRepository authorRepository, TagRepository tagRepository) {
        ValidationAspect.authorRepository = authorRepository;
        ValidationAspect.tagRepository = tagRepository;
    }

    @Before("Pointcuts.authorServiceUpdateAndCreateMethods()")
    public void validateAuthorDtoAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof AuthorDtoRequest dtoRequest) {
            validateString(dtoRequest.name(),
                    AUTHOR_NAME,
                    AUTHOR_NAME_MIN_LENGTH,
                    AUTHOR_NAME_MAX_LENGTH);
        }
    }

    @Before("Pointcuts.authorUpdate()")
    public void validateAuthorCreateDtoAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof AuthorDtoRequest dtoRequest) {
            validateNumber(dtoRequest.id(), AUTHOR_ID);
        }
    }

    @Before("Pointcuts.tagServiceUpdateAndCreateMethods()")
    public void validateTagDtoAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof TagDtoRequest dtoRequest) {
            validateString(dtoRequest.name(),
                    TAG_NAME,
                    TAG_NAME_MIN_LENGTH,
                    TAG_NAME_MAX_LENGTH);
        }
    }

    @Before("Pointcuts.tagUpdate()")
    public void validateTagCreateDtoAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof TagDtoRequest dtoRequest) {
            validateNumber(dtoRequest.id(), TAG_ID);
        }
    }
    @Before("Pointcuts.newsServiceUpdateAndCreateMethods()")
    public void validateNewsDto(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof NewsDtoRequest dtoRequest) {
            validateString(dtoRequest.title(),
                    NEWS_TITLE,
                    NEWS_TITLE_MIN_LENGTH,
                    NEWS_TITLE_MAX_LENGTH);
            validateString(dtoRequest.content(),
                    NEWS_CONTENT,
                    NEWS_CONTENT_MIN_LENGTH,
                    NEWS_CONTENT_MAX_LENGTH);
            validateAuthorId(dtoRequest.authorId());
            if(dtoRequest.tagIds()!= null)dtoRequest.tagIds().forEach(ValidationAspect::validateTagId);
        }
    }

    @Before("Pointcuts.newsIdValidation()")
    public void validateNewsId(JoinPoint joinPoint) {
        if (joinPoint.getArgs()[0] instanceof NewsDtoRequest dtoRequest) {
            validateNumber(dtoRequest.id(), NEWS_ID);
        }
        else if(joinPoint.getArgs()[0] instanceof Long id){
            validateNumber(id, NEWS_ID);
        }
        else throw new ValidatorException(
                String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), NEWS_ID, NEWS_ID, null));
    }
    @Before("Pointcuts.readDeleteByID()")
    public void validateNumberAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof Long id) {
            if (id < 1) {
                throw new ValidatorException(
                        String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), AUTHOR_ID, AUTHOR_ID, id));
            }
        } else throw new ValidatorException(
                String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), AUTHOR_ID, AUTHOR_ID, null));
    }

    @Before("Pointcuts.newsByParams()")
    public void validateNewsParamsAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        boolean noArgsFlag = true;
        if(args[0] != null && args[0] instanceof List tagIds){
            tagIds.forEach((elem)->{
                if(elem instanceof Long id){
                    validateTagId(id);
                }
                else throw new IllegalArgumentException(String.format(VALIDATE_INT_VALUE.getMessage(), "tag id"));
            });
            noArgsFlag = false;
        }
        if(args[1] != null && args[1] instanceof String str){
            if(str.length() != 0) {
                validateString(str,
                        TAG_NAME,
                        TAG_NAME_MIN_LENGTH,
                        TAG_NAME_MAX_LENGTH);
                noArgsFlag = false;
            }

        }
        if(args[2] != null && args[2] instanceof String str){
            if(str.length() != 0) {
                validateString(str,
                        AUTHOR_NAME,
                        AUTHOR_NAME_MIN_LENGTH,
                        AUTHOR_NAME_MAX_LENGTH);
                noArgsFlag = false;
            }
        }
        if(args[3] != null && args[3] instanceof String str){
            if(str.length() != 0) {
                validateString(str,
                        NEWS_TITLE,
                        NEWS_TITLE_MIN_LENGTH,
                        NEWS_TITLE_MAX_LENGTH);
                noArgsFlag = false;
            }

        }
        if(args[4] != null && args[4] instanceof String str){
            if(str.length() != 0) {
                validateString(str,
                        NEWS_CONTENT,
                        NEWS_CONTENT_MIN_LENGTH,
                        NEWS_CONTENT_MAX_LENGTH);
                noArgsFlag = false;
            }

        }
        if(noArgsFlag) throw new NotFoundException(String.format(
                String.valueOf(NO_VALID_ARGUMENT_GIVEN.getMessage())));
    }


    private void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (!authorRepository.existById(authorId)) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }

    private static void validateTagId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (!tagRepository.existById(authorId)) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }

    private static void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(
                    String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(
                    String.format(
                            VALIDATE_STRING_LENGTH.getMessage(),
                            parameter,
                            minLength,
                            maxLength,
                            parameter,
                            value));
        }
    }
}
