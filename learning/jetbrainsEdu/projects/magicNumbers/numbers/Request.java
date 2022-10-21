package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Request {
    private String[] parts;

    private List<String> invalidProperties;

    private List<String[]> mutuallyExclusiveProperties;

    public static List<String> propertiesList = List.of(("BUZZ, DUCK, JUMPING, PALINDROMIC, GAPFUL, SPY, " +
                            "EVEN, ODD, HAPPY, SAD, SQUARE, SUNNY").split(", "));
    public static String propertiesString = "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, " +
                            "SQUARE, HAPPY, SAD, JUMPING, SUNNY, EVEN, ODD]\n";

    public Request(String input) {
        this.parts = input.split("\\s+");
        identifyRequest();
    }

    private void identifyRequest(){
        switch (this.parts.length) {
            case 1:
                oneNumber(); break;
            case 2:
                listNumbers(); break;
            default:
                withProperties(); break;
        }
    }

    private void oneNumber() {
        long number = getNumber(this.parts[0]);
        if (number > 0) {
            new Number(number).printCard();
        } else {
            printErrorMessage(1);
        }
    }

    private void printErrorMessage(int level) {
        switch (level) {
            case 1:
                System.out.println("The first parameter should be a natural number or zero.\n");
                break;
            case 2:
                System.out.println("The second parameter should be a natural number.\n");
                break;
            case 3:
                setInvalidProperties();

                if (invalidProperties.size() == 1) {
                    System.out.printf("The property [%s] is wrong.\n", invalidProperties.get(0).toUpperCase());
                    System.out.println(propertiesString + "\n");
                } else {
                    StringBuilder str = new StringBuilder("The properties [");
                    for (int i = 0; i < invalidProperties.size(); i++) {
                        str.append(invalidProperties.get(i).toUpperCase()).append(", ");
                    }
                    str = new StringBuilder(str.substring(0, str.length() - 2));
                    str.append("] are wrong.");
                    System.out.println(str.append(propertiesString));
                }
                break;
            case 4:
                setMutuallyExclusiveProperties();

                StringBuilder str = new StringBuilder("The request contains mutually exclusive properties: ");

                for (int i = 0; i < mutuallyExclusiveProperties.size(); i++) {
                    String[] properties = mutuallyExclusiveProperties.get(i);
                    str.append("[").append(properties[0].toUpperCase()).append(", ")
                            .append(properties[1].toUpperCase()).append("] ");
                }

                System.out.println(str.append("\n").append(propertiesString));

                break;
        }
    }

    private void listNumbers() {
        long first = getNumber(this.parts[0]);
        long second = getNumber(this.parts[1]);
        if (first >= 0 && second > 0) {
            NumberList list = new NumberList(first, second);
            list.printListCard();
        } else if (first < 0) {
            printErrorMessage(1);
        } else {
            printErrorMessage(2);
        }
    }

    private void withProperties() {
        long first = getNumber(this.parts[0]);
        long second = getNumber(this.parts[1]);

        if (first >= 0 && second > 0 &&
                arePropertyValid(Arrays.copyOfRange(this.parts, 2, this.parts.length)) &&
                !mutuallyExclusiveProperties(Arrays.copyOfRange(this.parts, 2, this.parts.length))) {
            NumberList list = new NumberList(first, second, Arrays.copyOfRange(this.parts, 2, this.parts.length));
            list.printListCard();
        } else if (first < 0) {
            printErrorMessage(1);
        } else if (second < 1) {
            printErrorMessage(2);
        } else if (!arePropertyValid(Arrays.copyOfRange(this.parts, 2, this.parts.length))) {
            printErrorMessage(3);
        } else {
            printErrorMessage(4);
        }
    }

    private boolean mutuallyExclusiveProperties(String... properties) {
        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties.length; j++){
                if (i == j) {
                    continue;
                }
                if (mutualExclusive(properties[i], properties[j])) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean mutualExclusive(String prop1, String prop2) {

        boolean bothExclusive = prop1.charAt(0) == '-' && prop2.charAt(0) == '-';

        boolean sameType = bothExclusive || prop1.charAt(0) != '-' && prop2.charAt(0) != '-';

        if (prop1.charAt(0) == '-') {
            prop1 = prop1.substring(1);
        }

        if (prop2.charAt(0) == '-') {
            prop2 = prop2.substring(1);
        }

        if (!sameType && prop1.equals(prop2)) {
            return true;
        }

        return sameType &&
                (prop1.equals("even") && prop2.equals("odd") ||
                prop1.equals("odd") && prop2.equals("even") ||
                prop1.equals("duck") && prop2.equals("spy") ||
                prop1.equals("spy") && prop2.equals("duck") ||
                prop1.equals("square") && prop2.equals("sunny") ||
                prop1.equals("sunny") && prop2.equals("square") ||
                prop1.equals("happy") && prop2.equals("sad"));
    }

    private void setMutuallyExclusiveProperties() {
        mutuallyExclusiveProperties = new ArrayList<>();

        String[] properties = Arrays.copyOfRange(this.parts, 2, this.parts.length);

        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties.length; j++){
                if (i == j) {
                    continue;
                }
                if (mutualExclusive(properties[i], properties[j])) {
                    mutuallyExclusiveProperties.add(new String[]{properties[i], properties[j]});
                }
            }
        }
    }



    private long getNumber(String part) {
        try {
            long number = Long.parseLong((part));
            return number;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void setInvalidProperties() {
        invalidProperties = new ArrayList<>();

        List<String> properties = List.of(Arrays.copyOfRange(this.parts, 2, this.parts.length));

        for (String prop : properties) {
            if (!isValidProperty(prop)) {
                invalidProperties.add(prop);
            }
        }
    }

    private boolean arePropertyValid(String... properties) {
        for (String property: properties) {
            if (!isValidProperty(property)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidProperty(String part) {
        if (part.charAt(0) != '-') {
            return this.propertiesList.contains(part.toUpperCase());
        } else {
            return this.propertiesList.contains(part.substring(1).toUpperCase());
        }
    }
}