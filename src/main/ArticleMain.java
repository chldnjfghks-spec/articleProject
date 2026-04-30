package main;

import dao.ArticleDAO;
import db.DBConn;
import service.ArticleService;
import service.CommentService;
import view.ArticleView;

import java.sql.Connection;
import java.util.Scanner;

public class ArticleMain {
    public static void main(String[] args) {

        Connection conn = DBConn.getConnection();
        Scanner sc = new Scanner(System.in);



        ArticleDAO dao = new ArticleDAO(conn);


        ArticleService articleService = new ArticleService(dao);
        CommentService commentService = new CommentService(dao);


        ArticleView articleView = new ArticleView(sc, articleService, commentService);

        int input;


        while (true) {

            do {
                System.out.println("0.전체보기 1.새글 2.자세히보기 3.게시글삭제 4.수정 5.종료");
                input = sc.nextInt();
                sc.nextLine();
            } while (input < 0 || input > 5);

            switch (input) {
                case 0:
                    articleView.showAll();
                    break;
                case 1:  
                    articleView.showNewArticle();
                    break;
                case 2:
                    articleView.showDetail();
                    break;
                case 3:
                    articleView.showDelete();
                    break;
                case 4:
                    articleView.showUpdate();
                    break;
                case 5:
                    System.out.println("프로그램 종료");
                    try{
                        conn.close();
                        sc.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return;
            }
        }
    }
}