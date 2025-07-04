package ir.ac.kntu.main.help;

import java.util.Scanner;

public class ScannerWrapper {

    private static final ScannerWrapper instance = new ScannerWrapper();
    private final Scanner scanner;

    private ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public static ScannerWrapper getInstance() {
        return instance;
    }

    public String next() {
        return scanner.next();
    }

    public Double nextDouble() {
        return scanner.nextDouble();
    }

    public Integer nextInt() {
        return scanner.nextInt();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}