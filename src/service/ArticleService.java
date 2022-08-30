package service;

import data.Article;
import infra.Container;
import repository.ArticleRepository;

import java.util.List;

public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(){
        this.articleRepository = Container.articleRepository;
    }

    public int write(String title, String author, String body) {
        return articleRepository.write(title,author,body);
    }

    public Article getById(int id) {
        return  articleRepository.getById(id);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }


    public List<Article> getArticles() {
        return articleRepository.getArticles();

    }
}
