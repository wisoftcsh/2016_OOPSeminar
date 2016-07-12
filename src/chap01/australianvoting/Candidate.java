package chap01.australianvoting;

import java.util.*;
import java.io.*;

public class Candidate {
    String name;
    int candidateNum;
    int n;
    int target = 0;
    int result;
    int nextResult;
    String line;
    String min;
    String max;
    int oneNum = 0;
    int sum = 0;

    ArrayList<String> information = new ArrayList<>();
    ArrayList<ArrayList> candidateName = null;
    ArrayList<Integer> votingNum = null;
    ArrayList<Integer> index = null;
    ArrayList<Integer> sindex = null;
    ArrayList<Integer> minIndex = null;
    ArrayList<String> counting = null;
    ArrayList<Integer> prefer = null;
    ArrayList<ArrayList> compArray = null;
    ArrayList<Integer> compares = null;
    ArrayList<Integer> init = null;
    FileWriter fw = null;
    File file = null;
    File fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\australianvoting\\candidateOutput.txt");

    Candidate() {
        file = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\australianvoting\\candidateInput.txt");
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
        max = Collections.max(counting);
        index = new ArrayList<>();
        int maxCount = 0;
        for (int k = 0; k < counting.size(); k++) {
            if (max.compareTo(counting.get(k)) == 0) {
                maxCount++;
            }
        }
        if (maxCount != 1) {
            System.out.println("첫 투표 결과 동률입니다.");
            for (int i = 0; i < counting.size(); i++) {
                if (max.equals(counting.get(i))) {
                    index.add(i);
                }
            }
        }
        if ((Double.parseDouble(max) / sum) * 100 >= 50) {
            if (index.size() != 0) {
                String candidateNameString;
                Iterator<Integer> its = index.iterator();
                while (its.hasNext()) {
                    candidateNameString = information.get(its.next() + target+1);
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
        } else if (index.size() == candidateNum) {
            String candidateNameStringAll;
            Iterator<Integer> itsAll = index.iterator();
            while (itsAll.hasNext()) {
                candidateNameStringAll = information.get(itsAll.next() + target+1);
                outPut(candidateNameStringAll);
                System.out.println(candidateNameStringAll);
            }
            index.clear();
            counting.clear();
            return -9;
        }
        return -2;
    }

    public int secondVoing() {
        System.out.println("재투표 시작");
        min = Collections.min(counting);
        for (int i = 0; i < counting.size(); i++) {
            if (min.equals("0")) {
                min = counting.get(i);
                if (min.compareTo(counting.get(i + 1)) > 0) {
                    min = counting.get(i + 1);
                } else if (min.compareTo(counting.get(i + 1)) < 0 || min.compareTo(counting.get(i + 1)) == 0) {
                    continue;
                }
            }
        }
        sindex = new ArrayList<>();
        int minCount = 0;
        for (int k = 0; k < counting.size(); k++) {
            if (min.compareTo(counting.get(k)) == 0) {
                minCount++;
            }
        }
        if (minCount != 1) {
            for (int i = 0; i < counting.size(); i++) {
                if (min.equals(counting.get(i))) {
                    sindex.add(i);
                }
            }
        }
        if (counting.size() - sindex.size() == 1) {
            for (int i = 0; i < counting.size(); i++) {
                if (!(counting.get(i).equals(min))) {
                    counting.clear();
                    return i + 999;
                }
            }
        }
        minIndex = new ArrayList<>();
        for (int i = 0; i < counting.size(); i++) {
            if (counting.get(i).equals(min)) {
                minIndex.add(i);
            }
        }
        for (int i = 0; i < candidateName.size(); i++) {
            if (candidateName.get(i).indexOf("탈락") != 0) {
                for (int j = 0; j < minIndex.size(); j++) {
                    int temp = minIndex.get(j);
                    candidateName.get(temp).set(0, "탈락");
                }
            }
        }
        int pre = preference();
        for (int i = 0; i < oneNum; i++) {
            candidateName.get(pre).add("1");
        }
        oneNum = 0;
        return -5;
    }

    public int preference() {
        prefer = new ArrayList<>();
        for (int i = 0; i < candidateName.size(); i++) {
            if (candidateName.get(i).indexOf("탈락") != 0) {
                prefer.add(i);
            } else if (candidateName.get(i).indexOf("탈락") == 0) {
                for (int j = 0; j < candidateName.get(i).size(); j++) {
                    if (candidateName.get(i).get(j).equals("1")) {
                        oneNum++;
                        candidateName.get(i).set(j, "0");
                    }
                }
            }
        }
        compArray = new ArrayList<>();
        for (int i = 0; i < candidateNum; i++) {
            init = new ArrayList<>();
            compArray.add(init);
        }
        int compCount = 0;
        for (int j = 0; j < prefer.size(); j++) {
            compares = new ArrayList<>();
            for (int comp = 0; comp <= candidateNum; comp++) {
                for (int i = 0; i < votingNum.size(); i++) {
                    if (candidateName.get(prefer.get(j)).get(i).equals(String.valueOf(comp))) {
                        compCount++;
                    }
                }
                compares.add(compCount);
                compCount = 0;
            }
            compArray.set(prefer.get(j), compares);
        }
        int priority = 0;
        int k = 1;
        for (int pro = 0; pro < compArray.size(); pro++) {
            int first = 0;
            int second = 0;
            if (pro + 1 == compArray.size()) {
                break;
            }
            if (compArray.get(pro).size() != 0) {
                first = (int) compArray.get(pro).get(k);/// 캐스팅 이유 ? Integer형 인데 int 형이 아닌 오브젝트.
            }
            if (compArray.get(pro + 1).size() != 0) {
                second = (int) compArray.get(pro + 1).get(k);
            }
            if (first > second) {
                priority = pro;
            } else if (first < second) {
                priority = pro + 1;
            } else if (first == second) {
                k++;
                continue;
            }
        }
        return priority;
    }

    public void voting() {
        int loop = 0;
        fileWrite.delete();
        fileWrite = new File("E:\\Development\\java\\OOPSeminar_2016\\src\\chap01\\australianvoting\\candidateOutput.txt");
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
                sum = candidateName.get(0).size();
                result = firstCounting();
                if (result == -1 || result == -9) {
                    candidateName.clear();
                    votingNum.clear();
                } else if (result != -1 && result != -2) {
                    outPut(information.get(result + target + 1));
                    System.out.println("당선 : " + information.get(result + target + 1));
                    candidateName.clear();
                    votingNum.clear();
                } else if (result == -2) {
                    int secondResult = secondVoing();
                    if (secondResult >= 999) {
                        int realI = secondResult - 999;
                        outPut(information.get(realI + target + 1));
                        System.out.println("당선 : " + information.get(realI + target + 1));
                        candidateName.clear();
                        votingNum.clear();
                    } else if (secondResult == -5) {
                        nextResult = firstCounting();
                        if (nextResult == -2) {
                            while (((Double.parseDouble(max) / sum) * 100 < 50)) {
                                    secondVoing();
                                    nextResult = firstCounting();
                                    if (nextResult != -2) {
                                    break;
                                }
                            }
                        }
                        outPut(information.get(nextResult + target + 1));
                        System.out.println("당선 : " + information.get(nextResult + target + 1));
                        candidateName.clear();
                        votingNum.clear();
                        counting.clear();
                        compArray.clear();
                    }
                } else {
                    System.out.println("판별 실패");
                }
                System.out.println("투표 마감");
                System.out.println();
            }
            loop++;
        }
    }
}
