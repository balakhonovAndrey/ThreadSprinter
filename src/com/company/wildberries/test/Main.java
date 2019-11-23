package com.company.wildberries.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите путь к файлу: ");
        String filePath = reader.readLine();

        reader.close();

        Sprinter sprinter1 = new Sprinter("Поток А", filePath);
        Sprinter sprinter2 = new Sprinter("Поток Б", filePath);

        sprinter1.start();
        sprinter2.start();
    }
}
