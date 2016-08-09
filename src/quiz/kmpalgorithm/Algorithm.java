package quiz.kmpalgorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Algorithm {
    private String string = "";
    private String patten = "";
    private int pattenSize = 0;
    private int stringSize = 0;
    private int[] pi;
    private ArrayList<Integer> ans = null;

    Algorithm() {
        InputString inputString = new InputString();
        this.string = inputString.getString();
    }

    public void pattenInput() {
        Scanner scanner = new Scanner(System.in);
        patten = scanner.nextLine();
        scanner.close();
        pattenSize = patten.length();
    }

    public void kmpSearch() {
        piTable();
        stringSize = string.length();
        ans = new ArrayList<>();
        int j = 0;
        for(int i = 0 ; i < stringSize ; i++){
            System.out.println(string.charAt(i) +"와 " + patten.charAt(j) +" 비교");
            if(string.charAt(i) != patten.charAt(j)){
                System.out.println(string.charAt(i) +"와 " + patten.charAt(j) +" 가 불일치");
            }
            while(j > 0 && string.charAt(i) != patten.charAt(j)){
                j = pi[j-1];
                System.out.println("j 를 " + j +" 로 저장");
                if(j == 0){
                    System.out.println(string.charAt(i) +"와 " + patten.charAt(j) +" 가 불일치");
                }
            }
            if(string.charAt(i) == patten.charAt(j)){
                System.out.println(string.charAt(i) +"와 " + patten.charAt(j) +" 가 일치");
                if(j==pattenSize-1){
                    ans.add(i-pattenSize+1);
                    j = pi[j];
                }else{
                    j++;
                }
            }
        }
        System.out.println(ans);

        Iterator<Integer> it =ans.iterator();
        while(it.hasNext()){
            pattenSize = patten.length();
            int i = it.next();
            while(pattenSize > 0){
                System.out.print(string.charAt(i));
                pattenSize--;
                i++;
            }
            System.out.println();
        }
    }

    public void piTable() {
        pi = new int[pattenSize];
        int j = 0;
        for(int i = 1 ; i < pattenSize ; i++){
            System.out.println(patten.charAt(i) +"와 " + patten.charAt(j) +" 비교");
            if(patten.charAt(i) != patten.charAt(j)){
                System.out.println(patten.charAt(i) +"와 " + patten.charAt(j) +" 가 불일치");
            }
            while(j > 0 && patten.charAt(i) != patten.charAt(j)){
                j = pi[j-1];
                System.out.println("j 를 " + j +" 로 저장");
                if(j ==0){
                    System.out.println(patten.charAt(i) +"와 " + patten.charAt(j) +" 가 불일치");
                }
            }
            if(patten.charAt(i) == patten.charAt(j)){
                System.out.println(patten.charAt(i) +"와 " + patten.charAt(j) +" 가 일치");
                pi[i] = ++j;
            }
        }
        printTable();
    }

    public void print() {
        System.out.println("문자열 = " + string);
        System.out.print("패턴 입력 : ");
        pattenInput();
    }

    public void printTable() {
        System.out.println("------------------테이블 생성------------------");
        for (int k = 0; k < patten.length(); k++) {
            System.out.print(patten.charAt(k) + " ");
        }
        System.out.println();
        for (int k = 0; k < pi.length; k++) {
            System.out.print(pi[k] + " ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
    }
}
