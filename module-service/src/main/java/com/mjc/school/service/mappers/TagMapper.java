package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    List<TagDtoResponse> tagModelListToTagDtoResponseList(List<TagModel> TagModelList);

    TagDtoResponse tagModelToTagDtoResponse(TagModel TagModel);

    TagModel tagModelDtoToTagModel(TagDtoRequest TagDtoRequest);

}
