package ua.nure.motdo.first_brigade;

import java.util.function.Function;

public class Main {
    public static Function<Double, Double> function;
    public static Function<Double, Double> firstDerivative;
    public static Function<Double, Double> secondDerivative;

    public static void main(String[] args) {
//        1 function
//        function = (x) -> 3 * Math.pow(x, 4) + Math.pow(x - 1, 2);
//        firstDerivative = (x) -> 12 * Math.pow(x, 3) + 2 * (x - 1);
//        secondDerivative = (x) -> 36 * Math.pow(x, 2) + 2;
//        double resultPoint = newtonRaphsonMethod(0, 4, 0.0001);
//
//        System.out.println(resultPoint);

//        2 function
        function = (x) -> x / Math.log(x);
        firstDerivative = (x) -> (Math.log(x) - 1) / (Math.pow(Math.log(x), 2));
        secondDerivative = (x) -> -((Math.log(x) - 2) / (x * Math.pow(Math.log(x), 3)));
        double resultPoint = newtonRaphsonMethod(2, 4, 0.0001);

        System.out.println(resultPoint);
    }

    public static double newtonRaphsonMethod(double startPoint, double accuracy) {
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

    public static double newtonRaphsonMethod(double startIntervalPoint, double endIntervalPoint, double accuracy) {
        if ((calculateFunctionInPoint(startIntervalPoint) * calculateSecondDerivativeInPoint(startIntervalPoint)) > 0) {
            return newtonRaphsonMethod(startIntervalPoint, accuracy);
        }
        else if ((calculateFunctionInPoint(endIntervalPoint) * calculateSecondDerivativeInPoint(endIntervalPoint)) > 0) {
            return newtonRaphsonMethod(endIntervalPoint, accuracy);
        }
        else {
            throw new RuntimeException("Error in intervals");
        }
    }

    public static double calculateFunctionInPoint(double x) {
        return function.apply(x);
    }
    public static double calculateFirstDerivativeInPoint(double x) {
        return firstDerivative.apply(x);
    }
    public static double calculateSecondDerivativeInPoint(double x) {
        return secondDerivative.apply(x);
    }
}