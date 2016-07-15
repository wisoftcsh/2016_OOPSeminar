package quiz.numbercounting;

import java.util.ArrayList;

public class RandomArray {
  ArrayList<Integer> randArray = new ArrayList<>();
  ArrayList<Integer> targetArray = new ArrayList<>();
  public void setRandN(int n){
    if(n < 1 || n > 200000){

    }
    while(n > 0){
      int number = (int) (Math.random()*10 + 1);
      randArray.add(number);
      n--;
    }
  }
  public void setRandM(int m){
    if(m < 1 || m > 200000){
      System.out.println("숫자를 다시 입력해 주세요.");
    }
    while(m > 0){
      int number = (int) (Math.random()*10 + 1);
      targetArray.add(number);
      m--;
    }
  }

  public void printArray(){
    System.out.println("---------------------------대상 배열---------------------------");
    System.out.println(randArray);
    System.out.println("---------------------------타겟 배열---------------------------");
    System.out.println(targetArray);
    System.out.println("---------------------------------------------------------------");
  }
}
