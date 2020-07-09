package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.Join;
import com.example.repository.JoinRepository;

@Service
@Transactional
public class JoinService {

	@Autowired
	private JoinRepository joinRepository;

	public List<Article> join() {

		Article article = new Article();
		List<Article> articleList = new ArrayList<>();
		Comment comment = new Comment();
		List<Comment> commentList = new ArrayList<>();
		
		List<Article> articleList2 = new ArrayList<>();// 新しく詰め直す用

		articleList = joinRepository.findAll();
		System.out.println(articleList.size());
		System.out.println(articleList);

		for (int i = 0; i < articleList.size(); i++) {

//			if( i == articleList.size()-1 ) {
//				if(articleList.get(i).getId() != articleList.get(i - 1).getId()) {
//					article.setCommentList(commentList);
//					articleList2.add(article);
//					
//					 article = new Article();
//						article.setId(articleList.get(i).getId());
//						article.setName(articleList.get(i).getName());
//						article.setContent(articleList.get(i).getContent());
//
//						 commentList = new ArrayList<>();
//						commentList.addAll(articleList.get(i).getCommentList());
//						article.setCommentList(commentList);
//				} else {
//					commentList.addAll(articleList.get(i).getCommentList());
//					article.setCommentList(commentList);
//				}
//				
//				articleList2.add(article);
//			}
			
			if (i == 0 ) {
				article.setId(articleList.get(i).getId());
				article.setName(articleList.get(i).getName());
				article.setContent(articleList.get(i).getContent());

				commentList.addAll(articleList.get(i).getCommentList());
			
			} else if (articleList.get(i).getId() != articleList.get(i - 1).getId()) {
				
				article.setCommentList(commentList);
				articleList2.add(article);

				 article = new Article();//初期化
				article.setId(articleList.get(i).getId());
				article.setName(articleList.get(i).getName());
				article.setContent(articleList.get(i).getContent());

				 commentList = new ArrayList<>();//初期化
				commentList.addAll(articleList.get(i).getCommentList());
				article.setCommentList(commentList);				

			}else {
				commentList.addAll(articleList.get(i).getCommentList());
				article.setCommentList(commentList);
			}
			
			if( i == articleList.size()-1 ) {
				article.setCommentList(commentList);
				articleList2.add(article);
			}
			
		}

		return articleList2;
	}

}
