package numbers;

import java.util.ArrayList;
import java.util.List;

public class NumberList {
    private List<Number> list;
    private long start;
    private long length;
    private String[] properties;
    public NumberList(long start, long length, String... properties) {
        this.start = start;
        this.length = length;
        this.properties = properties;
        this.list = new ArrayList<>();
        setNumberList();
    }
    private void setNumberList() {
        if (this.properties.length == 0) {
            for (long i = this.start; i < this.start + this.length; i++) {
                list.add(new Number(i));
            }
        } else {
            long j = this.start;
            while (list.size() < this.length) {
                Number number = new Number(j);
                if (number.hasAllProperties(properties)) {
                    list.add(number);
                }
                j++;
            }
        }
    }

    public void printListCard() {
        this.list.forEach(Number::printLine);
        System.out.println();
    }

}