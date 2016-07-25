package quiz.numbercounting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Counting {
    RandomArray randomArray = new RandomArray();
    ArraySort arraySort = new ArraySort();
    ArrayList<Integer> tmp = new ArrayList<>();
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

    public boolean binarySearch(int target) {
        int first = 0;
        int last = tmp.size() - 1;  // 0 ~ n까지
        while (first <= last) {
            int mid = (first + last) / 2;
            if (target == tmp.get(mid)) {
                tmp.remove(mid);
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

    public void counting() {
        tmp.addAll(randomArray.randArray);
        Iterator<Integer> targetIt = randomArray.targetArray.iterator();
        int count = 0;
        while (targetIt.hasNext()) {
            int target = targetIt.next();
            int min = randomArray.randArray.get(0);
            int max = randomArray.randArray.get(randomArray.randArray.size() - 1);
            long startTime = System.currentTimeMillis();
            if(target < min || target > max){
                // nothing
            }else{
                while (binarySearch(target)) {
                    count++;
                }
            }
            long endTime = System.currentTimeMillis();
            long lTime = endTime - startTime;
            System.out.println("TIME : " + lTime + "(ms)");
            if (count == 0) {
                System.out.println("target : " + target + " 이(가) 배열에 존재하지 않습니다.");
            } else {
                System.out.println("target : " + target + " 이(가) 배열에 " + count + " 만큼 존재합니다.");
            }
            count = 0;
        }
    }
}
