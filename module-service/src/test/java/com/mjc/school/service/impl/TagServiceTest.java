package com.mjc.school.service.impl;


import com.mjc.school.service.Config;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest()
@Import(Config.class)
class TagServiceTest {
    @Autowired
    TagService tagService;
    @Autowired
    NewsService newsService;

    @Test
    void readAll() {
        int size = tagService.readAll().size();
        tagService.create(new TagDtoRequest(null, "ValidTagName"));
        Assertions.assertEquals(size+1, tagService.readAll().size());
    }

    @Test
    void readById() {
        int size = tagService.readAll().size();
        tagService.create(new TagDtoRequest(null, "ValidTagName"));
        Assertions.assertEquals(new TagDtoResponse((long) (size+1),"ValidTagName"), tagService.readById((long) (size+1)));
    }

    @Test
    void create() {
        int size = tagService.readAll().size();
        tagService.create(new TagDtoRequest(null, "ValidTagName"));
        Assertions.assertEquals(size+1, tagService.readAll().size());
    }

    @Test
    void update() {
        int size = tagService.readAll().size();
        tagService.update(new TagDtoRequest(8L, "ValidTagName"));
        Assertions.assertEquals(new TagDtoResponse((long) (8L),"ValidTagName"), tagService.readById(8L));
    }

    @Test
    void deleteById() {
        int size = tagService.readAll().size();
        tagService.deleteById(1L);
        Assertions.assertEquals(size-1, tagService.readAll().size());
    }

    @Test
    void readTagsByNewsId() {
        TagDtoResponse aut = tagService.readById(2L);
        newsService.update(new NewsDtoRequest(
                10L,
                "ValidTitle",
                "ValidContent",
                3L,
                List.of(1L, 5L, 8L)));
        Assertions.assertEquals(List.of(1L, 5L, 8L), tagService.readTagsByNewsId(10L).stream().map(TagDtoResponse::id).toList());
    }
}