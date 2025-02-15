package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-10T15:39:45+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class TagMapperImpl implements TagMapper {

    @Override
    public List<TagDtoResponse> tagModelListToTagDtoResponseList(List<TagModel> TagModelList) {
        if ( TagModelList == null ) {
            return null;
        }

        List<TagDtoResponse> list = new ArrayList<TagDtoResponse>( TagModelList.size() );
        for ( TagModel tagModel : TagModelList ) {
            list.add( tagModelToTagDtoResponse( tagModel ) );
        }

        return list;
    }

    @Override
    public TagDtoResponse tagModelToTagDtoResponse(TagModel TagModel) {
        if ( TagModel == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = TagModel.getId();
        name = TagModel.getName();

        TagDtoResponse tagDtoResponse = new TagDtoResponse( id, name );

        return tagDtoResponse;
    }

    @Override
    public TagModel tagModelDtoToTagModel(TagDtoRequest TagDtoRequest) {
        if ( TagDtoRequest == null ) {
            return null;
        }

        TagModel tagModel = new TagModel();

        tagModel.setId( TagDtoRequest.id() );
        tagModel.setName( TagDtoRequest.name() );

        return tagModel;
    }
}
