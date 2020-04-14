package main.java.rle;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class RleLaunch {
    @Option(name = "-z", usage = "Encode")
    private boolean charInd;

    @Option(name = "-u", usage = "decode", forbids = {"-z"})
    private boolean worldInd;

    @Option(name = "-out", metaVar = "OutputName", usage = "Output file name")
    private String outName;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inName;

    public static void main(String[] args) {
        //TODO
    }
    //launch TODO


}
