package quiz.yahtzee;

import java.io.*;
import java.util.*;

public class Yahtzee {
    Category category = new Category();
    String line;
    ArrayList<String> yahtzeeArray = new ArrayList<>();
    File file = null;

    Yahtzee() {
        file = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\quiz\\yahtzee\\yahtzeeInput.txt");
        Scanner fs = null;
        try {
            fs = new Scanner(new FileReader(file));
            while (fs.hasNext()) {
                line = fs.nextLine();
                if(line.isEmpty()){
                    break;
                }
                yahtzeeArray.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류입니다.");
        } finally {
            fs.close();
        }
    }

    public void print() {
        System.out.println("==============================");
        Iterator<String> it = yahtzeeArray.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("==============================");
        System.out.println();
    }

    public void start() {
        int loop = 0;
        while (true) {
            for(int i=0; i<yahtzeeArray.size(); i++){
                if (loop == yahtzeeArray.size()) {
                    break;
                }
                if(i%13==0){
                    if(i != 0){
                        category.selectScore();
                        System.out.println(category.scoreArray);
                        category.scoreArrayPrint();
                    }
                    System.out.println("게임 시작");
                    category.totalArrayClear();
                    category.outArrayClear();
                    category.counterInit();
                }
                String[] temp = yahtzeeArray.get(i).split(" ");
                category.playing(temp);
                loop++;
//                category.bonus();
//                category.score();
            }
//            category.outArrayPrint();
            category.selectScore();
            System.out.println(category.scoreArray);
            category.scoreArrayPrint();
            break;
        }

    }

}
