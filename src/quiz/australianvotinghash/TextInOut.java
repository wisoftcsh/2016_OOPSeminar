package quiz.australianvotinghash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TextInOut {
    static ArrayList<String> information = null;
    File file = null;
    FileWriter fw = null;
    Scanner scanner = null;
    File fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\quiz\\australianvotinghash\\candidateOutput.txt");

    TextInOut() {
    }
    public void Input() {
        file = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\quiz\\australianvotinghash\\candidateInput.txt");
        String line;
        information = new ArrayList<>();
        try {
            scanner = new Scanner(new FileReader(file));
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                information.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류입니다.");
        } finally {
            scanner.close();
        }
    }
    public void ouput(String name){
        try {
            String c = "당선 : " + name + "\r\n";
            fw = new FileWriter(fileWrite, true);
            fw.write(c);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류입니다.");
        }
    }
    public void print() {
        System.out.println("===========================");
        Iterator<String> it = information.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("===========================");
        System.out.println();
    }
}
