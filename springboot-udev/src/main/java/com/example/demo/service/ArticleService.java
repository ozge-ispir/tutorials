package com.example.demo.service;

import com.example.demo.helper.ArticleMapper;
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
    private ArticleMapper articleMapper;

    public List<ArticleJSON> getAllArticles() {
        log.info("Called for getAllArticles ...");
        List<Article> articleList = articleRepository.findAll();
        return articleMapper.mapTo(articleList);
    }

    @Cacheable(cacheManager = "redisCacheManager", cacheNames = "articles", key = "#id")
    public ArticleJSON getArticleById(long id) {
        log.info("Called for getArticleById ...");
        Article b = articleRepository.getOne(id);
        return articleMapper.mapTo(b);
    }

    public ArticleJSON addArticle(ArticleJSON subject) {
        Article b = articleRepository.save(articleMapper.mapTo(subject));
        return articleMapper.mapTo(b);
    }

    public List<ArticleJSON> getAllArticlesByAuthor(String author) {
        List<Article> articleList = articleRepository.findByAuthor(author);
        return articleMapper.mapTo(articleList);
    }

    public List<ArticleJSON> getAllArticlesByTitle(String title) {
        List<Article> articleList = articleRepository.findByTitle(title);
        return articleMapper.mapTo(articleList);
    }
}
