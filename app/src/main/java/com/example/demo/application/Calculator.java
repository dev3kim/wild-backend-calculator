package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.CalculationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
    private final CalculationRepository calculationRepository = CalculationRepository.getInstance();

    private final Map<String, Operator> operators = new HashMap<>();

    public Calculator() {
        operators.put("+", new OperatorPlus());
        operators.put("-", new OperatorMinus());
        operators.put("*", new OperatorMultiply());
        operators.put("/", new OperatorDivide());
    }

    public Calculation caculate(int number1, int number2, String operatorSymbol) {
        Operator operator = operators.get(operatorSymbol);

        Calculation calculation = new Calculation(operatorSymbol, number1, number2, operator.calculate(number1, number2));

        calculationRepository.add(calculation);

        return calculation;
    }

    public List<Calculation> getCalculationList() {
        return calculationRepository.getAll();
    }
}
