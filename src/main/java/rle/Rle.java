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
                        while (num > 9) {
                            num -= 9;
                            writer.write(Integer.toString(9));
                            writer.write(lastSym);
                        }
                        writer.write(Integer.toString(num));
                    }
                    writer.write(lastSym);
                    lastSym = sym;
                }
            }
        }
    }

    public void rleDecode(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                int num;
                int sym = reader.read();
                boolean check = false;
                while (sym != -1) {
                    num = sym;
                    if (num == '-') {
                        sym = reader.read();
                        sym = reader.read();
                        while (sym > 57 || sym < 49) {
                            writer.write(sym);
                            sym = reader.read();
                        }
                    } else {
                        num -= 48;
                        sym = reader.read();
                        for (int i = 0; i < num; i++) {
                            writer.write(sym);
                        }
                        sym = reader.read();
                    }

                }
            }
        }
    }

    public void code(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                if (this.isCode)
                    rleEncode(inputStream, outputStream);
                else rleDecode(inputStream, outputStream);
            }
        }
    }
}
