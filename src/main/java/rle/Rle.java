package main.java.rle;

import java.io.*;

public class Rle {
    private final Boolean isCode;

    public Rle(Boolean isCode) {
        this.isCode = isCode;
    }


    public void rleEncode(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                boolean check = true;
                int sym = 0;
                int lastSym = reader.read();
                while (sym != -1) {
                    int num = 1;
                    //lastSym
                    while (true) {
                        sym = reader.read();
                        if (sym == lastSym) num++;
                        else break;
                        lastSym = sym;
                    }

                    if (num == 1) {
                        if (check) {
                            writer.write('-');
                            writer.write('1');
                            check = false;
                        }
                    } else {
                        check = true;
                        writer.write(Integer.toString(num));
                    }
                    writer.write(lastSym);
                    lastSym = sym;
                }
            }
        }
    }

    public static void rleDecode(InputStream in, OutputStream out) throws IOException {
//TODO
    }


    public void code(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                if (isCode)
                    rleEncode(inputStream, outputStream);
                else rleDecode(inputStream, outputStream);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Rle rle = new Rle(true);
        rle.code("C:\\Users\\alexe\\IdeaProjects\\RleCoding\\src\\main\\java\\rle\\input test", "output.txt");
    }
}
