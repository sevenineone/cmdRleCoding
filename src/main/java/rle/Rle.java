package main.java.rle;

import java.io.*;

public class Rle {
    private final Boolean isCode;

    public Rle(Boolean isCode) {
        this.isCode = isCode;
    }

    public static void rleCode(InputStream in, OutputStream out) {
        //TODO
    }


    public void rleCode(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                rleCode(inputStream, outputStream);
            }
        }
    }
}
