package view;

import dto.ArticleDto;
import dto.CommentDto;
import entity.Article;
import service.ArticleService;
import service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ArticleView {



        private Scanner sc = new Scanner(System.in);
        private ArticleService articleService = new ArticleService();
        private CommentService commentService = new CommentService();

        private List<ArticleDto> articles;

        public void showAll() {

            articles = articleService.all();

            if (articles.isEmpty()) {
                System.out.println("게시글이 없습니다.");
                return;
            }

            System.out.println("=============================================");
            System.out.println("id\tname\t\ttitle\t\t작성일");
            System.out.println("=============================================");

            for (ArticleDto a : articles) {
                System.out.println(
                        a.getId() + "\t" +
                                a.getName() + "\t\t" +
                                a.getTitle() + "\t\t" +
                                a.getInsertedDate()
                );

                for (CommentDto c : a.getCommentList()) {
                    System.out.println(c);
                }
            }

            System.out.println("=============================================");
        }

        public void showNewArticle() {

            sc.nextLine();

            System.out.print("작성자: ");
            String name = sc.nextLine();

            System.out.print("제목: ");
            String title = sc.nextLine();

            System.out.print("내용: ");
            String content = sc.nextLine();

            ArticleDto dto = ArticleDto.makeArticleDto(
                    null, name, title, content, LocalDateTime.now()
            );

            articleService.newArticle(dto);

            System.out.println("등록 완료");
        }

        public void showDetail() {

            System.out.print("조회할 ID: ");
            Long id = sc.nextLong();

            Article articleEntity = articleService.detail(id);

            if (articleEntity == null) {
                System.out.println("게시글 없음");
                return;
            }

            ArticleDto article = ArticleDto.fromArticle(articleEntity);

            System.out.println("🚀 ID      : " + article.getId());
            System.out.println("🚀 Name    : " + article.getName());
            System.out.println("🚀 Title   : " + article.getTitle());
            System.out.println("🚀 Content : " + article.getContent());
            System.out.println("🚀 작성일  : " + article.getInsertedDate());
            System.out.println("🚀 수정일  : " + article.getUpdatedDate());

            while (true) {

                System.out.println("\n🎶🎶  댓글 리스트  🎶🎶");
                for (CommentDto c : article.getCommentList()) {
                    System.out.println(c);
                }

                System.out.println("\n1.댓글입력  2.댓글수정  3.댓글삭제  4.돌아가기");
                System.out.print("> ");

                int menu = sc.nextInt();

                if (menu == 1) {
                    sc.nextLine();

                    System.out.print("이름: ");
                    String name = sc.nextLine();

                    System.out.print("내용: ");
                    String content = sc.nextLine();

                    CommentDto comment = new CommentDto(
                            null,
                            article.getId(),
                            name,
                            content
                    );

                    commentService.commentAdd(comment);

                } else if (menu == 2) {
                    System.out.print("댓글 ID: ");
                    Long cid = sc.nextLong();

                    sc.nextLine();
                    System.out.print("수정 내용: ");
                    String content = sc.nextLine();

                    CommentDto comment = new CommentDto(
                            cid,
                            article.getId(),
                            "",
                            content
                    );

                    commentService.commentUpdate(comment);

                } else if (menu == 3) {
                    System.out.print("삭제할 댓글 ID: ");
                    Long cid = sc.nextLong();

                    commentService.commentDelete(cid);

                } else {
                    break;
                }

                article = ArticleDto.fromArticle(articleService.detail(id));
            }
        }

        public void showDelete() {

            System.out.print("삭제할 ID: ");
            Long id = sc.nextLong();

            boolean result = articleService.delete(id);

            if (result) {
                System.out.println("삭제됐습니다.");
            } else {
                System.out.println("실패했습니다.");
            }
        }

        public void showUpdate() {

            System.out.print("수정할 ID: ");
            Long id = sc.nextLong();

            Article articleEntity = articleService.detail(id);

            if (articleEntity == null) {
                System.out.println("게시글 없음");
                return;
            }

            ArticleDto article = ArticleDto.fromArticle(articleEntity);

            sc.nextLine();

            System.out.println("현재 제목: " + article.getTitle());
            System.out.println("현재 내용: " + article.getContent());

            System.out.print("새 제목: ");
            String title = sc.nextLine();

            System.out.print("새 내용: ");
            String content = sc.nextLine();

            ArticleDto updated = new ArticleDto(
                    article.getId(),
                    article.getName(),
                    title,
                    content,
                    article.getInsertedDate(),
                    LocalDateTime.now(),
                    null
            );

            articleService.update(updated);

            System.out.println("수정 완료");
        }
    }

