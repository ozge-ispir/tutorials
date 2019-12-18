package com.example.demo.helper;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.ArticleJSON;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public Article mapTo(ArticleJSON articleJSON) {
        Assert.notNull(articleJSON, "The articleJSON must not be null");
        Article a = new Article();
        // must not set id !
        a.setTitle(articleJSON.getTitle());
        a.setMessage(articleJSON.getMessage());
        a.setAuthor(articleJSON.getAuthor().getId());
        return a;
    }

    public ArticleJSON mapTo(Article article) {
        Assert.notNull(article, "The article must not be null");
        ArticleJSON aJSON = new ArticleJSON();
        aJSON.setTitle(article.getTitle());
        aJSON.setMessage(article.getMessage());
        aJSON.setId(article.getId());
        return aJSON;
    }

    public List<ArticleJSON> mapTo(List<Article> articleList) {
        Assert.notNull(articleList, "The articleList must not be null");
        return articleList.stream().map(this::mapTo).collect(Collectors.toList());
    }

}
