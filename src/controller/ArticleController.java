package controller;

import data.Article;
import infra.Container;
import infra.Request;
import service.ArticleService;
import utils.Util;

import java.util.Scanner;

public class ArticleController implements Controller{

    private Scanner sc;
    private ArticleService articleService;

    public ArticleController (){
        this.sc = Container.sc;
        this.articleService = Container.articleService;
    }
    @Override
    public void execute(Request request) {
        switch (request.getTarget()){
            case"write":
                write(request);
                break;
            case"detail":
                detail(request);
                break;
            default :
                System.out.println("존재하지 않는 요청입니다");
                break;
        }
    }
    public void write(Request request){


        System.out.println("== 게시글 작성 ==");


        System.out.println("제목 :");
        String title = sc.nextLine().trim();

        if(title.length() == 0){
            System.out.println("제목을 입력하세요");
            return;
        }

        System.out.println("내용 :");
        String body = sc.nextLine().trim();


        System.out.println("작성자 :");
        String author = request.getLogonMemberId();

        int articled = articleService.write(title,author,body);

        System.out.println(articled + "번 게시글이 작성 되었습니다");


    }

    public void detail(Request request){

        String paramKey = "id";
        if(!Util.hasParam(request , paramKey)){
            System.out.println(paramKey + "파라미터가 필요 합니다");
            return;

        }

        int articleId = request.getParameterIntValue(paramKey);

        Article findArticle = articleService.getById(articleId);

        if(findArticle == null){
            System.out.println("해당 게시글이 존재 하진 않습니다");
            return;


        }
        System.out.println("=="+ findArticle.getId() + "번 게시글 ==");
        System.out.println("작성자 : " +findArticle.getAuthor() );
        System.out.println("제목 : " +findArticle.getTitle() );
        System.out.println("내용 : " +findArticle.getBody() );
        System.out.println("작성일 : " +findArticle.getRegDate() );
    }

}
