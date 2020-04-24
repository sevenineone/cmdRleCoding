package main.java.rle;

import java.io.*;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class RleLaunch {
    @Option(name = "-z", usage = "Encode")
    private boolean encode;

    @Option(name = "-u", usage = "decode", forbids = {"-z"})
    private boolean decode;

    @Option(name = "-out", metaVar = "OutputName", usage = "Output file name")
    private String outName;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inName;

    public static void main(String[] args) {
        new RleLaunch().launch(args);
    }
    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar RleCoding.jar -z|-u -out OutputName InputName");
            parser.printUsage(System.err);
            return;
        }
        Rle rle = new Rle(encode);
        try {
            rle.code(inName, outName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
