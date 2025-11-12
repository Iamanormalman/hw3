public class Student {
    private String name;
    private int[] scores;

    public Student(String name, int[] scores){
        this.name = name;
        this.scores = scores.clone();
    }

    public String getName() {
        return name;
    }

    public int[] getScores() {
        return scores.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int[] scores) {
        this.scores = scores.clone();
    }
}