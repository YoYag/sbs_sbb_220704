package com.mysite.sbb.Article.dao;

import com.mysite.sbb.Article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);

    List<Article> findByBody(String body);

    List<Article> findByTitleOrBody(String title, String body);
}
