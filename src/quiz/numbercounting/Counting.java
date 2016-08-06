package quiz.numbercounting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Counting {
  RandomArray randomArray = new RandomArray();
  ArraySort arraySort = new ArraySort();
  List<Integer> tmp = new ArrayList<>();
  private int n = 0;
  private int m = 0;
  private int count = 0;
  private int caseNumber = 0; // 처음 배열 수정할때 사용하는 번호

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

  public boolean binarySearch(int target) {
    int first = 0;
    int last = randomArray.randArray.size() - 1;  // 0 ~ n까지
    while (first <= last) {
      int mid = (first + last) / 2;
      if (target == randomArray.randArray.get(mid)) {
        int addMid = mid + 1;
        int decMid = mid - 1;
        count++;
        while (true) { // 주변 탐색
          if ((addMid >= randomArray.randArray.size() && decMid < 0) ||
              (addMid >= randomArray.randArray.size() && randomArray.randArray.get(decMid) != target) ||
              (randomArray.randArray.get(addMid) != target && decMid < 0) ||
              (randomArray.randArray.get(addMid) != target && randomArray.randArray.get(decMid) != target)) {
            break; // 탈출 조건
          }
          if (addMid < randomArray.randArray.size() && randomArray.randArray.get(addMid) == target) {
            count++;
            addMid++;
          }          //////// ?????? 기본이흔들린 부분 다중if문의 경우에 하나가 참이면 다른 하나는 무시
          if (decMid >= 0 && randomArray.randArray.get(decMid) == target) {
            count++;
            decMid--;
          }
        }
        return true;
      } else {
        if (target < randomArray.randArray.get(mid))
          last = mid - 1;
        else
          first = mid + 1;
      }
    }
    return false;
  }

  public boolean sortBinarySearch(int target) {
    int first = 0;
    int last = tmp.size() - 1;  // 0 ~ n까지
    while (first <= last) {
      int mid = (first + last) / 2;
      if (target == tmp.get(mid)) {
        int addMid = mid + 1;
        int decMid = mid - 1;
        count++;
        while (true) { // 주변 탐색
          if ((addMid >= tmp.size() && decMid < 0) ||
              (addMid >= tmp.size() && tmp.get(decMid) != target) ||
              (tmp.get(addMid) != target && decMid < 0) ||
              (tmp.get(addMid) != target && tmp.get(decMid) != target)) {
            break; // 탈출 조건
          }
          if (addMid < tmp.size() && tmp.get(addMid) == target) {
            count++;
            addMid++;
          }
          if (decMid >= 0 && tmp.get(decMid) == target) {
            count++;
            decMid--;
          }
        }
        tmp.subList(addMid, tmp.size());
        return true;
      } else {
        if (target < tmp.get(mid))
          last = mid - 1;
        else
          first = mid + 1;
      }
    }
    return false;
  }

  public int basicBinarySearch(int target) { //초기 배열 설정을 위한 이진탐색
    int first = 0;
    int last = randomArray.randArray.size() - 1;  // 0 ~ n까지
    while (first <= last) {
      int mid = (first + last) / 2;
//      System.out.println(mid + " " + first + " " + last);
      if (randomArray.randArray.get(mid) == target) { // 발견이 된 경우
        while (mid + 1 < randomArray.randArray.size() && mid - 1 >= 0) {
          if (caseNumber == 0) {
            if (randomArray.randArray.get(mid - 1) == target) {
              mid--;
            } else {
              break;
            }
          } else if (caseNumber == 1) {
            if (randomArray.randArray.get(mid + 1) == target) {
              mid++;
            } else {
              break;
            }
          }
        }
        return mid;
      } else {
        if (target < randomArray.randArray.get(mid))
          last = mid - 1;
        else
          first = mid + 1;
      }
    }
    //발견하지 못한 경우 인덱스를 어떻게 추출할까>/
    first = 0;
    last = randomArray.randArray.size() - 1;  // 0 ~ n까지
    while (first <= last) {
      System.out.println("발견 못함, 근사값 설정");
      int mid = (first + last) / 2;
      if (first == last && last == mid) {
        break;
      }
      if (mid + 1 < randomArray.randArray.size() && mid - 1 >= 0) {
        if (randomArray.randArray.get(mid) < target && randomArray.randArray.get(mid + 1) > target) {
          return mid + 1;
        } else if (randomArray.randArray.get(mid) > target && randomArray.randArray.get(mid - 1) < target) {
          return mid;
        } else {
          if (target < randomArray.randArray.get(mid))
            last = mid - 1;
          else
            first = mid + 1;
        }
      }
    }
    if (randomArray.randArray.get(randomArray.randArray.size() - 1) < target) {
      return randomArray.randArray.size() - 1;
    } else if (randomArray.randArray.get(0) > target) {
      return 0;
    }
    return -1;
  }

  public void targetSearch(Iterator<Integer> targetIt) {
    while (targetIt.hasNext()) {
      int target = targetIt.next();
      int min = randomArray.randArray.get(0);
      int max = randomArray.randArray.get(randomArray.randArray.size() - 1);
      if (target >= min && target <= max) {
        binarySearch(target);
      }
      if (count == 0) {
        System.out.println("target : " + target + " 이(가) 배열에 존재하지 않습니다.");
      } else {
        System.out.println("target : " + target + " 이(가) 배열에 " + count + " 만큼 존재합니다.");
      }
      count = 0;
    }
  }

  public void sortTargetSearch(Iterator<Integer> targetIt) {
    while (targetIt.hasNext()) {
      int target = targetIt.next();
      if (tmp.size() == 0) { // 배열이 전부 삭제 되었다면 더이상 존재 x
        System.out.println("target : " + target + " 이(가) 배열에 존재하지 않습니다.");
        break;
      }
      int min = tmp.get(0);
      int max = tmp.get(tmp.size() - 1);
      if (target >= min && target <= max) {
        sortBinarySearch(target);
      }
      if (count == 0) {
        System.out.println("target : " + target + " 이(가) 배열에 존재하지 않습니다.");
      } else {
        System.out.println("target : " + target + " 이(가) 배열에 " + count + " 만큼 존재합니다.");
      }
      while (true) { // 탐색 후 제거
        if (tmp.size() == 0 || tmp.get(0) > target) {
          break;
        } else {
          tmp.remove(0);
        }
      }
      count = 0;
      System.out.println(tmp); //시간 비교시에는주석처리 해야함
    }
  }

  public void counting() {
    count = 0;
    System.out.println("=========================== N만 정렬 ============================");
    Iterator<Integer> targetIt = randomArray.targetArray.iterator();
    long startTime = System.currentTimeMillis();
    targetSearch(targetIt);
    long endTime = System.currentTimeMillis();
    long lTime = endTime - startTime;
    System.out.println("TIME : " + lTime + "(ms)");
    //타겟 배열 정렬
    System.out.println("======================= N 과 M 둘다 정렬 ========================");
    arraySort.sort(randomArray.targetArray);
    randomArray.printArray();
    tmp.addAll(randomArray.randArray); //깊은 복사
    int targetMax = randomArray.targetArray.get(randomArray.targetArray.size() - 1);
    int targetMin = randomArray.targetArray.get(0);
    caseNumber = 0;
    int minSearch = basicBinarySearch(targetMin);
    caseNumber = 1;
    int maxSearch = basicBinarySearch(targetMax);
    tmp = tmp.subList(minSearch, maxSearch);
    System.out.println("------------------------- 수정된 tmp --------------------------");
    System.out.println(tmp);
    System.out.println("---------------------------------------------------------------");
    targetIt = randomArray.targetArray.iterator();
    long startTime2 = System.currentTimeMillis();
    sortTargetSearch(targetIt);
    long endTime2 = System.currentTimeMillis();
    long lTime2 = endTime2 - startTime2;
    System.out.println("TIME : " + lTime2 + "(ms)");
  }
}
