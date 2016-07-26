package quiz.searchString;

import java.util.Scanner;

public class SearchString {
    String string = "KOIOIOIKOIOIOI";
    String tmp = "";
    SearchString() {
//        inputString(); 생략
    }

    public void inputString() {
        Scanner scanner = new Scanner(System.in);
        string = scanner.nextLine();
        if(string.length() > 10000){
            System.out.println("길이 초과입니다.");
            string = scanner.nextLine();
        }
        scanner.close();
    }

    public void search(){
        int oneCount = 0;
        int twoCount = 0;
        string.toUpperCase();
        tmp = string;
        for(int i = 0; i< tmp.length(); i++) {
            if(i+2 > tmp.length()){
                break;
            }
            if (tmp.charAt(i) == 'K' && tmp.charAt(i+1) == 'O' && tmp.charAt(i+2)=='I'){
                oneCount++;
            }
            if (tmp.charAt(i) == 'I' && tmp.charAt(i+1) == 'O' && tmp.charAt(i+2)=='I'){
                twoCount++;
            }
        }
        System.out.println(oneCount);
        System.out.println(twoCount);
    }
}
