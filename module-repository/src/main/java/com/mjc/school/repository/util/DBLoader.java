package com.mjc.school.repository.util;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Component
public class DBLoader {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String AUTHORS_FILE_NAME = "authors";
    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private static final String TAGS_FILE_NAME = "tags";

    @Transactional
    @Bean
    public void loadDB() {
        //writeAuthors();
        //writeTags();
        writeNews();
    }

   /* @Transactional
    void writeAuthors() {
        List<String> authors = DataReader.read(AUTHORS_FILE_NAME);
        Set<Integer> prevs = new HashSet<>();
        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            int index = rnd.nextInt(authors.size());
            if (prevs.contains(index)) {
                i--;
            } else {
                prevs.add(index);
                AuthorModel am = new AuthorModel();
                am.setName(authors.get(index));
                entityManager.persist(am);
            }
        }
        System.out.println(entityManager.find(AuthorModel.class, 2L));
    }*/
   @Transactional
   void writeNews() {
       List<String> authors = DataReader.read(AUTHORS_FILE_NAME);
       List<String> tags = DataReader.read(TAGS_FILE_NAME);
       Set<Integer> prevs = new HashSet<>();
       List<TagModel> tagModels = new LinkedList<>();
       List<AuthorModel> authorModels = new LinkedList<>();
       Random rnd = new Random();
       for (long i = 1; i <= 20; i++) {
           int index = rnd.nextInt(tags.size());
           if (prevs.contains(index)) {
               i--;
           } else {
               prevs.add(index);
               TagModel tm = new TagModel();
               AuthorModel am = new AuthorModel();
               tm.setName(tags.get(index));
               am.setName(authors.get(index));
               tagModels.add(tm);
               authorModels.add(am);
           }
       }
       List<String> content = DataReader.read(CONTENT_FILE_NAME);
       List<String> titles = DataReader.read(NEWS_FILE_NAME);
       for (long i = 1; i <= 20; i++) {
           NewsModel nm = new NewsModel();
           for (int j = 0; j < rnd.nextInt(2, 6); j++) {
               nm.getTags().add(tagModels.get(rnd.nextInt(20)));
           }
           nm.setTitle(titles.get(rnd.nextInt(titles.size())));
           nm.setContent(content.get(rnd.nextInt(content.size())));
           nm.setAuthor(authorModels.get(rnd.nextInt(authorModels.size())));
           entityManager.persist(nm);
       }
   }
    /*@Transactional
    void writeNews() {
        List<AuthorModel> authors = entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
        List<String> tags = DataReader.read(TAGS_FILE_NAME);
        Set<Integer> prevs = new HashSet<>();
        List<TagModel> tagModels = new LinkedList<>();
        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            int index = rnd.nextInt(tags.size());
            if (prevs.contains(index)) {
                i--;
            } else {
                prevs.add(index);
                TagModel tm = new TagModel();
                tm.setName(tags.get(index));
                tagModels.add(tm);
            }
        }
        List<String> content = DataReader.read(CONTENT_FILE_NAME);
        List<String> titles = DataReader.read(NEWS_FILE_NAME);
        for (long i = 1; i <= 20; i++) {
            NewsModel nm = new NewsModel();
            for (int j = 0; j < rnd.nextInt(2, 6); j++) {
                nm.getTags().add(tagModels.get(rnd.nextInt(20)));
            }
            nm.setTitle(titles.get(rnd.nextInt(titles.size())));
            nm.setContent(content.get(rnd.nextInt(content.size())));
            nm.setAuthor(authors.get(rnd.nextInt(authors.size())));
            entityManager.persist(nm);
        }
        int i = 10;
        //List<NewsModel> news2 = entityManager.createQuery("SELECT n FROM NewsModel n JOIN n.tags t join t.")
    }*/

    /*@Transactional
    void writeNews() {
        List<AuthorModel> authors = entityManager.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
        List<TagModel> tags = entityManager.createQuery("SELECT a FROM TagModel a", TagModel.class).getResultList();
        List<String> content = DataReader.read(CONTENT_FILE_NAME);
        List<String> titles = DataReader.read(NEWS_FILE_NAME);
        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            NewsModel nm = new NewsModel();
            for (int j = 0; j < rnd.nextInt(2, 6); j++) {
                nm.getTags().add(tags.get(rnd.nextInt(20)));
            }
            nm.setTitle(titles.get(rnd.nextInt(titles.size())));
            nm.setContent(content.get(rnd.nextInt(content.size())));
            nm.setAuthor(authors.get(rnd.nextInt(authors.size())));
            entityManager.persist(nm);
        }
        int i = 10;
        //List<NewsModel> news2 = entityManager.createQuery("SELECT n FROM NewsModel n JOIN n.tags t join t.")
    }*/

    /*@Transactional
    void writeTags() {
        List<String> tags = DataReader.read(TAGS_FILE_NAME);
        List<Integer> prevs = new LinkedList<>();
        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            int index = rnd.nextInt(tags.size());
            if (prevs.contains(index)) {
                i--;
            } else {
                prevs.add(index);
                TagModel tm = new TagModel();
                tm.setName(tags.get(index));
                entityManager.persist(tm);
            }
        }
    }*/
}
