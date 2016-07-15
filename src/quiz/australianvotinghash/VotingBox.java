package quiz.australianvotinghash;

import java.util.ArrayList;

public class VotingBox {
  ArrayList<String> boxArrayList = TextInOut.information;
  ArrayList<Integer> box = null;
  ArrayList<ArrayList> ranking = null; // 투표함
  int targetBox = 0;
  int index = 0;

  public ArrayList votingBox(int target) {
    this.targetBox = target + 1; //투표용지 포인터
    box = new ArrayList<>();
    ranking = new ArrayList<>();
    int nullPointer = targetBox; //공백까지
    for (int j = 1; nullPointer < boxArrayList.size() && !boxArrayList.get(nullPointer).isEmpty(); j++) {
      box = new ArrayList<>();
      ranking.add(box);
      nullPointer++;
    } // 투표함 만들기
    while (targetBox < boxArrayList.size() && !boxArrayList.get(targetBox).isEmpty()) {
      if (targetBox == boxArrayList.size()) {
        break;
      }
      String[] tmpBox = boxArrayList.get(targetBox).split(" ");
      StringToIntArray(tmpBox);
      targetBox++;
    }
    index = 0;
    return ranking;
  }

  public void StringToIntArray(String[] tmpBox) {
    for (int i = 0; i < tmpBox.length && !tmpBox[i].isEmpty(); i++) {
      ranking.get(index).add(Integer.parseInt(tmpBox[i]));
    }
    index++;
  }
}
