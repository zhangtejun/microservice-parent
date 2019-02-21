package test.java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class A {
    public static String processFile(BufferedReaderProcessor p) throws
            IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\新建文本文档 (2).txt"))) {
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
/*        String oneLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(oneLine);
        String oneLine1 = processFile(b -> b.readLine()+ b.readLine());
        System.out.println(oneLine1);*/
          int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
       // portNumber = 1;
    }
}
