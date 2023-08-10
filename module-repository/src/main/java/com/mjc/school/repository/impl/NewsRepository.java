package com.mjc.school.repository.impl;


import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("SELECT a FROM NewsModel a", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable((entityManager.find(NewsModel.class, id)));
    }

    @Override
    @Transactional
    public NewsModel create(NewsModel entity) {
        entityManager.merge(entity);
        //return entityManager.find(NewsModel.class, entity.getId());
        return entityManager
                .createQuery("SELECT n FROM NewsModel n WHERE n.id = (SELECT MAX(n2.id) FROM NewsModel n2)"
                        , NewsModel.class).getResultList().get(0);

    }

    @Override
    @Transactional
    public NewsModel update(NewsModel entity) {
        entity.setCreateDate(entityManager.createQuery("SELECT a.createDate FROM NewsModel a WHERE id = :id", LocalDateTime.class).setParameter("id", entity.getId()).getSingleResult());
        entityManager.merge(entity);
        return entityManager.find(NewsModel.class, entity.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        NewsModel toRemove = entityManager.find(NewsModel.class, id);
        if (toRemove != null) {
            entityManager.remove(toRemove);
        } else return false;
        return true;
    }

    public List<NewsModel> readNewsByParams(List<Long> tagsIds,
                                            String tagName,
                                            String authorName,
                                            String title,
                                            String content) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<NewsModel> query = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = query.from(NewsModel.class);

        List<Predicate> predicates = new ArrayList<>();
        if (tagName != null || tagsIds != null) {
            Join<TagModel, NewsModel> newsTags = root.join("tags");
            if (tagName != null) {
                predicates.add(newsTags.get("name").in(tagName));
            }
            if (tagsIds != null) {
                predicates.add(newsTags.get("id").in(tagsIds));
            }
        }
        if (authorName != null) {
            Join<AuthorModel, NewsModel> newsAuthor = root.join("author");
            predicates.add(criteriaBuilder.equal(newsAuthor.get("name"), authorName));
        }
        if (title != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
        if (content != null) {
            predicates.add(criteriaBuilder.like(root.get("content"), "%" + content + "%"));
        }
        query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.find(NewsModel.class, id) != null;
    }
}
