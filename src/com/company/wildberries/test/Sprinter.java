package com.company.wildberries.test;

import java.io.FileWriter;
import java.io.IOException;

public class Sprinter extends Thread {

    private String filePath;

    private static FileWriter writer;
    private static volatile Boolean isFinished = false;

    public Sprinter(String threadName, String filePath) {
        super(threadName);
        this.filePath = filePath;
    }

    public static FileWriter getWriter() {
        return writer;
    }

    @Override
    public void run() {

        try {
            writer = new FileWriter(filePath);

            for (int i = 1; i <= 100; i++) {
                String str = getName() + ": \t score = " + i + "\n";

                synchronized (writer) {
                    writer.write(str);
                    writer.flush();
                }
            }

            synchronized (isFinished) {
                if (isFinished == false) {
                    synchronized (writer) {
                        writer.write(getName() + " выиграл \n");
                        writer.flush();

                    }

                    isFinished = true;

                } else {
                    synchronized (writer) {
                        writer.write(getName() + " проиграл \n");
                        writer.flush();
                    }
                }
            }

        } catch (IOException e) { e.printStackTrace(); }
    }
}
