package com.mjc.school.service.impl;

import com.mjc.school.service.Config;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import(Config.class)
class AuthorServiceTest {
    @Autowired
    AuthorService authorService;
    @Autowired
    NewsService newsService;

    @Test
    void readAll() {
        int size = authorService.readAll().size();
        authorService.create(new AuthorDtoRequest(null, "Mateusz Mroz"));
        Assertions.assertEquals(size + 1, authorService.readAll().size());
    }

    @Test
    void readById() {
        int size = authorService.readAll().size();
        Assertions.assertTrue(size != 0);
    }

    @Test
    void create() {
        int size = authorService.readAll().size();
        authorService.create(new AuthorDtoRequest(null, "Mateusz Mroz"));
        Assertions.assertEquals("Mateusz Mroz", authorService.readById((long) (size+1)).name());
    }

    @Test
    void update() {
        authorService.update(new AuthorDtoRequest(7L, "Mateusz Mroz"));
        Assertions.assertEquals("Mateusz Mroz", authorService.readById(7L).name());
    }

    @Test
    void deleteByIdTest() {
        int currentSize = authorService.readAll().size();
        authorService.deleteById(5L);
        Assertions.assertTrue(currentSize > authorService.readAll().size());
    }

    @Test
    void readByIdThrowValidateExceptionTest() {
        Exception exception = Assertions.assertThrows(ValidatorException.class, () -> authorService.readById(-1L));
        String expectedMessage = String.format(ServiceErrorCode.VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), "Author id", "Author id", -1);
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));

    }

    @Test
    void createAuthorThrowValidatorExceptionTest() {
        Exception exception = Assertions.assertThrows(ValidatorException.class, () -> authorService.create(new AuthorDtoRequest(null, "jh")));
        String expectedMessage = String.format(
                ServiceErrorCode.VALIDATE_STRING_LENGTH.getMessage(),
                "Author name",
                3,
                15,
                "Author name",
                "jh");
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void readAuthorByNewsId() {
        AuthorDtoResponse aut = authorService.readById(2L);
        newsService.update(new NewsDtoRequest(
                10L,
                "ValidTitle",
                "ValidContent",
                3L,
                null));
        Assertions.assertEquals(3L, authorService.readAuthorByNewsId(10L).id());

    }
}