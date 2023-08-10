package com.mjc.school.service.impl;

import com.mjc.school.service.Config;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(Config.class)
class NewsServiceTest {
    @Autowired
    NewsService newsService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;

    @Test
    void readAll() {
        int size = newsService.readAll().size();
        newsService.create(new NewsDtoRequest(
                null,
                "ValidTitle",
                "ValidContent",
                3L,
                null));
        Assertions.assertEquals(size + 1, newsService.readAll().size());
    }

    @Test
    void readById() {
        int size = newsService.readAll().size();
        newsService.create(new NewsDtoRequest(
                null,
                "ValidTitle",
                "ValidContent",
                3L,
                null));
        NewsDtoResponse ndtr = newsService.readById((long) (size + 1));
        Assertions.assertEquals(size + 1, ndtr.id());
        Assertions.assertEquals("ValidTitle", ndtr.title());
        Assertions.assertEquals("ValidContent", ndtr.content());
        Assertions.assertEquals(3L, ndtr.authorId());


    }

    @Test
    void create() {
        int size = newsService.readAll().size();
        newsService.create(new NewsDtoRequest(
                null,
                "ValidTitle",
                "ValidContent",
                3L,
                null));
        Assertions.assertEquals(size + 1, newsService.readAll().size());

    }

    @Test
    void update() {
        newsService.update(new NewsDtoRequest(
                10L,
                "ValidTitle",
                "ValidContent",
                3L,
                null));
        NewsDtoResponse ndtr = newsService.readById((10L));
        Assertions.assertEquals(ndtr.id(), 10L);
        Assertions.assertEquals(ndtr.title(), "ValidTitle");
        Assertions.assertEquals(ndtr.content(), "ValidContent");
        Assertions.assertEquals(ndtr.authorId(), 3L);


    }

    @Test
    void deleteById() {
        int size = newsService.readAll().size();
        newsService.deleteById(1L);
        Assertions.assertEquals(size - 1, newsService.readAll().size());
    }

    @Test
    void readNewsByParams() {
        newsService.update(new NewsDtoRequest(10L, "ValidTitle", "ValidContent", 4L, List.of(2L, 3L, 4L)));
        assertTrue(newsService.readNewsByParams(List.of(2L, 3L, 4L), null, null, null, null).stream().map(NewsDtoResponse::id).toList().contains(10L));
        assertTrue(newsService.readNewsByParams(null, null, null, "ValidTitle", null).stream().map(NewsDtoResponse::id).toList().contains(10L));
        assertTrue(newsService.readNewsByParams(null, null, null, null, "ValidContent").stream().map(NewsDtoResponse::id).toList().contains(10L));
        assertTrue(newsService.readNewsByParams(null, tagService.readById(2L).name(), null, null, null).stream().map(NewsDtoResponse::id).toList().contains(10L));
        assertTrue(newsService.readNewsByParams(null, null, authorService.readById(4L).name(), null, null).stream().map(NewsDtoResponse::id).toList().contains(10L));
        assertTrue(newsService.readNewsByParams(null, null, authorService.readById(4L).name(), null, null).stream().map(NewsDtoResponse::id).toList().contains(10L));
        assertTrue(newsService.readNewsByParams(List.of(2L, 3L, 4L),
                tagService.readById(2L).name(),
                authorService.readById(4L).name(),
                "ValidTitle",
                "ValidContent").stream().map(NewsDtoResponse::id).toList().contains(10L));

    }

    @Test
    void readNewsByParamsThrowsExceptions(){
        assertThrows(NotFoundException.class, ()->newsService.readNewsByParams(null, null, null, null, null));
        assertThrows(ValidatorException.class, ()->newsService.readNewsByParams(List.of(100L, 10L), null, null, null, null));
        assertThrows(ValidatorException.class, ()->newsService.readNewsByParams(null, "1", null, null, null));
        assertThrows(ValidatorException.class, ()->newsService.readNewsByParams(null, null, "1", null, null));
        assertThrows(ValidatorException.class, ()->newsService.readNewsByParams(null,null , null,"1" , null));
        assertThrows(ValidatorException.class, ()->newsService.readNewsByParams(null,null , null, null,"1" ));
    }
}