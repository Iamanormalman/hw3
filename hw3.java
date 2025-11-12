import java.io.File;
import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.out.println("只能有一個檔案");
            System.exit(0);
        }

        ScoreManager scoreManager = new ScoreManager();

        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String[] str = scanner.nextLine().split("\\s+");
            String name = str[0];
            int[] scores = new int[5];
            for(int i=1;i<6;i++){
                scores[i-1] = Integer.parseInt(str[i]);
            }
            scoreManager.addStudent(name, scores);
        }
    }
}