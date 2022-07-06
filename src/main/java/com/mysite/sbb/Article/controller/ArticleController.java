package com.mysite.sbb.Article.controller;

import com.mysite.sbb.Article.dao.ArticleRepository;
import com.mysite.sbb.Article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> articles() {
        return articleRepository.findAll();
    }

    @RequestMapping("detail")
    @ResponseBody
    public Article article() {
        Optional<Article> article = articleRepository.findById(1L);
        return article.get();
    }
}
