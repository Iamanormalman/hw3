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
            }
        }
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