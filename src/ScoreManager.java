import java.util.ArrayList;

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
        for(Student s:students){
            if(s.getName().equals(name)){
                students.remove(s);
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

    private Student findStudent(String name){
        for(Student s:students){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }
}