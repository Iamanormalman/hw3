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

    public boolean setIndividualScore(int index, int score) {
        if (index >= 0 && index < scores.length && score >= 0 && score <= 100) {
            scores[index] = score;
            return true;
        }
        return false;
    }
}