package com.mjc.school.service.aspects;

import org.aspectj.lang.annotation.Pointcut;


public class Pointcuts {
    @Pointcut("execution(* com.mjc.school.service.impl.AuthorService.update(*))")
    public void authorUpdate() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.AuthorService.create(*))")
    public void authorCreate() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.AuthorService.readAuthorByNewsId(*))")
    public void AuthorByNewsId() {
    }

    @Pointcut("authorUpdate() || authorCreate()")
    public void authorServiceUpdateAndCreateMethods() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.NewsService.update(*))")
    public void newsUpdate() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.NewsService.create(*))")
    public void newsCreate() {
    }

    @Pointcut("newsUpdate() || newsCreate()")
    public void newsServiceUpdateAndCreateMethods() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.TagService.update(*))")
    public void tagUpdate() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.TagService.create(*))")
    public void tagCreate() {
    }

    @Pointcut("execution(* com.mjc.school.service.impl.TagService.readTagsByNewsId(*))")
    public void tagByNewsId() {

    }

    @Pointcut("tagUpdate() || tagCreate()")
    public void tagServiceUpdateAndCreateMethods() {
    }

    @Pointcut("AuthorByNewsId() || newsUpdate()")
    public void newsIdValidation() {

    }

    @Pointcut("execution(* com.mjc.school.service.impl.NewsService.readNewsByParams(*,*,*,*,*))")
    public void newsByParams(){
    }

    @Pointcut("execution(* com.mjc.school.service.impl.*Service.*ById(Long))")
    public void readDeleteByID() {

    }

}
