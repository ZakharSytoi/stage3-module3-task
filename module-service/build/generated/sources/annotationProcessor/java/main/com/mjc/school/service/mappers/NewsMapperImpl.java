package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-10T15:39:45+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public List<NewsDtoResponse> newsModelListToNewsDtoResponseList(List<NewsModel> newsModelList) {
        if ( newsModelList == null ) {
            return null;
        }

        List<NewsDtoResponse> list = new ArrayList<NewsDtoResponse>( newsModelList.size() );
        for ( NewsModel newsModel : newsModelList ) {
            list.add( newsModelToNewsDtoResponse( newsModel ) );
        }

        return list;
    }

    @Override
    public NewsDtoResponse newsModelToNewsDtoResponse(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        Long authorId = null;
        Long id = null;
        String title = null;
        String content = null;
        LocalDateTime createDate = null;
        LocalDateTime lastUpdatedDate = null;

        authorId = newsModelAuthorId( newsModel );
        id = newsModel.getId();
        title = newsModel.getTitle();
        content = newsModel.getContent();
        createDate = newsModel.getCreateDate();
        lastUpdatedDate = newsModel.getLastUpdatedDate();

        NewsDtoResponse newsDtoResponse = new NewsDtoResponse( id, title, content, createDate, lastUpdatedDate, authorId );

        return newsDtoResponse;
    }

    @Override
    public NewsModel newsDtoRequestToNewsModel(NewsDtoRequest newsDtoRequest) {
        if ( newsDtoRequest == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setId( newsDtoRequest.id() );
        newsModel.setTitle( newsDtoRequest.title() );
        newsModel.setContent( newsDtoRequest.content() );

        newsModel.setAuthor( com.mjc.school.service.mappers.Creator.authorCreator(newsDtoRequest.authorId()) );
        newsModel.setTags( com.mjc.school.service.mappers.Creator.tagListCreator(newsDtoRequest.tagIds()) );

        return newsModel;
    }

    private Long newsModelAuthorId(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }
        AuthorModel author = newsModel.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
