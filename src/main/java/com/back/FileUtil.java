package com.back;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void saveQuoteToFile(Quote quote) {

        File dir = new File("db/wiseSaying");
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File("db/wiseSaying/" + quote.id + ".json");

        String json = "{\n" +
                "  \"id\": " + quote.id + ",\n" +
                "  \"content\": \"" + quote.content + "\",\n" +
                "  \"author\": \"" + quote.author + "\"\n" +
                "}";

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(json);
            pw.close();
        } catch (IOException e) {
            System.out.println("파일 저장 실패: " + e.getMessage());
        }
    }

    public static void saveLastIdToFile(int id) {

        File file = new File("db/wiseSaying/lastId.txt");

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println(id);
            pw.close();
        } catch (IOException e) {
            System.out.println("lastId 저장 실패: " + e.getMessage());
        }
    }

    public static int loadLastIdFromFile() {

        File file = new File("db/wiseSaying/lastId.txt");

        if (!file.exists()) {
            return 1;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(br.readLine().trim());
        } catch (Exception e) {
            System.out.println("lastId.txt 읽기 실패 : " + e.getMessage());
            return  1;
        }
    }

    public static void buildDataJson() {
        File dir = new File("db/wiseSaying");
        File[] files = dir.listFiles();

        if (files == null) return;

        List<String> jsonList = new ArrayList<>();

        for (File file : files) {
            String fileName = file.getName();
            if (fileName.equals("lastId.txt") || fileName.equals("data.json")) continue;

            try {
                String json = Files.readString(file.toPath()).trim();
                jsonList.add(json);
            } catch (IOException e) {
                System.out.println("파일 읽기 실패: " + file.getName());
            }
        }

        String result = "[\n" + String.join(",\n", jsonList) + "\n]";

        File output = new File("db/wiseSaying/data.json");
        try (PrintWriter pw = new PrintWriter(output)) {
            pw.write(result);
            System.out.println("✅ data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            System.out.println("data.json 저장 실패: " + e.getMessage());
        }
    }

}
