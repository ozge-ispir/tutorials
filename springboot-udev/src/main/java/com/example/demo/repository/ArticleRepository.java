package com.example.demo.repository;

import com.example.demo.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /*JpaRepository will have all the functions of CrudRepository and PagingAndSortingRepository. So if you don't need
     the repository to have the functions provided by JpaRepository and PagingAndSortingRepository , use CrudRepository.*/

    List<Article> findByAuthor(String author);

    @Query("SELECT b FROM Article b WHERE b.title LIKE %:title%")
    List<Article> findByTitle(String title);
}
