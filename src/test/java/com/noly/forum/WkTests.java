package com.noly.forum;

import java.io.IOException;

public class WkTests {

    public static void main(String[] args) {
        String cmd = "D:/Apps_IDE/wkhtmltopdf/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com D:/data/wk-images/3.jpg";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("Ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
