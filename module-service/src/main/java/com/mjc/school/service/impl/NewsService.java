package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mappers.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return NewsMapper.INSTANCE.newsModelListToNewsDtoResponseList(newsRepository.readAll());

    }

    public List<NewsDtoResponse> readNewsByParams(List<Long> tagsIds,
                                                  String tagName,
                                                  String authorName,
                                                  String title,
                                                  String content) {
        return NewsMapper.INSTANCE.newsModelListToNewsDtoResponseList(newsRepository.readNewsByParams(tagsIds, tagName, authorName, title, content));
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return NewsMapper.INSTANCE
                .newsModelToNewsDtoResponse(newsRepository.readById(id)
                        .orElseThrow(() ->
                                new NotFoundException(String.format(
                                        String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), id))));
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel model = NewsMapper.INSTANCE.newsDtoRequestToNewsModel(createRequest);
        return NewsMapper.INSTANCE.newsModelToNewsDtoResponse(newsRepository.create(model));
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (newsRepository.existById(updateRequest.id())) {
            NewsModel model = NewsMapper.INSTANCE.newsDtoRequestToNewsModel(updateRequest);
            return NewsMapper.INSTANCE.newsModelToNewsDtoResponse(newsRepository.update(model));
        } else {
            throw new NotFoundException(String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), updateRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)) {
            return newsRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), id));
        }
    }
}
