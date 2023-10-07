package com.ardetrick;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("Compiled by Java version: " + CompiledWith.version());
        System.out.println("Running on Java version: " + Runtime.version());
    }

}

class CompiledWith {

    private static final int FIRST_FOUR_BYTES_OF_JAVA_FILE_AS_INT = 0xcafebabe;

    static String version() {
        try (var resourceAsStream =
                     new DataInputStream(Objects.requireNonNull(Main.class.getResourceAsStream("Main.class")))
        ) {
            int fileMagicString = resourceAsStream.readInt();
            if (FIRST_FOUR_BYTES_OF_JAVA_FILE_AS_INT != fileMagicString) {
                System.out.println("Not a Java class");
            }
            resourceAsStream.readUnsignedShort();
            return byteToMajorVersion(resourceAsStream.readUnsignedShort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="https://docs.oracle.com/javase/specs/jvms/se20/html/jvms-4.html#jvms-4.1-200-B.2" /a>
     */
    private static String byteToMajorVersion(int readUnsignedShort) {
        return switch (readUnsignedShort) {
            case 45 -> "1.1 or 1.0.2";
            case 46 -> "1.2";
            case 47 -> "1.3";
            case 48 -> "1.4";
            case 49 -> "5.0";
            case 50 -> "6";
            case 51 -> "7";
            case 52 -> "8";
            case 53 -> "9";
            case 54 -> "10";
            case 55 -> "11";
            case 56 -> "12";
            case 57 -> "13";
            case 58 -> "14";
            case 59 -> "15";
            case 60 -> "16";
            case 61 -> "17";
            case 62 -> "18";
            case 63 -> "19";
            case 64 -> "20";
            case 65 -> "21";
            // Assumed
            case 66 -> "21";
            default -> "unknown";
        };
    }

}
