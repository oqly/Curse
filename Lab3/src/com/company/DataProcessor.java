package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataProcessor<T extends ArtUnit> {

    private List<T> list;
    public int lowerRangeValue;
    public int upperRangeValue;
    public void setList(List<T> list){
        this.list = list;
    }

    public List<T> processing(int lowerRange, int upperRange) throws myException{
        if (lowerRange > upperRange){
            lowerRangeValue = lowerRange;
            upperRangeValue = upperRange;
            throw new myException(lowerRange);
        }
        return list.stream()
                .filter(ArtUnit -> (ArtUnit.getInfo() >= lowerRange) && (ArtUnit.getInfo() <= upperRange))
                .limit(3)
                .sorted(Comparator.comparing(ArtUnit::getInfo))
                .collect(Collectors.toList());
    }

    public ArtUnit matching(int match){
        Optional<T> elementOptional = list.stream()
                .filter(ArtUnit -> (ArtUnit.getInfo() == match))
                .findFirst();
        ArtUnit artUnit = null;
        if (elementOptional.isPresent()) {
            artUnit = elementOptional.get();
        }
        return artUnit;
    }

}
