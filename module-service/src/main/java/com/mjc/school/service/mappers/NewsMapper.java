package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    List<NewsDtoResponse> newsModelListToNewsDtoResponseList(List<NewsModel> newsModelList);
    @Mappings(value = {@Mapping(target = "authorId", source = "newsModel.author.id")})
    NewsDtoResponse newsModelToNewsDtoResponse(NewsModel newsModel);

    @Mappings(value = {
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdatedDate", ignore = true),
            @Mapping(target = "author", expression = "java(com.mjc.school.service.mappers.Creator.authorCreator(newsDtoRequest.authorId()))"),
            @Mapping(target = "tags", expression = "java(com.mjc.school.service.mappers.Creator.tagListCreator(newsDtoRequest.tagIds()))"),
    })
    NewsModel newsDtoRequestToNewsModel(NewsDtoRequest newsDtoRequest);
}

class Creator{
    static AuthorModel authorCreator(Long id){
        AuthorModel a = new AuthorModel();
        a.setId(id);
        return a;
    }
    static List<TagModel> tagListCreator(List<Long> tagIds){
        if(tagIds == null) return new LinkedList<>();
        List<TagModel> l = new LinkedList<>();
        for(Long id: tagIds){
            TagModel t = new TagModel();
            t.setId(id);
            l.add(t);
        }
        return l;
    }
}
