package ru.oqly;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataProcessor<T extends Operation> {
    private List<T> list;

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> processing(int lowerRange, int upperRange) throws MyException {
        if (lowerRange > upperRange){
            throw new MyException(lowerRange, upperRange);
        }
        return list.stream()
                .filter(operation -> (operation.getTimeop() >= lowerRange) & (operation.getTimeop() <= upperRange))
                .limit(2)
                .sorted(Comparator.comparing(Operation::getTimeop).reversed())
                .collect(Collectors.toList());
    }

    public Operation matching(int match) throws MyNullException {
        Optional<T> matchingOperation = list.stream()
                .filter(operation -> (operation.getTimeop() == match))
                .findFirst();
        Operation matched;
        if (matchingOperation.isPresent()) {
            matched = matchingOperation.get();
        }
        else {
            throw new MyNullException(match);
        }
        return matched;
    }
}