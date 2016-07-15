package quiz.yahtzee;

import java.util.*;
import java.io.*;

public class Category {
    String out = "";
    ArrayList<Integer> array = new ArrayList<>();
    ArrayList<Integer> outArray = new ArrayList<>();
    ArrayList<ArrayList> totalArray = new ArrayList<>();
    ArrayList<Integer> scoreArray = new ArrayList<>();
    ArrayList<Integer> tmpArray = null;
    int[] counter = new int[13];
    ArrayList<Integer> resultArray = new ArrayList<>();
    int count = 0;
    int result;
    int min = 0;
    int max = 0;
    int minCount = 0;
    int maxCount = 0;
    FileWriter fw = null;
    File fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\quiz\\yahtzee\\yahtzeeOutput.txt");

    Category() {
        arrayInit();
    }

    public void playing(String[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].isEmpty()) {
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

    public void scoreArrayPrint() {
        fileWrite.delete();
        fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\quiz\\yahtzee\\yahtzeeOutput.txt");
        System.out.println("-------------------------------------------------");
        System.out.println(scoreArray);
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < scoreArray.size(); i++) {
            out = out + scoreArray.get(i) + " ";
        }
        out = out + "\r\n";
        outPut(out);
    }

    public void countReset() {
        minCount = 0;
        maxCount = 0;
    }

    public void totalArrayClear() {
        totalArray.clear();
    }

    public void outArrayClear() {
        for (int i = 0; i < counter.length; i++) {
            outArray.set(i, 0);
        }
    }

    public void resultArrayClear() {
        for (int i = 0; i < counter.length; i++) {
            resultArray.set(i, 0);
        }
    }

    public void counterInit() {
        for (int i = 0; i < counter.length; i++) {
            counter[i] = 1;
        }
    }

    public void arrayInit() {
        for (int i = 0; i < counter.length; i++) {
            resultArray.add(0);
        }
        for (int i = 0; i < counter.length + 2; i++) {
            outArray.add(0);
        }
        for (int i = 0; i < counter.length + 2; i++) {
            scoreArray.add(0);
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

//        int max = Collections.max(resultArray);
//        if(max !=0){
//            int maxIndex = resultArray.indexOf(max);
//            outArray.set(maxIndex, max);
//            counter[maxIndex] = 0;
//        }
//        System.out.println(resultArray);
//        for(int i=0; i<counter.length;i++){
//            System.out.print(counter[i] + " ");
//        }
//        System.out.println();

        Iterator<Integer> it = resultArray.iterator();
        tmpArray = new ArrayList<>();
        while (it.hasNext()) {
            tmpArray.add(it.next());
        }
        totalArray.add(tmpArray);
        resultArrayClear();
    }

    public void selectScore() {
        for (int a = 0; a < totalArray.size(); a++) {
            outArray.set(0, (int) totalArray.get(0).get(a));
            for (int b = 0; b < totalArray.size(); b++) {
                if (b == a) {
                    continue;
                }
                outArray.set(1, (int) totalArray.get(1).get(b));
                for (int c = 0; c < totalArray.size(); c++) {
                    if (c == a || c == b) {
                        continue;
                    }
                    outArray.set(2, (int) totalArray.get(2).get(c));
                    for (int d = 0; d < totalArray.size(); d++) {
                        if (d == a || d == b || d == c) {
                            continue;
                        }
                        outArray.set(3, (int) totalArray.get(3).get(d));
                        for (int e = 0; e < totalArray.size(); e++) {
                            if (e == a || e == b || e == c || e == d) {
                                continue;
                            }
                            outArray.set(4, (int) totalArray.get(4).get(e));
                            for (int f = 0; f < totalArray.size(); f++) {
                                if (f == a || f == b || f == c || f == d || f == e) {
                                    continue;
                                }
                                outArray.set(5, (int) totalArray.get(5).get(f));
                                for (int g = 0; g < totalArray.size(); g++) {
                                    if (g == a || g == b || g == c || g == d || g == e || g == f) {
                                        continue;
                                    }
                                    outArray.set(6, (int) totalArray.get(6).get(g));
                                    for (int h = 0; h < totalArray.size(); h++) {
                                        if (h == a || h == b || h == c || h == d || h == e || h == f || h == g) {
                                            continue;
                                        }
                                        outArray.set(7, (int) totalArray.get(7).get(h));
                                        for (int i = 0; i < totalArray.size(); i++) {
                                            if (i == a || i == b || i == c || i == d || i == e || i == f || i == g || i == h) {
                                                continue;
                                            }
                                            outArray.set(8, (int) totalArray.get(8).get(i));
                                            for (int j = 0; j < totalArray.size(); j++) {
                                                if (j == a || j == b || j == c || j == d || j == e || j == f || j == g || j == h || j == i) {
                                                    continue;
                                                }
                                                outArray.set(9, (int) totalArray.get(9).get(j));
                                                for (int k = 0; k < totalArray.size(); k++) {
                                                    if (k == a || k == b || k == c || k == d || k == e || k == f || k == g || k == h || k == i || k == j) {
                                                        continue;
                                                    }
                                                    outArray.set(10, (int) totalArray.get(10).get(k));
                                                    for (int l = 0; l < totalArray.size(); l++) {
                                                        if (l == a || l == b || l == c || l == d || l == e || l == f || l == g || l == h || l == i || l == j || l == k) {
                                                            continue;
                                                        }
                                                        outArray.set(11, (int) totalArray.get(11).get(l));
                                                        for (int m = 0; m < totalArray.size(); m++) {
                                                            if (m == a || m == b || m == c || m == d || m == e || m == f || m == g || m == h || m == i || m == j || m == k || m == l) {
                                                                continue;
                                                            }
                                                            outArray.set(12, (int) totalArray.get(12).get(m));
                                                            bonus();
                                                            score();
                                                            System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+f+" "+g+" "+h+" "+i+" "+j+" "+k+" "+l+" "+m);
                                                            if (scoreArray.get(14) < outArray.get(14)) {
                                                                for(int z=0; z<outArray.size(); z++){
                                                                    scoreArray.set(z, outArray.get(z));
                                                                }
                                                                System.out.println(scoreArray);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += outArray.get(i);
        }
        if (sum >= 63) {
            result = sum;
            outArray.set(13, result);
        } else {
            result = 0;
            outArray.set(13, result);
        }
    }

    public void score() {
        int scorePoint = 0;
        for (int i = 0; i < 13; i++) {
            scorePoint += outArray.get(i);
        }
        result = scorePoint;
        outArray.set(14, result);
    }
}