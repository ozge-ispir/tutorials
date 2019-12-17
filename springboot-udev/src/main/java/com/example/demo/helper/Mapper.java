package com.example.demo.helper;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.ArticleJSON;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Article mapTo(ArticleJSON articleJSON) {
        Assert.notNull(articleJSON, "The articleJSON must not be null");
        Article b = new Article();
        // must not set id !
        b.setTitle(articleJSON.getTitle());
        b.setMessage(articleJSON.getMessage());
        b.setAuthor(articleJSON.getAuthor());
        return b;
    }

    public ArticleJSON mapTo(Article article) {
        Assert.notNull(article, "The article must not be null");
        ArticleJSON bJSON = new ArticleJSON();
        bJSON.setTitle(article.getTitle());
        bJSON.setMessage(article.getMessage());
        bJSON.setAuthor(article.getAuthor());
        bJSON.setId(article.getId());
        return bJSON;
    }

    public List<ArticleJSON> mapTo(List<Article> articleList) {
        Assert.notNull(articleList, "The articleList must not be null");
        return articleList.stream().map(this::mapTo).collect(Collectors.toList());
    }
}
