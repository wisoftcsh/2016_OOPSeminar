package quiz.numbersumrecursion;

import java.util.Scanner;

public class NumberSum {
  private int n = 0 ;
  NumberSum(){
    numberInit();
  }
  public void numberInit(){
    Scanner scanner = new Scanner(System.in);
    n = scanner.nextInt();
    scanner.close();
  }

  public int numberSum(int n){
    if(n==1){
      return 1;
    }
    return (n--) + numberSum(n);
  }

  public void print(){
    System.out.println(numberSum(n));
  }
}
