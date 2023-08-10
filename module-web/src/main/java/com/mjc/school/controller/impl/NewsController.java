package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final NewsService service;

    @Autowired
    public NewsController(NewsService newsService){
        this.service = newsService;
    }
    @Override
    public List<NewsDtoResponse> readAll() {
        return service.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return service.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }

    public List<NewsDtoResponse> readNewsByParams(List<Long> tagsIds,
                                                  String tagName,
                                                  String authorName,
                                                  String title,
                                                  String content){
        return service.readNewsByParams(tagsIds, tagName, authorName, title, content);
    }
}
