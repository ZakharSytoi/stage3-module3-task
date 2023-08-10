package com.mjc.school.repository.impl;


import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("SELECT a FROM TagModel a", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable((entityManager.find(TagModel.class, id)));
    }

    @Override
    @Transactional
    public TagModel create(TagModel entity) {
        entityManager.persist(entity);
        return entityManager.find(TagModel.class, entity.getId());
    }

    @Override
    @Transactional
    public TagModel update(TagModel entity) {
        entityManager.merge(entity);
        return entityManager.find(TagModel.class, entity.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        TagModel toRemove = entityManager.find(TagModel.class, id);
        if (toRemove != null) {
            toRemove.getNewsModel().forEach(news->{
                NewsModel tmpNews = entityManager.find(NewsModel.class, news.getId());
                tmpNews.getTags().remove(toRemove);
                entityManager.persist(tmpNews);
            });
            entityManager.remove(toRemove);
        } else return false;
        return true;
    }

    @Transactional
    public List<TagModel> readTagsByNewsId(Long id){
        return entityManager.find(NewsModel.class, id).getTags();
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.find(TagModel.class, id) != null;
    }
}
