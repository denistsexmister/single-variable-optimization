package ua.nure.motdo.first_brigade;

import java.util.function.Function;

public class Main {
    public static Function<Double, Double> firstDerivative;
    public static Function<Double, Double> secondDerivative;

    public static void main(String[] args) {
//        1 function
//        firstDerivative = (x) -> 12 * Math.pow(x, 3) + 2 * (x - 1);
//        secondDerivative = (x) -> 36 * Math.pow(x, 2) + 2;
//        double resultPoint = findMinimumOfFunc(4, 0.0001);

//        System.out.println(resultPoint);

//        2 function
        firstDerivative = (x) -> (Math.log(x) - 1) / (Math.pow(Math.log(x), 2));
        secondDerivative = (x) -> -((Math.log(x) - 2) / (x * Math.pow(Math.log(x), 3)));
        double resultPoint = findMinimumOfFunc(4, 0.0001);

        //System.out.println(resultPoint);// TODO: implement bounded newton method
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
        return firstDerivative.apply(x);
    }

    public static double calculateSecondDerivativeInPoint(double x) {
        return secondDerivative.apply(x);
    }
}