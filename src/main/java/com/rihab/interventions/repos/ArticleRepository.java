package com.rihab.interventions.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rihab.interventions.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
