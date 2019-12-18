package com.example.demo.service;

import com.example.demo.helper.ArticleMapper;
import com.example.demo.helper.UserMapper;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.ArticleJSON;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserJSON;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    public List<ArticleJSON> getAllArticles() {
        log.info("Called for getAllArticles ...");
        List<Article> articleList = articleRepository.findAll();

        List<ArticleJSON> articleJSONS = articleList.stream().map(a -> {
            User user = userRepository.getOne(a.getAuthor());
            UserJSON userJSON = userMapper.mapTo(user);
            ArticleJSON articleJSON = articleMapper.mapTo(a);
            articleJSON.setAuthor(userJSON);
            return articleJSON;
        }).collect(Collectors.toList());

        return articleJSONS;
    }

    @Cacheable(cacheManager = "redisCacheManager", cacheNames = "articles", key = "#id")
    public ArticleJSON getArticleById(long id) {
        log.info("Called for getArticleById ...");
        Article a = articleRepository.getOne(id);
        return articleMapper.mapTo(a);
    }

    public ArticleJSON addArticle(ArticleJSON subject) {
        Article a = articleRepository.save(articleMapper.mapTo(subject));
        return articleMapper.mapTo(a);
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
