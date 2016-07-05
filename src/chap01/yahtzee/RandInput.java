package chap01.yahtzee;

import java.io.*;

public class RandInput {
    int game = 0;
    String randString = "";
    FileWriter fwr = null;
    File fileWriterand = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\yahtzee\\yahtzeeInput.txt");
    RandInput(){
        removeFile();
        randString();
    }
    public void removeFile(){
        fileWriterand.delete();
        fileWriterand = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\yahtzee\\yahtzeeInput.txt");
    }
    public void RandomIn(String string) {
        try {
            fwr = new FileWriter(fileWriterand, true);
            fwr.write(string);
            fwr.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류입니다.");
        }
    }

    public void randString() {
        while(true) {
            if(game==3){
                return;
            }
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 5; j++) {
                    int rand = (int) ((Math.random() * 6 +1));
                    randString = randString + rand + " ";
                }
                randString = randString + "\r\n";
                RandomIn(randString);
                randString ="";
            }
            game++;
        }
    }
}
