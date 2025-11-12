import java.io.File;
import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.out.println("必須且只能有一個檔案");
            System.exit(0);
        }

        ScoreManager scoreManager = new ScoreManager();

        File file = new File(args[0]);
        Scanner reader = new Scanner(file);
        while(reader.hasNextLine()) {
            String[] str = reader.nextLine().split("\\s+");
            String name = str[0];
            int[] scores = new int[5];
            for(int i=1;i<6;i++){
                scores[i-1] = Integer.parseInt(str[i]);
            }
            scoreManager.addStudent(name, scores);
        }
        reader.close();

        System.out.println("歡迎使用成績管理系統");

        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning){
            String inputLine = input.nextLine().trim();
            if(inputLine.isEmpty()){
                continue;
            }

            String commandKey = inputLine.split("\\s+")[0];
            if(commandKey.equals("exit")){
                System.out.println("感謝使用!再見!");
                isRunning = false;
                break;
            }
            else if (commandKey.equals("add")) {}
            else if (commandKey.equals("delete")) {}
            else if (commandKey.equals("show")) {}
            else {
                System.out.println("指令輸入錯誤!請重新輸入");
                break;
            }


        }
    }
}