import java.util.ArrayList;

public class ScoreManager {
    private ArrayList<Student> students;

    public ScoreManager(){
        students = new ArrayList<>();
    }

    public void addStudent(String name, int[] scores){
        students.add(new Student(name, scores));
    }

    public void deleteStudent(String name){
        for(Student s:students){
            if(s.getName().equals(name)){
                students.remove(s);
                break;
            }
        }
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
        String result = "";
        for(Student s:students){
            result += s.getName();
            for(int score:s.getScores()){
                result += " " + score;
            }
            result += "\n";
        }
        return result;
    }
}