package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {

    private final BaseService<TagDtoRequest, TagDtoResponse, Long> service;

    @Autowired
    public TagController(TagService tagService) {
        this.service = tagService;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return service.readAll();
    }

    @Override
    public TagDtoResponse readById(Long id) {
        return service.readById(id);
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
