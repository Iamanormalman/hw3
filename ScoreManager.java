import java.util.ArrayList;

public class ScoreManager {
    private ArrayList<Student> students;

    public ScoreManager(){
        students = new ArrayList<>();
    }

    public void addStudent(String name, int[] scores){
        Student name = new Student(name, scores);
        students.add(name);
    }

    public void deleteStudent(String name){

    }

    public int[] showIndividualScore(String name){

    }

    public double showIndividualAverage(String name){

    }

    public double showHomeworkAverage(int number){

    }

    public double showFinalProjectAverage(int number){

    }

    public String showTotal(){

    }
}