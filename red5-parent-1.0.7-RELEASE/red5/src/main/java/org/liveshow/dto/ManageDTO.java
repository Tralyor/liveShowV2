package org.liveshow.dto;

import java.util.List;

public class ManageDTO {
    private List<String> className;
    private List<Integer> personNums;
    private List<Integer> factNums;
    private List<Double> rates;

    public List<String> getClassName() {
        return className;
    }

    public void setClassName(List<String> className) {
        this.className = className;
    }

    public List<Integer> getPersonNums() {
        return personNums;
    }

    public void setPersonNums(List<Integer> personNums) {
        this.personNums = personNums;
    }

    public List<Integer> getFactNums() {
        return factNums;
    }

    public void setFactNums(List<Integer> factNums) {
        this.factNums = factNums;
    }

    public List<Double> getRates() {
        return rates;
    }

    public void setRates(List<Double> rates) {
        this.rates = rates;
    }
}
