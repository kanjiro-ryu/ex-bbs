package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.Join;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import com.example.repository.JoinRepository;
import com.example.service.JoinService;

@Controller
@RequestMapping("/art")
public class ArticleController {
	
	@Autowired
	private JoinService service;
	
	@Autowired
	private JoinRepository joinRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public CommentForm setUpFormC(){
		return new CommentForm();
	}
	
	@ModelAttribute
	public ArticleForm setUpFormA(){
		return new ArticleForm();
	}
	
	@RequestMapping("")
	public String Index(Model model) {
		
		
//		List<Article> articleList = new ArrayList<>();
//		articleList = articleRepository.findAll();
//		
//		List<Comment> commentList = new ArrayList<>();
//		
//		for( Article art : articleList) {
//			
//			commentList = commentRepository.findByArticleId(art.getId());
//			
//			art.setCommentList(commentList);
//			
//		}		
//		
//		model.addAttribute("art", articleList );
//		
		
		
		
//		model.addAttribute("join", articleList);
//		Article article = new Article();
		List<Article> articleList = new ArrayList<>();
		
		articleList = service.join();
		
		model.addAttribute("join", articleList);
		
		
		return "/article";
	} 
	
	
	@RequestMapping("showart")
	public String showArticle(ArticleForm formArt , Model model) {
		List<Article> articleList = new ArrayList<>();
		
		Article article = new Article();
		article.setName(formArt.getName());
		article.setContent(formArt.getContent());
	
		
		articleRepository.Insert(article);
		articleList = articleRepository.findAll();
		
		model.addAttribute( "artPush", articleList );
		
		return Index(model);
	} 
	
	@RequestMapping("showcomment")
	public String showComment(CommentForm formComment , Model model) {
		
		Comment comment = new Comment();
		comment.setName(formComment.getName());
		comment.setContent(formComment.getContent());
		comment.setArticleId(Integer.parseInt(formComment.getArticleId()));
			
		commentRepository.Insert(comment);
		
		return Index(model);
	}
	
	
	@RequestMapping("delete")
	public String delete( CommentForm com , Model model) {
		
		Article article = new Article();
		Comment comment = new Comment();
		
		article.setId(Integer.parseInt(com.getArticleId()));
		comment.setArticleId(Integer.parseInt(com.getArticleId()));
		
		commentRepository.deleteById(Integer.parseInt(com.getArticleId()));
	
		articleRepository.deleteById(Integer.parseInt(com.getArticleId()));
		
		
		return Index(model);
	}
	
	
	
	
}
