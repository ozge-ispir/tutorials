package com.example.demo.controller;

import com.example.demo.exceptions.ForbiddenException;
import com.example.demo.pojo.ArticleJSON;
import com.example.demo.service.AuthService;
import com.example.demo.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = {"Article"})
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private AuthService authService;

    @ApiOperation(value = "Get all Articles")
    @RequestMapping(method = RequestMethod.GET)
    public List<ArticleJSON> getAll() {
        return articleService.getAllArticles();
    }

    @ApiOperation(value = "Get one Book", response = ArticleJSON.class)
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ArticleJSON getArticle(@ApiParam(value = "Article id", required = true) @PathVariable long id) {
        return articleService.getArticleById(id);
    }

    @ApiOperation(value = "Add Article", response = ArticleJSON.class)
    @RequestMapping(method = RequestMethod.POST, headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleJSON addArticle(@ApiParam(value = "Article to Add", required = true) @Valid @RequestBody ArticleJSON article) {
        // test with @Valid : @Valid @RequestBody ... get Spring Bad Request 400 if NotEmpty

        return articleService.addArticle(article);
    }

    @ApiOperation(value = "Get Article by Author")
    @RequestMapping(method = RequestMethod.GET, value = "/author/{author}")
    // FIXME not good path for REST ...
    public List<ArticleJSON> getAllArticlesByAuthor(@ApiParam(value = "Author", required = true) @PathVariable String author) {
        return articleService.getAllArticlesByAuthor(author);
    }

    @ApiOperation(value = "Get Article by Title")
    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
    // FIXME not good path for REST ...
    public List<ArticleJSON> getAllArticlesByTitle(@ApiParam(value = "Title", required = true) @PathVariable String title) {
        return articleService.getAllArticlesByTitle(title);
    }
}
