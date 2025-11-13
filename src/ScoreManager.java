import java.util.ArrayList;
import java.util.Iterator;

public class ScoreManager {
    private ArrayList<Student> students;

    public ScoreManager(){
        students = new ArrayList<>();
    }

    public boolean addStudent(String name, int[] scores){
        if(findStudent(name)!=null){return false;}
        students.add(new Student(name, scores));
        return true;
    }

    public boolean deleteStudent(String name){
        Iterator<Student> iterator = students.iterator();

        while(iterator.hasNext()){
            Student s = iterator.next();
            if(s.getName().equals(name)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public int[] showIndividualScore(String name){
        for(Student s:students){
            if(s.getName().equals(name)){
                return s.getScores();
            }
        }
        return null;
    }

    public double showIndividualAverage(String name){
        int sum = 0;
        for(Student s:students){
            if(s.getName().equals(name)){
                for(int i:s.getScores()){
                    sum += i;
                }
                return (double)sum/s.getScores().length;
            }
        }
        return -1;
    }

    public double showHomeworkAverage(int number){
        int sum = 0;
        for(Student s:students){
            sum += s.getScores()[number-1];
        }
        return (double)sum/students.size();
    }

    public double showFinalProjectAverage(int number){
        int sum = 0;
        for(Student s:students){
            sum += s.getScores()[number+2];
        }
        return (double)sum/students.size();

    }

    public String showTotal(){
        if (students.isEmpty()) {
            return "目前系統中沒有學生資料";
        }

        String result = "";
        result += "========================================================================================================================\n";
        // 標題行 - 使用固定寬度
        result += String.format("%-11s %-9s %-9s %-8s %-9s %-12s\n",
                "學生姓名", "作業1", "作業2", "作業3", "專案1", "專案2");
        result += "========================================================================================================================\n";

        for(Student s : students){
            int[] scores = s.getScores();
            // 資料行 - 數字靠右對齊比較好看
            result += String.format("%-15s %-10d %-10d %-10d %-10d %-10d\n",
                    s.getName(),
                    scores[0], scores[1], scores[2], scores[3], scores[4]);
        }
        result += "========================================================================================================================\n";
        result += "統計資訊：\n";
        result += "  學生總數：" + students.size() + " 人\n";
        result += "  全班總平均：" + String.format("%.2f", getOverallAverage()) + "\n";
        result += "========================================================================================================================\n";

        return result;
    }

    private Student findStudent(String name){
        for(Student s:students){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    public boolean updateScore(String name, String subject, int score) {
        Student student = findStudent(name);
        if (student == null) {
            return false;
        }

        int index = -1;
        if (subject.equals("Homework1")) {
            index = 0;
        } else if (subject.equals("Homework2")) {
            index = 1;
        } else if (subject.equals("Homework3")) {
            index = 2;
        } else if (subject.equals("FinalProject1") || subject.equals("Final Project1")) {
            index = 3;
        } else if (subject.equals("FinalProject2") || subject.equals("Final Project2")) {
            index = 4;
        }

        if (index == -1) {
            return false;
        }

        return student.setIndividualScore(index, score);
    }

    public int getStudentCount() {
        return students.size();
    }

    public double getOverallAverage() {
        if (students.isEmpty()) {
            return 0;
        }

        int totalSum = 0;
        int totalCount = 0;

        for (Student s : students) {
            for (int score : s.getScores()) {
                totalSum += score;
                totalCount++;
            }
        }

        return (double)totalSum / totalCount;
    }
}