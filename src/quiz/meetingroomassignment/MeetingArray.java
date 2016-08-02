package quiz.meetingroomassignment;

import java.util.ArrayList;
import java.util.Scanner;

public class MeetingArray {
    Scanner scanner = new Scanner(System.in);
    Meeting meeting = null;
    ArrayList<Meeting> meetingArrayList = new ArrayList<>();

    MeetingArray() {
      createMeetingArray();
    }

    public void input(){
        String information = scanner.nextLine();
        String[] tmp = information.split(" ");
        if(Integer.parseInt(tmp[1]) > 500 || Integer.parseInt(tmp[2]) >500){
            System.out.println("범위 오류");
            information = scanner.nextLine();
            tmp = information.split(" ");
        }
        meeting = new Meeting(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
        meetingArrayList.add(meeting);
    }
    public void createMeetingArray() {
        System.out.print("회의 수(5개이상 500개이하) : ");
        int number = scanner.nextInt();
        if(number < 5 || number >500){
            System.out.println("범위 오류");
            number = scanner.nextInt();
        }
        scanner.nextLine();
        while (number != 0) {
            input();
            number--;
        }
        scanner.close();
    }
}
