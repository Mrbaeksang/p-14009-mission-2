package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Quote> quotes = new ArrayList<>();
        int id = 1;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();

            switch (command) {
                case "등록":
                    System.out.print("명언을 입력하세요: ");
                    String content = sc.nextLine();

                    System.out.print("작가를 입력하세요: ");
                    String author = sc.nextLine();

                    quotes.add(new Quote(id, content, author));

                    System.out.println("명언 : " + content);
                    System.out.println("작가 : " + author);
                    System.out.println(id + "번 명언이 등록되었습니다.");
                    id++;
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("--------------------");
                    for (Quote quote : quotes) {
                        System.out.println(quote.id + " / " + quote.author + " / " + quote.content);
                    }
                    break;

                case "삭제":
                    System.out.println("삭제할 번호를 입력하세요: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;
                    for (int i = 0; i < quotes.size(); i++) {
                        if (quotes.get(i).id == deleteId) {
                            quotes.remove(i);
                            System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                    }
                    break;

                case "수정":
                    System.out.println("수정할 번호를 입력하세요: ");
                    int modifyId = sc.nextInt();
                    sc.nextLine();

                    boolean modified = false;
                    for (Quote quote : quotes) {
                        if(quote.id == modifyId) {
                            System.out.println("기존 명언: " + quote.content);
                            System.out.println("새 명언을 입력하세요: ");
                            quote.content = sc.nextLine();

                            System.out.println("기존 작가: " + quote.author);
                            System.out.println("새 작가를 입력하세요: ");
                            quote.author = sc.nextLine();

                            System.out.println(modifyId + "번 째 명언이 수정되었습니다");
                            System.out.println("번호" + quote.id + "\n명언: " + quote.content + "\n작가: " +quote.author);

                            modified = true;
                            break;
                        }
                    }

                    if(!modified){
                        System.out.println(modifyId +"는 존재하지 않는 번호입니다.");
                    }
                    break;

                case "종료":
                    System.out.println("프로그램을 종료합니다.");
                    return;

                default:
                    System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }
}

