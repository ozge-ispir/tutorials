package com.example.demo.service;

import com.example.demo.helper.Mapper;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.ArticleJSON;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private Mapper mapper;

    public List<ArticleJSON> getAllArticles() {
        log.info("Called for getAllArticles ...");
        List<Article> articleList = articleRepository.findAll();
        return mapper.mapTo(articleList);
    }

    @Cacheable(cacheManager = "redisCacheManager", cacheNames = "articles", key = "#id")
    public ArticleJSON getArticleById(long id) {
        log.info("Called for getArticleById ...");
        Article b = articleRepository.getOne(id);
        return mapper.mapTo(b);
    }

    public ArticleJSON addArticle(ArticleJSON subject) {
        Article b = articleRepository.save(mapper.mapTo(subject));
        return mapper.mapTo(b);
    }

    public List<ArticleJSON> getAllArticlesByAuthor(String author) {
        List<Article> articleList = articleRepository.findByAuthor(author);
        return mapper.mapTo(articleList);
    }

    public List<ArticleJSON> getAllArticlesByTitle(String title) {
        List<Article> articleList = articleRepository.findByTitle(title);
        return mapper.mapTo(articleList);
    }
}
