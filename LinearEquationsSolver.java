import java.util.Scanner;

public class LinearEquationsSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Зчитування коефіцієнтів з клавіатури
        System.out.println("Введіть коефіцієнти системи рівнянь:");
        double[][] coefficients = new double[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                coefficients[i][j] = scanner.nextDouble();
            }
        }

        // Розв'язання системи рівнянь методом Гаусса
        for (int i = 0; i < 3; i++) {
            // Пошук максимального елементу в стовпці
            double maxElement = Math.abs(coefficients[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < 3; k++) {
                if (Math.abs(coefficients[k][i]) > maxElement) {
                    maxElement = Math.abs(coefficients[k][i]);
                    maxRow = k;
                }
            }

            // Обмін рядків, якщо потрібно
            for (int k = i; k < 4; k++) {
                double temp = coefficients[maxRow][k];
                coefficients[maxRow][k] = coefficients[i][k];
                coefficients[i][k] = temp;
            }

            // Прямий хід методу Гаусса
            for (int k = i + 1; k < 3; k++) {
                double c = -coefficients[k][i] / coefficients[i][i];
                for (int j = i; j < 4; j++) {
                    if (i == j) {
                        coefficients[k][j] = 0;
                    } else {
                        coefficients[k][j] += c * coefficients[i][j];
                    }
                }
            }
        }

        // Зворотній хід методу Гаусса
        double[] solution = new double[3];
        for (int i = 2; i >= 0; i--) {
            solution[i] = coefficients[i][3];
            for (int j = i + 1; j < 3; j++) {
                solution[i] -= coefficients[i][j] * solution[j];
            }
            solution[i] /= coefficients[i][i];
        }

        // Перевірка розв'язку
        boolean solutionCorrect = true;
        for (int i = 0; i < 3; i++) {
            double result = 0;
            for (int j = 0; j < 3; j++) {
                result += coefficients[i][j] * solution[j];
            }
            if (Math.abs(result - coefficients[i][3]) > 0.0001) {
                solutionCorrect = false;
                break;
            }
        }

        // Виведення розв'язку
        System.out.println("\nРозв'язок системи рівнянь:");
        for (int i = 0; i < 3; i++) {
            System.out.printf("x%d = %.2f%n", i+1, solution[i]);
        }
        System.out.println("\nПеревірка пройдена: " + solutionCorrect);

    }
}

