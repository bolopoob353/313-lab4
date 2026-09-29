package mn.edu.must.sqat;

public class GradeCalculator {

    // 90+ -> A, 80-89 -> B, 70-79 -> C, 60-69 -> D, <60 -> F
    public String letterGrade(double score) {
        if (Double.isNaN(score) || score < 0 || score > 100) {
            throw new IllegalArgumentException("Оноо 0-100 хооронд байх ёстой: " + score);
        }
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        check(att, 10, "Ирц");
        check(lab, 40, "Лаб+бие даалт");
        check(quiz1, 10, "Сорил 1");
        check(quiz2, 10, "Сорил 2");
        check(exam, 30, "Шалгалт");
        return att + lab + quiz1 + quiz2 + exam;
    }

    private static void check(double value, double max, String name) {
        if (Double.isNaN(value) || value < 0 || value > max) {
            throw new IllegalArgumentException(
                name + " 0-" + max + " хооронд байх ёстой: " + value);
        }
    }
}
