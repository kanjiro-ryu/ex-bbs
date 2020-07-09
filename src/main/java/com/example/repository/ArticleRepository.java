package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {

		Article article = new Article();

		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
	
		
		return article;
	};

	public List<Article> findAll() {

		List<Article> articleList = new ArrayList<>();

		String findAllSql = "SELECT *  FROM articles ORDER BY id DESC";

		articleList = template.query(findAllSql, ARTICLE_ROW_MAPPER);

		return articleList;
	}

	public void Insert(Article article) {
		
		String insSql = "INSERT INTO articles (name, content) VALUES (:name , :content) ";
	
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		template.update(insSql, param);

	}

	public void deleteById(int id) {
		
		String delSql = "DELETE FROM articles WHERE id = :id ";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(delSql, param);
		
	}

}
