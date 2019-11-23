package com.company.wildberries.test;

import java.io.FileWriter;
import java.io.IOException;

public class Sprinter extends Thread {

    private String filePath;

    private static FileWriter writer;
    private static volatile boolean isFinished = false;

    public Sprinter(String threadName, String filePath) {
        super(threadName);
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            writer = new FileWriter(filePath);
        } catch (IOException e) { e.printStackTrace(); }

        for (int i = 1; i <= 100; i++) {
            String str = getName() + ": \t score = " + i + "\n";

            synchronized (writer) {
                try {
                    writer.write(str);
                    writer.flush();
                } catch (IOException e) { e.printStackTrace(); }
            }
        }

        if (isFinished == false) {
            synchronized (writer) {
                try {
                    writer.write(getName() + " выиграл \n");
                    writer.flush();
                } catch (IOException e) { e.printStackTrace(); }
            }

            isFinished = true;

        } else {
            synchronized (writer) {
                try {
                    writer.write(getName() + " проиграл \n");
                    writer.flush();
                } catch (IOException e) { e.printStackTrace(); }
            }
        }

    }
}
