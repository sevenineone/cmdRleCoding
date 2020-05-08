package main.java.rle;

import java.io.*;

public class Rle {
    private final Boolean isCode;

    public Rle(Boolean isCode) {
        this.isCode = isCode;
    }


    private void rleEncode(InputStreamReader reader, OutputStreamWriter writer) throws IOException {
        boolean check = true;
        int sym = 0;
        int lastSym = reader.read();
        while (sym != -1) {
            int num = 1;
            while (true) {
                sym = reader.read();
                if (sym == lastSym) num++;
                else break;
            }
            if (num == 1) {
                StringBuilder mid = new StringBuilder();
                mid.append(Character.toString(lastSym));
                mid.append(Character.toString(sym));
                lastSym = sym;
                for (int i = 0; i < 8; i++) {
                    sym = reader.read();
                    if (sym == lastSym) break;
                    num++;
                    mid.append(Character.toString(sym));
                    lastSym = sym;
                }
                writer.write(Integer.toString(-num));
                writer.write(mid.toString().substring(0, mid.length() - 1));
            } else {
                if (num > 9) num++;
                while (num > 9) {
                    num -= 9;
                    writer.write(Integer.toString(9));
                    writer.write(lastSym);
                }
                writer.write(Integer.toString(num));
                writer.write(lastSym);
            }
            lastSym = sym;
        }
    }

    private void rleDecode(InputStreamReader reader, OutputStreamWriter writer) throws IOException {
        int num;
        int sym = ' ';
        boolean check = false;
        while (sym != -1) {
            num = reader.read();
            if (num == '-') {
                num = reader.read();
                num -= '0';
                for (int i = 0; i < num; i++) {
                    sym = reader.read();
                    writer.write(sym);
                }
            } else {
                num -= 48;
                sym = reader.read();
                for (int i = 0; i < num; i++) {
                    writer.write(sym);
                }
            }
        }
    }

    public void code(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName);
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName);
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {

                if (this.isCode)
                    rleEncode(reader, writer);
                else rleDecode(reader, writer);
            }
        }
    }
}
