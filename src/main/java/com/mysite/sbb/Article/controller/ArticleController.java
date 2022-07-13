package com.mysite.sbb.Article.controller;

import com.mysite.sbb.Article.dao.ArticleRepository;
import com.mysite.sbb.Article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    // 생성
    @RequestMapping("doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if(title == null || title.trim().length() == 0) {
            return "제목을 입력해주세요.";
        }

        title = title.trim();

        if(body == null || body.trim().length() == 0) {
            return "내용을 입력해주세요.";
        }

        body = body.trim();

        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }

    // 조회
    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showArticles(String title, String body) {
        if(title != null || body != null) {
            List<Article> articles = articleRepository.findByTitleOrBody(title, body);
            return articles;
        }

        return articleRepository.findAll();
    }

    // 단건 조회
    @RequestMapping("detail")
    @ResponseBody
    public Article showArticle(@RequestParam long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null); // article id 값이 없으면 null 반환
    }

    // 수정
    @RequestMapping("doModify")
    @ResponseBody
    public Article doModify(@RequestParam long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if(title != null) {
            article.setTitle(title);
        }

        if(body != null){
            article.setBody(body);
        }

        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);

        return article;
    }

    // 삭제
    @RequestMapping("doDelete")
    @ResponseBody
    public String doDelete(@RequestParam long id) {

        if(articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return String.format("%d번 게시물이 삭제되었습니다.", id);
        }
        return String.format("이미 삭제되었거나 없는 게시물입니다.");
    }
}