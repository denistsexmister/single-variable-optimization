package ua.nure.motdo.first_brigade;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import java.util.function.Function;

public class Main {
    private static Function<Double, Double> function;

    private static String equation;
    private final static JEP equationSolver = new JEP();
    private final static DJep derivativeFinder = new DJep();

    static {
        equationSolver.addStandardConstants();
        equationSolver.addStandardFunctions();

        derivativeFinder.addStandardConstants();
        derivativeFinder.addStandardFunctions();
        derivativeFinder.addStandardDiffRules();
    }

    public static void main(String[] args) {
        equation = "3 * x^4 + (x - 1)^2";
        equationSolver.addVariable("x", 0);
        equationSolver.parseExpression(equation);
        function = (x) -> { equationSolver.addVariable("x", x); return equationSolver.getValue(); };
        double resultPoint = newtonRaphsonMethod(4, 0.0001);

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
        try {
            derivativeFinder.addVariable("x", 0);
            Node equationNode = derivativeFinder.parse(equation);

            Node firstDerivativeNode = derivativeFinder.differentiate(equationNode, "x");

            JEP tempSolver = new JEP();
            tempSolver.addVariable("x", x);
            tempSolver.parseExpression(derivativeFinder.toString(firstDerivativeNode));


            return tempSolver.getValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static double calculateSecondDerivativeInPoint(double x) {
        try {
            derivativeFinder.addVariable("x", 0);
            Node equationNode = derivativeFinder.parse(equation);

            Node firstDerivativeNode = derivativeFinder.differentiate(equationNode, "x");
            Node secondDerivativeNode = derivativeFinder.differentiate(firstDerivativeNode, "x");

            JEP tempSolver = new JEP();
            tempSolver.addVariable("x", x);
            tempSolver.parseExpression(derivativeFinder.toString(secondDerivativeNode));

            return tempSolver.getValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}