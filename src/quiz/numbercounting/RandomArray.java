package quiz.numbercounting;

import java.util.ArrayList;
import java.util.Random;

public class RandomArray {
  Random random = new Random();
  ArrayList<Integer> randArray = new ArrayList<>();
  ArrayList<Integer> targetArray = new ArrayList<>();

  public void setRandN(int n) {
    while (n > 0) {
      int number = random.nextInt(100);
      randArray.add(number);
      n--;
    }
  }

  public void setRandM(int m) {
    while (m > 0) {
      int number = random.nextInt(100);
      if (!targetArray.contains(number)) {
        targetArray.add(number);
        m--;
      }
    }
  }

  public void printArray() {
    System.out.println("---------------------------대상 배열---------------------------");
    System.out.println(randArray);
    System.out.println("---------------------------타겟 배열---------------------------");
    System.out.println(targetArray);
  }
}
