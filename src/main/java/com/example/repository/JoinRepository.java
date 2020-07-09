package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.Join;

@Repository
public class JoinRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private final static RowMapper<Article> JOIN_ROW_MAPPER = (rs, i) -> {
		
		List<Comment> commentList = new ArrayList<>();
		
		Article article = new  Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		Comment comment = new Comment();

		comment.setId(rs.getInt("com_id"));
		comment.setName(rs.getString("com_name"));
		comment.setContent(rs.getString("com_content"));
		comment.setArticleId(rs.getInt("article_id"));
		
		commentList.add(comment);
		
		article.setCommentList(commentList);
		
		return article;
		
		
//		Join join = new Join();
//		
//		join.setId(rs.getInt("id"));
//		join.setName(rs.getString("name"));
//		join.setContent(rs.getString("content"));
//		join.setComId(rs.getInt("com_id"));
//		join.setComName(rs.getString("com_name"));
//		join.setComContent(rs.getString("com_content"));
//		join.setArticleId(rs.getInt("article_id"));
//		

	};
	
	public List<Article> findAll() {

		List<Article> joinList = new ArrayList<>();
//SQL文はORDER BYつけよう
		String findAllSql = "SELECT articles.id AS id , articles.name AS name,"
				+ "articles.content AS content, comments.id AS com_id,"
				+ "comments.name AS com_name, comments.content AS com_content,"
				+ "comments.article_id FROM articles LEFT OUTER JOIN comments ON articles.id = comments.article_id "
				+ " ORDER BY articles.id DESC , comments.id DESC ";

		joinList = template.query(findAllSql, JOIN_ROW_MAPPER);

		return joinList;
	}
	
	
}
