package ru.oqly;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {
    private final DataProcessor<Operation> operationStr = new DataProcessor<>();

    @org.junit.jupiter.api.Test
    void processing() throws MyException {
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(new ComplicatedOperation(1,10, 100));
        operations.add(new ComplicatedOperation(2,5, 20));
        operations.add(new ComplicatedOperation(3,15, 100));
        operations.add(new ComplicatedOperation(4,30, 300));
        operations.add(new ComplicatedOperation(5,25, 400));
        operations.add(new SimpleOperation(1,20));
        operations.add(new SimpleOperation(2,10));
        operations.add(new SimpleOperation(3,15));

        operationStr.setList(operations);
        List<Operation> processed = operationStr.processing(5, 5);
        String actual = processed.get(0).getInfo(1);
        String expected = "Сложная операция 2: общее время - 5, общая стоимость - 20, Количество повторений - 1\n";

        assertEquals(expected, actual);
        assertThrows(MyException.class, () -> {
            operationStr.processing(15, 10);
        });
    }

    @org.junit.jupiter.api.Test
    void matching() throws MyNullException {
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(new ComplicatedOperation(1,10, 100));
        operations.add(new ComplicatedOperation(2,5, 20));
        operations.add(new ComplicatedOperation(3,15, 100));
        operations.add(new ComplicatedOperation(4,30, 300));
        operations.add(new ComplicatedOperation(5,25, 400));
        operations.add(new SimpleOperation(1,20));
        operations.add(new SimpleOperation(2,10));
        operations.add(new SimpleOperation(3,15));

        operationStr.setList(operations);
        String actual = operationStr.matching(20).getInfo(1);
        String expected = "Простая операция 1: общее время - 20, Количество повторений - 1\n";

        assertEquals(expected, actual);
        assertThrows(MyNullException.class, () ->{
            operationStr.matching(21);
        });
    }
}