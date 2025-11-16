import java.io.File;
import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) throws Exception {
        //程式啟動時必須且只能提供一個檔案，否則就結束程式
        if (args.length != 1) {
            System.out.println("必須且只能有一個檔案");
            System.exit(0);
        }

        // 建立 ScoreManager 核心物件，用於管理所有學生資料和相關操作
        ScoreManager scoreManager = new ScoreManager();

        //用reader物件讀取檔案，把每行的文字根據一個或多個空白分割，第一個字是姓名，後面五個則是成績，建立一個陣列來儲存轉成整數後的成績資料，然後關閉reader
        File file = new File(args[0]);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] str = reader.nextLine().split("\\s+");
            String name = str[0];
            int[] scores = new int[5];
            for (int i = 1; i < 6; i++) {
                scores[i - 1] = Integer.parseInt(str[i]);
            }
            scoreManager.addStudent(name, scores);
        }
        reader.close();

        //顯示歡迎訊息，並列出所有可用指令
        System.out.println("========================================================================================================================");
        System.out.println("歡迎使用成績管理系統");
        System.out.println("========================================================================================================================");
        System.out.println("可用指令：");
        System.out.println("  add [姓名] [Homework1成績,Homework2成績,Homework3成績,Final Project1成績,Final Project2成績]");
        System.out.println("  delete [姓名]");
        System.out.println("  update [姓名] [科目] [成績]");
        System.out.println("  show individual score [姓名]");
        System.out.println("  show individual average [姓名]");
        System.out.println("  show Homework[1-3] average");
        System.out.println("  show Final Project[1-2] average");
        System.out.println("  show total");
        System.out.println("  exit");
        System.out.println("========================================================================================================================");

        //持續讀取使用者輸入的指令，用switch判斷指令並呼叫ScoreManager的各個方法，同時做好錯誤處理。若是只按Enter則繼續下一次迴圈，而若是輸入exit則結束程式
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.print(">");
            String inputLine = input.nextLine().trim();
            if (inputLine.isEmpty()) {
                continue;
            }
            String[] parts = inputLine.split("\\s+");
            String commandKey = parts[0];
            switch (commandKey) {
                case "exit":
                    System.out.println("感謝使用!再見!");
                    isRunning = false;
                    break;
                case "add": {
                    try {
                        String name = parts[1];
                        String[] scoresStr = parts[2].split(",");
                        int[] scores = new int[scoresStr.length];
                        for (int i = 0; i < scoresStr.length; i++) {
                            scores[i] = Integer.parseInt(scoresStr[i]);
                        }

                        boolean success = scoreManager.addStudent(name, scores);
                        if (success) {
                            System.out.println("新增成功");
                        } else {
                            System.out.println("新增失敗：此學生已存在");
                        }
                        break;
                    } catch (Exception e){
                        System.out.println("新增失敗：格式錯誤，正確格式為 add [Student name] [Homework1, Homework2, Homework3, Final Project1, Final Project2]");
                    }
                    break;
                }
                case "delete": {
                    try {
                        String name = parts[1];

                        int[] scores = scoreManager.showIndividualScore(name);
                        if (scores == null) {
                            System.out.println("刪除失敗：查無此學生");
                            break;
                        }

                        System.out.print("即將刪除學生：" + name + " (成績：");
                        for (int i = 0; i < scores.length; i++) {
                            System.out.print(scores[i]);
                            if (i < scores.length - 1) System.out.print(", ");
                        }
                        System.out.println(")");
                        System.out.print("確定要刪除嗎？(y/n): ");

                        String confirm = input.nextLine().trim().toLowerCase();
                        if (confirm.equals("y") || confirm.equals("yes")) {
                            boolean success = scoreManager.deleteStudent(name);
                            if (success) {
                                System.out.println("刪除成功");
                            }
                        } else {
                            System.out.println("已取消刪除");
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("刪除失敗：格式錯誤");
                    }
                    break;
                }
                case "update": {
                    try {
                        String name = parts[1];
                        String subject;
                        int newScore;

                        if (parts[2].equals("Final") && parts.length >= 5) {
                            subject = parts[2] + " " + parts[3];
                            newScore = Integer.parseInt(parts[4]);
                        } else {
                            subject = parts[2];
                            newScore = Integer.parseInt(parts[3]);
                        }

                        int[] oldScores = scoreManager.showIndividualScore(name);
                        if (oldScores == null) {
                            System.out.println("修改失敗：學生不存在");
                            break;
                        }

                        int index = -1;
                        if (subject.equals("Homework1")) index = 0;
                        else if (subject.equals("Homework2")) index = 1;
                        else if (subject.equals("Homework3")) index = 2;
                        else if (subject.equals("Final Project1")) index = 3;
                        else if (subject.equals("Final Project2")) index = 4;

                        if (index == -1) {
                            System.out.println("修改失敗：科目名稱錯誤");
                            break;
                        }

                        int oldScore = oldScores[index];

                        boolean success = scoreManager.updateScore(name, subject, newScore);
                        if (success) {
                            System.out.println("修改成功：" + name + " 的 " + subject + " 從 " + oldScore + " 改為 " + newScore);
                        } else {
                            System.out.println("修改失敗");
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("修改失敗：格式錯誤，正確格式為 update [Student name] [科目] [成績]");
                    }
                    break;
                }
                case "show":
                    try {
                        if (parts.length < 2) {
                            System.out.println("指令輸入錯誤!請重新輸入");
                            break;
                        }

                        if (parts[1].equals("individual") && parts.length >= 4 && parts[2].equals("average")) {
                            String name = parts[3];
                            double avg = scoreManager.showIndividualAverage(name);
                            if (avg == -1) {
                                System.out.println("查無資料");
                            } else {
                                System.out.println(formatAverage(avg));
                            }
                        } else if (parts[1].equals("individual") && parts.length >= 4 && parts[2].equals("score")) {
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
                            System.out.println(formatAverage(scoreManager.showHomeworkAverage(number)));
                        } else if (parts[1].equals("Final") && parts.length >= 4) {
                            String numberStr = parts[2].substring(7);
                            int number = Integer.parseInt(numberStr);
                            System.out.println(formatAverage(scoreManager.showFinalProjectAverage(number)));
                        } else if (parts[1].equals("total")) {
                            String result = scoreManager.showTotal();
                            System.out.print(result);
                        } else {
                            System.out.println("指令輸入錯誤!請重新輸入");
                        }
                    } catch (Exception e) {
                        System.out.println("指令輸入錯誤!請重新輸入");
                    }
                    break;
                default:
                    System.out.println("指令輸入錯誤!請重新輸入");
            }
        }
    }

    //將傳入的double數值，依照作業要求的格式處理好後，再return回去
    public static String formatAverage(double avg){
        double rounded = Math.round(avg*100.0)/100.0;

        if(rounded == (int)rounded){
            return String.valueOf((int)rounded);
        }

        String formatted = String.format("%.2f",rounded);
        if(formatted.endsWith("0") && !formatted.endsWith(".0")){
            formatted = formatted.substring(0, formatted.length() - 1);
        }
        return formatted;
    }
}