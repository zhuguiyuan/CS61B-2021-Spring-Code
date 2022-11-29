package tester;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    /* Randomized test for addFirst, addLast, removeFirst, and removeLast. */
    public void randTest() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        StringBuilder opSeries = new StringBuilder();
        int N = 10000;
        int LB = 0, UB = 100;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                int operandNumber = StdRandom.uniform(LB, UB);
                solution.addFirst(operandNumber);
                student.addFirst(operandNumber);
                opSeries.append(String.format("addFirst(%s)\n", operandNumber));
            } else if (operationNumber == 1) {
                int operandNumber = StdRandom.uniform(LB, UB);
                solution.addLast(operandNumber);
                student.addLast(operandNumber);
                opSeries.append(String.format("addLast(%s)\n", operandNumber));
            } else if (operationNumber == 2) {
                if (!solution.isEmpty()) {
                    opSeries.append("removeFirst()\n");
                    assertEquals(opSeries.toString(),
                            solution.removeFirst(), student.removeFirst());
                }
            } else if (operationNumber == 3) {
                if (!solution.isEmpty()) {
                    opSeries.append("removeLast()\n");
                    assertEquals(opSeries.toString(),
                            solution.removeLast(), student.removeLast());
                }
            }
        }
    }
}
