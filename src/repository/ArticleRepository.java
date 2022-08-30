package repository;

import data.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {

    private int articleId = 0;

    private List<Article> store  = new ArrayList<>();

    public int write(String title, String author, String body) {


        articleId += 1;
        Article article = new Article(articleId,title,body,author);
        store.add(article);

        return articleId;

    }

    public Article getById(int id) {
       for(Article article : store){

           if(article.getId() ==  id){
               return article;
           }
       }
       return null;
    }

    public void delete(Article article) {
        store.remove(article);
    }


}
