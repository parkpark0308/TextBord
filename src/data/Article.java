package data;

import java.time.LocalDateTime;

public class Article {

    private int id;

    private String title;
    private String body;

    private String author;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    public Article(int id , String title , String body , String author){

        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }
}
