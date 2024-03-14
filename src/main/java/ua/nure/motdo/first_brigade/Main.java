package ua.nure.motdo.first_brigade;

public class Main {
    public static Function firstDerivative;
    public static Function secondDerivative;

    public static void main(String[] args) {
//        firstDerivative = (x) -> 12 * Math.pow(x, 3) + 2 * (x - 1);
//        secondDerivative = (x) -> 36 * Math.pow(x, 2) + 2;
//        double resultPoint = findMinimumOfFunc(4, 0.0001);

//        System.out.println(resultPoint);

        firstDerivative = (x) -> 12 * Math.pow(x, 3) + 2 * (x - 1);
        secondDerivative = (x) -> 36 * Math.pow(x, 2) + 2;
        double resultPoint = findMinimumOfFunc(4, 0.0001);

        System.out.println(resultPoint);
    }

    public static double findMinimumOfFunc(double startPoint, double accuracy) {
        double x = startPoint;
        double nextX;
        do {
            nextX = x - (calculateFirstDerivativeInPoint(x) /
                    calculateSecondDerivativeInPoint(x));
            x = nextX;
            System.out.println(x + "-" + calculateFirstDerivativeInPoint(x));
        } while (Math.abs(calculateFirstDerivativeInPoint(nextX)) >= accuracy);

        return nextX;
    }

    public static double calculateFirstDerivativeInPoint(double x) {
        return firstDerivative.function(x);
    }

    public static double calculateSecondDerivativeInPoint(double x) {
        return secondDerivative.function(x);
    }
}