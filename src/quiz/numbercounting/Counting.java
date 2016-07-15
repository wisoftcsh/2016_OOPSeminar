package quiz.numbercounting;

import java.util.Iterator;
import java.util.Scanner;

public class Counting {
  RandomArray randomArray = new RandomArray();
  ArraySort arraySort = new ArraySort();
  private int n = 0;
  private int m = 0;

  Counting() {
    numberInit();
  }

  public void numberInit() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("배열의 크기 입력(1이상 200,000 미만) : ");
    n = scanner.nextInt();
    while (n < 1 || n > 200000) {
      System.out.println("숫자 범위 초과, 다시입력해주세요.");
      n = scanner.nextInt();
    }
    randomArray.setRandN(n); // N 입력
    System.out.println("타겟 배열 크기 입력(1이상 200,000 미만) : ");
    m = scanner.nextInt();
    while (m < 1 || m > 200000) {
      System.out.println("숫자 범위 초과, 다시입력해주세요.");
      m = scanner.nextInt();
    }
    randomArray.setRandM(m); // M 입력
    scanner.close();
    randomArray.randArray = arraySort.sort(randomArray.randArray);
    randomArray.printArray();
  }

  public void counting() {
    Iterator<Integer> targetIt = randomArray.targetArray.iterator();
    int count = 0;
    while (targetIt.hasNext()) {
      int target = targetIt.next();
      for (int i = 0; i < randomArray.randArray.size(); i++) {
        if (randomArray.randArray.get(i)==target) {
          count++;
        }
      }
      if (count == 0) {
        System.out.println("target : " + target + " 이(가) 배열에 존재하지 않습니다.");
      } else {
        System.out.println("target : " + target + " 이(가) 배열에 " + count + " 만큼 존재합니다.");
      }
      count = 0;
    }
  }
}
