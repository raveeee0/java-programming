package numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Number {
    private final long number;
    private List<Integer> digits;
    private final Map<String, Boolean> properties;

    Number(long number) {
        this.number = number;
        setDigits();

        this.properties = new HashMap<String, Boolean>();
        setProperties();
    }
    private void setProperties() {
        properties.put("even", isEven());
        properties.put("odd", !isEven());
        properties.put("buzz", isBuzz());
        properties.put("duck", isDuck());
        properties.put("palindromic", isPalindromic());
        properties.put("gapful", isGapful());
        properties.put("spy", isSpy());
        properties.put("square", isSquare(this.number));
        properties.put("sunny", isSunny());
        properties.put("jumping", isJumping());
        properties.put("sad", isSad());
        properties.put("happy", isHappy());
    }
    public boolean getProperty(String property) {
        return this.properties.get(property.toLowerCase());
    }
    private void setDigits() {
        this.digits = new ArrayList<>();
        long num = this.number;
        while (num > 0) {
            digits.add(0, (int) (num % 10));
            num /= 10;
        }
    }
    public List<Integer> getDigits() {
        return this.digits;
    }
    private int getNumberDigits() {
        return getDigits().size();
    }
    private int getDigit(int fromLeft) {
        return this.digits.get(fromLeft);
    }
    private boolean isPalindromic() {
        int numDigits = getNumberDigits();
        for (int i = 0; i < numDigits / 2; i++) {
            int left = getDigit(i);
            int right = getDigit(numDigits - 1 - i);
            if (left != right) {
                return false;
            }
        }
        return true;
    }
    private boolean isDuck() { return this.digits.contains(0); }
    private boolean divisibleBySeven() {
        return number % 7 == 0;
    }
    private boolean endsWithSeven() {
        return number % 10 == 7;
    }
    private boolean isBuzz() {
        return divisibleBySeven() || endsWithSeven();
    }
    private boolean isEven() {
        return number % 2 == 0;
    }
    private boolean isGapful() {
        if (getNumberDigits() < 3) {
            return false;
        }
        int gap = 10 * getDigit(0) + getDigit(getNumberDigits() - 1);
        return number % gap == 0;
    }
    private boolean isSpy() {
        int sumOfDigits = getDigits().stream().reduce(0, Integer::sum);
        int productOfDigits = getDigits().stream().reduce(1, (a, b) -> a * b);
        return sumOfDigits == productOfDigits;
    }
    private boolean isSquare(long number) {
        return Math.sqrt(number) == Math.floor(Math.sqrt(number)); }
    private boolean isSunny() { return isSquare(this.number + 1); }

    private boolean isJumping() {
        for (int i = 0; i < this.getNumberDigits() - 1; i++) {
            if (Math.abs(this.getDigit(i) - this.getDigit(i + 1)) != 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isSad() {
        return !isHappy();
    }

    private boolean isHappy() {
        long result = number;

        while (result != 1 && result != 4) {
            result = numSquareSum(result);
        }

        if (result == 1) {
            return true;
        }

        return false;

    }

    private static long numSquareSum(long n) {
        long sum = 0;

        while (n > 0) {
            long m = n % 10;
            sum += m * m;
            n /= 10;
        }

        return sum;
    }

    public boolean hasAllProperties(String... propertiesToCheck) {
        for (String prop : propertiesToCheck) {
            if (prop.charAt(0) == '-') {
                if (properties.get(prop.substring(1).toLowerCase())) {
                    return false;
                }
            } else {
                if (!properties.get(prop.toLowerCase())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printCard() {
        StringBuilder card = new StringBuilder("Properties of " + this.number + "\n");
        for (String property: this.properties.keySet()) {
            card.append("\t").append(property).append(": ").append(properties.get(property)).append("\n");
        }
        System.out.println(card);
    }
    public void printLine() {
        StringBuilder line = new StringBuilder(this.number + " is");
        for (String property: this.properties.keySet()) {
            if (this.properties.get(property)) {
                line.append(" ").append(property).append(',');
            }
        }
        if (line.charAt(line.length() - 1) == ',') {
            line.delete(line.length() - 1, line.length());
        }
        System.out.println(line);
    }
}