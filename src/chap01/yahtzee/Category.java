package chap01.yahtzee;

import java.util.*;
import java.io.*;

public class Category {
    String out = "";
    ArrayList<Integer> array = new ArrayList<>();
    ArrayList<Integer> outArray = new ArrayList<>();
    int[] counter = new int[13];
    ArrayList<Integer> resultArray = new ArrayList<>();
    int count = 0;
    int result;
    int min = 0;
    int max = 0;
    int minCount = 0;
    int maxCount = 0;
    FileWriter fw = null;
    File fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\yahtzee\\yahtzeeOutput.txt");

    Category() {
        arrayInit();
    }

    public void playing(String[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            if(arrays[i].isEmpty()){
                continue;
            }
            array.add(Integer.parseInt(arrays[i]));
        }
        System.out.println(array);
        algorithm();
        array.clear();
    }
    public void outPut(String string) {
        try {
            String c = string + " ";
            fw = new FileWriter(fileWrite, true);
            fw.write(c);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류입니다.");
        }

    }
    public void outArrayPrint(){
        fileWrite.delete();
        fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\yahtzee\\yahtzeeOutput.txt");
        System.out.println("-------------------------------------------------");
        System.out.println(outArray);
        System.out.println("-------------------------------------------------");
        for(int i=0;i<outArray.size();i++){
            out = out + outArray.get(i) +" ";
        }
        out = out + "\r\n";
        outPut(out);
    }
    public void countReset() {
        minCount = 0;
        maxCount = 0;
    }
    public void outArrayClear(){
        for (int i = 0; i < counter.length; i++) {
            outArray.set(i, 0);
        }
    }
    public void resultArrayClear() {
        for (int i = 0; i < counter.length; i++) {
            resultArray.set(i, 0);
        }
    }
    public void counterInit(){
        for (int i = 0; i < counter.length; i++) {
            counter[i] = 1;
        }
    }
    public void arrayInit() {
        for (int i = 0; i < counter.length; i++) {
            resultArray.add(0);
        }
        for (int i = 0; i < counter.length+2; i++) {
            outArray.add(0);
        }
        for (int i = 0; i < counter.length; i++) {
            counter[i] = 1;
        }
    }

    public void algorithm() {
        if (counter[0] == 1) {
            first();
        }
        if (counter[1] == 1) {
            second();
        }
        if (counter[2] == 1) {
            third();
        }
        if (counter[3] == 1) {
            fourth();
        }
        if (counter[4] == 1) {
            fifth();
        }
        if (counter[5] == 1) {
            sixth();
        }
        if (counter[6] == 1) {
            seventh();
        }
        if (counter[7] == 1) {
            eighth();
        }
        if (counter[8] == 1) {
            ninth();
        }
        if (counter[9] == 1) {
            tenth();
        }
        if (counter[10] == 1) {
            eleventh();
        }
        if (counter[11] == 1) {
            twelfth();
        }
        if (counter[12] == 1) {
            thirteenth();
        }

        int max = Collections.max(resultArray);
        if(max !=0){
            int maxIndex = resultArray.indexOf(max);
            outArray.set(maxIndex, max);
            counter[maxIndex] = 0;
        }
        System.out.println(resultArray);
        for(int i=0; i<counter.length;i++){
            System.out.print(counter[i] + " ");
        }
        System.out.println();

        resultArrayClear();
    }

    public void first() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == 1) {
                count++;
            }
        }
        result = count;
        count = 0;
        resultArray.set(0, result);
    }

    public void second() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == 2) {
                count++;
            }
        }
        result = count * 2;
        count = 0;
        resultArray.set(1, result);
    }

    public void third() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == 3) {
                count++;
            }
        }
        result = count * 3;
        count = 0;
        resultArray.set(2, result);
    }

    public void fourth() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == 4) {
                count++;
            }
        }
        result = count * 4;
        count = 0;
        resultArray.set(3, result);
    }

    public void fifth() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == 5) {
                count++;
            }
        }
        result = count * 5;
        count = 0;
        resultArray.set(4, result);
    }

    public void sixth() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == 6) {
                count++;
            }
        }
        result = count * 6;
        count = 0;
        resultArray.set(5, result);
    }

    public void seventh() {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        result = sum;
        count = 0;
        resultArray.set(6, result);
    }

    public void eighth() {
        min = Collections.min(array);
        max = Collections.max(array);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == min) {
                minCount++;
            } else if (array.get(i) == max) {
                maxCount++;
            }
        }
        if (minCount >= 3 || maxCount >= 3) {
            int sum = 0;
            for (int i = 0; i < array.size(); i++) {
                sum += array.get(i);
            }
            result = sum;
            countReset();
            resultArray.set(7, result);
        } else {
            result = 0;
            countReset();
            resultArray.set(7, result);
        }
    }

    public void ninth() {
        min = Collections.min(array);
        max = Collections.max(array);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == min) {
                minCount++;
            } else if (array.get(i) == max) {
                maxCount++;
            }
        }
        if (minCount >= 4 || maxCount >= 4) {
            int sum = 0;
            for (int i = 0; i < array.size(); i++) {
                sum += array.get(i);
            }
            result = sum;
            countReset();
            resultArray.set(8, result);
        } else {
            result = 0;
            countReset();
            resultArray.set(8, result);
        }
    }

    public void tenth() {
        min = Collections.min(array);
        max = Collections.max(array);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == min) {
                minCount++;
            } else if (array.get(i) == max) {
                maxCount++;
            }
        }
        if (minCount >= 5 || maxCount >= 5) {
            result = 50;
            countReset();
            resultArray.set(9, result);
        } else {
            result = 0;
            countReset();
            resultArray.set(9, result);
        }
    }

    public void eleventh() {
        if (array.get(0) + 1 == array.get(1) && array.get(1) + 1 == array.get(2) && array.get(2) + 1 == array.get(3)) {
            result = 25;
            resultArray.set(10, result);
        } else if (array.get(1) + 1 == array.get(2) && array.get(2) + 1 == array.get(3) && array.get(3) + 1 == array.get(4)) {
            result = 25;
            resultArray.set(10, result);
        } else {
            result = 0;
            resultArray.set(10, result);
        }
    }

    public void twelfth() {
        if (array.get(0) + 1 == array.get(1) && array.get(1) + 1 == array.get(2) && array.get(2) + 1 == array.get(3) && array.get(3) + 1 == array.get(4)) {
            result = 35;
            resultArray.set(11, result);
        } else {
            result = 0;
            resultArray.set(11, result);
        }
    }

    public void thirteenth() {
        min = Collections.min(array);
        max = Collections.max(array);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == min) {
                minCount++;
            } else if (array.get(i) == max) {
                maxCount++;
            }
        }
        if (minCount >= 3 && maxCount >= 2) {
            result = 40;
            countReset();
            resultArray.set(12, result);
        } else if (minCount >= 2 && maxCount >= 3) {
            result = 40;
            countReset();
            resultArray.set(12, result);
        } else {
            result = 0;
            countReset();
            resultArray.set(12, result);
        }
    }

    public void bonus() {
        int sum=0;
        for(int i=0;i<6;i++){
            sum += outArray.get(i);
        }
        if(sum>=63){
            result = sum;
            outArray.set(13,result);
        }else{
            result = 0;
            outArray.set(13,result);
        }
    }

    public void score() {
        int scorePoint =0;
        for(int i = 0; i<13;i++){
            scorePoint += outArray.get(i);
        }
        result = scorePoint;
        outArray.set(14,result);
    }
}