package com.mjc.school.service;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Test {
    public static void main(String[] args) {
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);



       /* try{
            System.out.println(ctx.getBean("newsService", NewsService.class).readById(3L));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/
            /*AuthorService aus = ctx.getBean("authorService", AuthorService.class);
            aus.readAll().forEach(System.out::println);*/

            //System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "AAAAAA", "AAAAAAAAAAAAAAAA", 10L, List.of(1L, 5L, 6L))));
            //System.out.println(ctx.getBean("newsService", NewsService.class).update(new NewsDtoRequest(21L, "AAABBB", "AAAAAAAABBBBBBBB", 11L, List.of(1L, 5L, 6L, 10L))));


        /*try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).create(new AuthorDtoRequest(null, "nullddddd")));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/
        /*try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).create(new AuthorDtoRequest(null, "a")));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).create(new AuthorDtoRequest(null, null)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(10L, null, null, null, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "aaa", null, null, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "aaaaaaaa", "11", null, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "bbbbbbbbbb", "bbbbbbbbbbbbbbbb", 1000L, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
*/
        /*try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "AAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAA", 11L, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).create(new NewsDtoRequest(null, "bbbbbbbbbb", "bbbbbbbbbbbbbbbb", 10L, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            System.out.println(ctx.getBean("newsService", NewsService.class).update(new NewsDtoRequest(18L, "bbbbbbbbbb", "bbbbbbbbbbbbbbbb", 10L, List.of(1L, 5L, 6L))));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            System.out.println(ctx.getBean("tagService", TagService.class).deleteById(5L));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("authorService", AuthorService.class).deleteById(10L));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(ctx.getBean("tagService", TagService.class).deleteById(6L));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/


    }


}
