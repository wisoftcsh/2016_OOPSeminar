package quiz.australianvotinghash;

import java.util.ArrayList;
import java.util.HashMap;

public class Voting {
  static int candidateNumber = 0;
  ArrayList<String> informationArrayList = TextInOut.information; // 정보
  HashMap<Integer, Candidate> candidateMap = null; // 후보자 맵
  ArrayList<ArrayList> boxArray = null; // 투표함
  TextInOut textInOut = new TextInOut(); // 파일 출력 함수 사용 객체
  Candidate candidate = null; // 투표자 객체
  VotingBox paper = null; // 투표지 객체
  int loop = 0;
  int target = 0;
  int caseNumber = 0;
  int num = 1; //비교 변수
  int voter = 0; // 투표자 수

  Voting() {
  }

  public void read() {
    paper = new VotingBox();
    caseNumber = Integer.parseInt(informationArrayList.get(loop++));
    while (loop != informationArrayList.size()) {
      if (informationArrayList.get(loop).isEmpty() &&
          !informationArrayList.get(loop + 1).isEmpty()) {
        System.out.println("투표 시작");
        target = loop + 1;
        candidateNumber = Integer.parseInt(informationArrayList.get(target)); //후보자 수
        candidateCreate();  // 후보자 객체 생성
        boxArray = paper.votingBox(target); //투표함
        voter = boxArray.size();
        voting();
      } else if (informationArrayList.get(loop).isEmpty() &&
          informationArrayList.get(loop + 1).isEmpty()) {
        System.out.println("다른 케이스 발견");
      } else if (loop >= informationArrayList.size()) {
        break;
      }
      loop++;
    }
  }

  public void candidateCreate() {
    String candidateName;
    candidateMap = new HashMap<>();
    for (int i = 1; i <= candidateNumber; i++) {
      target += 1;
      candidateName = informationArrayList.get(target);
      candidate = new Candidate(candidateName);
      candidateMap.put(i, candidate);
    }
  }

  public void zeroRemove() { // 득표수가 0인 후보자 탈락
    for (int i = 1; i <= candidateNumber; i++) {
      if (candidateMap.get(i).NumberCounter == 0) {
        candidateMap.remove(i);
        for (int j = 0; j < boxArray.size(); j++) {
          for (int k = 0; k < boxArray.get(j).size(); k++) {
            if (boxArray.get(j).get(k).equals(i)) {  //탈락 후보 무효표 처리
              boxArray.get(j).remove(k);
            }
          }
        }
      }
    }
  }

  public void boxRemove(int hashIndex) { // 탈락자 후보 표 삭제
    for (int j = 0; j < boxArray.size(); j++) {
      for (int k = 0; k < boxArray.get(j).size(); k++) {
        if (boxArray.get(j).get(k).equals(hashIndex)) {
          boxArray.get(j).remove(k);
        }
      }
    }
  }

  public int minDetermine() {
    int min = 999;// 최하 득표 후보 판별
    for (int i = 1; i <= candidateNumber; i++) {
      if (candidateMap.get(i) != null) {
        if (min > candidateMap.get(i).getNumberCounter()) {
          min = candidateMap.get(i).getNumberCounter();
        }
      } else if (candidateMap.get(i) == null) {
        continue;
      }
    }
    return min;
  }

  public void removeHash(int min) {
    for (int i = 1; i <= candidateNumber; i++) {
      if (candidateMap.get(i) != null) {
        if (candidateMap.get(i).getNumberCounter() == min) {
          candidateMap.remove(i);
          boxRemove(i);
        }
      } else {
        continue;
      }
    }
  }

  public int isMajority() {
    int max = 0;
    int equalCounter = 0;
    int count = 0; // 동률 비교 변수
    for (int i = 1; i <= candidateNumber; i++) {
      if (candidateMap.get(i) != null) {
        equalCounter = candidateMap.get(1).NumberCounter;
        if (max < candidateMap.get(i).getNumberCounter()) {
          max = candidateMap.get(i).getNumberCounter();
        }
      } else {
        continue;
      }
    }

    for (int i = 1; i <= candidateMap.size(); i++) {
      if (equalCounter == candidateMap.get(i).getNumberCounter()) {
        count++; //동률 처리
      }
    }
    if (count == candidateNumber) {
      for (int i = 1; i <= candidateNumber; i++) {
        if (candidateMap.get(i) != null) {
          System.out.println(candidateMap.get(i).getName());  //output
          textInOut.ouput(candidateMap.get(i).getName());
        }
      }
      return 0;
    }


    int majority = (max * 100) / voter;
    if (majority >= 50) {
      for (int i = 1; i <= candidateNumber; i++) {
        if (candidateMap.get(i) != null) {
          if (candidateMap.get(i).getNumberCounter() == max) {
            System.out.println(candidateMap.get(i).getName());  //output
            textInOut.ouput(candidateMap.get(i).getName());
          }
        }
      }
      return 1;
    }
    return -1;
  }

  public void counterClear() {
    for (int i = 1; i <= candidateNumber; i++) {
      if (candidateMap.get(i) != null) {
        candidateMap.get(i).NumberCounter = 0;
      } else {
        continue;
      }
    }
  }

  public void total() {
    while (true) { // 1순위 투표 집계
      if (num > candidateNumber) {
        break;
      }
      for (int i = 0; i < boxArray.size(); i++) {
        if (boxArray.get(i).get(0).equals(num)) {
          candidateMap.get(num).setNumberCounter();
        }
      }
      num++;
    }
    num = 1;
  }

  public void voting() {
    total();
    int exit = isMajority();
    while (exit == -1) {
      System.out.println(boxArray);
      System.out.println(candidateMap);
      zeroRemove();
      System.out.println(boxArray);
      System.out.println(candidateMap);
      removeHash(minDetermine()); //최소 득표자 탈락
      System.out.println(boxArray);
      System.out.println(candidateMap);
      counterClear();
      total();
      exit = isMajority();
    }
  }
}