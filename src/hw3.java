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
        label:
        while(isRunning){
            System.out.print(">");
            String inputLine = input.nextLine().trim();
            if(inputLine.isEmpty()){
                continue;
            }
            String[] parts = inputLine.split("\\s+");
            String commandKey = parts[0];
            switch (commandKey) {
                case "exit":
                    System.out.println("感謝使用!再見!");
                    isRunning = false;
                    break label;
                case "add": {
                    String name = parts[1];
                    String[] scoresStr = parts[2].split(",");
                    int[] scores = new int[scoresStr.length];
                    for (int i = 0; i < scoresStr.length; i++) {
                        scores[i] = Integer.parseInt(scoresStr[i]);
                    }

                    boolean success = scoreManager.addStudent(name, scores);
                    if(success){System.out.println("新增成功");}
                    else{System.out.println("新增失敗，此學生已存在");}
                    break;
                }
                case "delete": {
                    String name = parts[1];
                    boolean success = scoreManager.deleteStudent(name);
                    if(success){System.out.println("刪除成功");}
                    else{System.out.println("刪除失敗，查無此學生");
                    break;
                }
                case "show":
                    if (parts[1].equals("individual") && parts[2].equals("average")) {
                        String name = parts[3];
                        System.out.println(scoreManager.showIndividualAverage(name));
                    } else if (parts[1].equals("individual") && parts[2].equals("score")) {
                        String name = parts[3];
                        int[] scores = scoreManager.showIndividualScore(name);
                        if (scores != null) {
                            System.out.print(name);
                            for (int score : scores) {
                                System.out.print(" " + score);
                            }
                            System.out.println();
                        } else {
                            System.out.println("查無資料");
                        }
                    } else if (parts[1].startsWith("Homework")) {
                        String numberStr = parts[1].substring(8);
                        int number = Integer.parseInt(numberStr);
                        System.out.println(scoreManager.showHomeworkAverage(number));
                    } else if (parts[1].startsWith("Final")) {
                        String numberStr = parts[2].substring(7);
                        int number = Integer.parseInt(numberStr);
                        System.out.println(scoreManager.showFinalProjectAverage(number));
                    } else if (parts[1].equals("total")) {
                        String result = scoreManager.showTotal();
                        System.out.print(result);
                    } else {
                        System.out.println("指令輸入錯誤!請重新輸入");
                    }
                    break;
                default:
                    System.out.println("指令輸入錯誤!請重新輸入");
                    continue;
            }
        }
    }
}