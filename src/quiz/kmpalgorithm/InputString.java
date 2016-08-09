package quiz.kmpalgorithm;

import java.io.*;

public class InputString {
    File file = new File("E:\\developments\\OOPSeminar_2016\\src\\quiz\\kmpalgorithm\\Input\\Input.txt");
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    private String string = "";

    InputString(){
        String data;
        try{
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            if((data = bufferedReader.readLine()) != null){  //여러 줄이면 while
                string = data;
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    public String getString (){
        return string;
    }
}
