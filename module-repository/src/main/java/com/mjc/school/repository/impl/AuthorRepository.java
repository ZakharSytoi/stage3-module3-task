package com.mjc.school.repository.impl;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        return entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable((entityManager.find(AuthorModel.class, id)));
    }

    @Override
    @Transactional
    public AuthorModel create(AuthorModel entity) {
        entityManager.persist(entity);
        return entityManager.find(AuthorModel.class, entity.getId());
    }

    @Override
    @Transactional
    public AuthorModel update(AuthorModel entity) {
        entityManager.merge(entity);
        return entityManager.find(AuthorModel.class, entity.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        AuthorModel toRemove = entityManager.find(AuthorModel.class, id);
        if(toRemove!=null){
            entityManager.createQuery("DELETE NewsModel a WHERE author = :author")
                    .setParameter("author", toRemove).executeUpdate();
            entityManager.remove(toRemove);

        }else return false;
        return true;
    }
    @Transactional
    public AuthorModel readAuthorByNewsId(Long id){
        return entityManager.find(NewsModel.class, id).getAuthor();
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.find(AuthorModel.class, id) != null;
    }
}
