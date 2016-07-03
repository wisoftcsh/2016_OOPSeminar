package chap01.australian_voting;

import java.util.*;
import java.io.*;

public class Candidate {
    String name;
    int candidateNum;
    int n;
    int target = 0;
    int result;
    String line;
    ArrayList<String> information = new ArrayList<>();
    ArrayList<ArrayList> candidateName = null;
    ArrayList<Integer> votingNum = null;
    ArrayList<Integer> index = null;
    ArrayList<Integer> sindex = null;
    ArrayList<String> counting = null;
    FileWriter fw = null;
    File file = null;
    File fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\australian_voting\\candidateOutput.txt");

    Candidate() {
        file = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\australian_voting\\candidateInput.txt");
        Scanner fs = null;
        try {
            fs = new Scanner(new FileReader(file));
            while (fs.hasNext()) {
                line = fs.nextLine();
                information.add(line);
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
        System.out.println("=============================");
        Iterator<String> it = information.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("=============================");
        System.out.println();
    }

    public void outPut(String string) {
        try {
            String c = "당선 : " + string + "\r\n";
            fw = new FileWriter(fileWrite, true);
            fw.write(c);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류입니다.");
        }

    }

    public int firstCounting() {
        int count = 0;
        counting = new ArrayList<>();
        for (int i = 0; i < candidateName.size(); i++) {
            for (int j = 0; j < candidateName.get(i).size(); j++) {
                if (candidateName.get(i).get(j).equals("1")) {
                    count++;
                }
            }
            counting.add(i, String.valueOf(count));
            count = 0;
        }

        int sum = 0;
        Iterator<String> itsum = counting.iterator();
        while (itsum.hasNext()) {
            sum += Integer.parseInt(itsum.next());
        }
        String max = counting.get(0);
        index = new ArrayList<>();

        for (int k = 1; k < counting.size(); k++) {
            if (max.compareTo(counting.get(k)) < 0) {
                max = counting.get(k);
            } else if (max.compareTo(counting.get(k)) > 0) {
                continue;
            } else if (max.compareTo(counting.get(k)) == 0) {
                System.out.println("첫 투표 결과 동률입니다.");
                for (int i = 0; i < counting.size(); i++) {
                    if (max.equals(counting.get(i))) {
                        index.add(i);
                    }
                }
            }
        }
        if ((Double.parseDouble(max) / sum) * 100 >= 50) {
            if (index.size() != 0) {
                String candidateNameString;
                Iterator<Integer> its = index.iterator();
                while (its.hasNext()) {
                    candidateNameString = information.get(its.next() + candidateNum);
                    outPut(candidateNameString);
                    System.out.println(candidateNameString);
                }
                index.clear();
                counting.clear();
                return -1;
            }
            int idx = 0;
            int tmp = counting.indexOf(max);
            if (tmp != -1 && index.size() == 0) {
                idx = tmp;
                counting.clear();
                return idx;
            }
        }
        return -2;
    }

    public int secondVoing() {
        String min = counting.get(0);
        for (int k = 1; k < counting.size(); k++) {
            if (min.compareTo(counting.get(k)) < 0) {
                continue;
            } else if (min.compareTo(counting.get(k)) > 0) {
                min = counting.get(k);
            } else if (min.compareTo(counting.get(k)) == 0) {
                sindex = new ArrayList<>();
                for (int i = 0; i < counting.size(); i++) {
                    if (min.equals(counting.get(i))) {
                        sindex.add(i);
                    }
                }
            }
        }
        int minInt = Integer.parseInt(min);
        if(counting.size()-sindex.size() == 1){
            System.out.println(counting);
            System.out.println(sindex);
            for(int i =0;i<counting.size();i++){
                if(!(counting.get(i).equals(min))){
                    counting.clear();
                    return i+999;
                }
            }
        }
        counting.clear();
        return -5;
    }

    public void voting() {
        int split = 0;
        int loop = 0;
        fileWrite.delete();
        fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\australian_voting\\candidateOutput.txt");
        while (true) {
            if (loop == information.size()) {
                loop = 0;
                return;
            }

            if (information.get(loop).length() == 0 && information.get(loop + 1).length() == 0) {
                System.out.println("다른 케이스 발견");
                System.out.println();
            }
            if (information.get(loop).length() == 0 && !(information.get(loop + 1).length() == 0)) {
                System.out.println("투표 시작");
                target = loop + 1;
                candidateNum = Integer.parseInt(information.get(target));
                candidateName = new ArrayList<>();
                for (int j = 1; j <= candidateNum; j++) {
                    votingNum = new ArrayList<>();
                    candidateName.add(votingNum);
                }
                for (int k = target + candidateNum + 1; k < information.size() && information.get(k).length() != 0; k++) {
                    String[] tmp = information.get(k).split(" ");
                    for (int c = 0; c <= candidateNum - 1; c++) {
                        candidateName.get(c).add((tmp[c]));
                    }
                }
                result = firstCounting();
                if (result == -1) {
                    candidateName.clear();
                    votingNum.clear();
                } else if (result != -1 && result != -2) {
                    outPut(information.get(result + target + 1));
                    System.out.println("당선 : " + information.get(result + target + 1));
                    candidateName.clear();
                    votingNum.clear();
                } else if (result == -2) {
                    int secondResult = secondVoing();
                    if(secondResult>=999){
                        int realI = secondResult-999;
                        outPut(information.get(realI + target + 1));
                        System.out.println("당선 : " + information.get(realI + target + 1));
                        candidateName.clear();
                        votingNum.clear();
                    } else if (secondResult == -5) {
                        System.out.println("판별 실패");
                    }
                }else{
                    System.out.println("판별 실패");
                }
                System.out.println("투표 마감");
                System.out.println();
            }
            loop++;
        }
    }
}
