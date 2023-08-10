package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.ValidatorException;
import com.mjc.school.service.mappers.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.TAG_ID_DOES_NOT_EXIST;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {
    private final TagRepository repository;

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return TagMapper.INSTANCE.tagModelListToTagDtoResponseList(repository.readAll());

    }

    @Override
    public TagDtoResponse readById(Long id) {
        return TagMapper.INSTANCE.tagModelToTagDtoResponse(repository.readById(id)
                .orElseThrow(() -> new ValidatorException(String
                        .format(TAG_ID_DOES_NOT_EXIST.getMessage(), id))));
    }

    public List<TagDtoResponse> readTagsByNewsId(Long id) {
        return TagMapper.INSTANCE.tagModelListToTagDtoResponseList(repository.readTagsByNewsId(id));
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        TagModel model = TagMapper.INSTANCE.tagModelDtoToTagModel(createRequest);
        return TagMapper.INSTANCE.tagModelToTagDtoResponse(repository.create(model));
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        if (repository.existById(updateRequest.id())) {
            TagModel model = TagMapper.INSTANCE.tagModelDtoToTagModel(updateRequest);
            return TagMapper.INSTANCE.tagModelToTagDtoResponse(repository.update(model));
        } else {
            throw new ValidatorException(String
                    .format(TAG_ID_DOES_NOT_EXIST.getMessage(), updateRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existById(id)) {
            return repository.deleteById(id);
        } else {
            throw new ValidatorException(String
                    .format(TAG_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
