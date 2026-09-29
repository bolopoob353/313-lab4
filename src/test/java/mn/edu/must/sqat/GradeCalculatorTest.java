package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(90.0);
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (A-гийн хязгаараас бага зэрэг доор)")
    void justBelowNinetyIsB() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(89.99);
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой")
    void sixtyIsExactlyD() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(60.0);
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой")
    void justBelowSixtyIsF() {
        GradeCalculator calc = new GradeCalculator();
        String grade = calc.letterGrade(59.99);
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("0 ба 100 захын утгууд хүчинтэй: 0 -> F, 100 -> A")
    void extremesAreValid() {
        GradeCalculator calc = new GradeCalculator();
        String low = calc.letterGrade(0);
        String high = calc.letterGrade(100);
        assertAll(
            () -> assertEquals("F", low),
            () -> assertEquals("A", high)
        );
    }

    @Test
    @DisplayName("letterGrade: -1 ба 101 оноонд IllegalArgumentException шиддэг")
    void letterGradeRejectsOutOfRange() {
        GradeCalculator calc = new GradeCalculator();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101))
        );
    }

    @Test
    @DisplayName("totalScore: дээд оноонууд (10,40,10,10,30) нийлээд 100 болно")
    void totalScoreMaximum() {
        GradeCalculator calc = new GradeCalculator();
        double total = calc.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, total, 1e-9);
    }

    @Test
    @DisplayName("totalScore: сөрөг ирц (att = -5) exception шиддэг")
    void totalScoreRejectsNegative() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class,
            () -> calc.totalScore(-5, 30, 8, 8, 25));
    }

    @Test
    @DisplayName("totalScore: лаб 41 (дээд хязгаараас хэтэрсэн) exception шиддэг")
    void totalScoreRejectsOverMax() {
        GradeCalculator calc = new GradeCalculator();
        assertThrows(IllegalArgumentException.class,
            () -> calc.totalScore(10, 41, 10, 10, 30));
    }
}
