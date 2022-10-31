package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Problem1 {
    private static final char ZERO = '0';
    private static final int POBI = 1;
    private static final int CRONG = 2;
    private static final int TIE = 0;
    private static final int EXCEPTION = -1;

    private static final int FIRST_PAGE = 1;
    private static final int LAST_PAGE = 400;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if (isInValidPageNumber(pobi, crong)) {
            return EXCEPTION;
        }

        List<List<Integer>> pobiDigits = getEachDigitOfTwoPages(pobi);
        Integer pobiScore = getScore(pobiDigits);

        List<List<Integer>> crongDigits = getEachDigitOfTwoPages(crong);
        Integer crongScore = getScore(crongDigits);

        return getWinner(pobiScore, crongScore);
    }

    private static boolean isInValidPageNumber(List<Integer> pobi, List<Integer> crong) {
        return isInValidPageNumber(pobi) || isInValidPageNumber(crong);
    }

    public static List<List<Integer>> getEachDigitOfTwoPages(List<Integer> pages) {
        List<List<Integer>> digitsOfTwoPages = new ArrayList<>();

        for (Integer page : pages) {
            digitsOfTwoPages.add(getEachDigit(page));
        }

        return digitsOfTwoPages;
    }

    public static List<Integer> getEachDigit(Integer page) {
        List<Integer> digits = new ArrayList<>();
        String pageString = page.toString();

        for (int i = 0; i < pageString.length(); i++) {
            digits.add(pageString.charAt(i) - ZERO);
        }

        return digits;
    }

    public static Integer getScore(List<List<Integer>> digitsOfTwoPages) {
        List<Integer> num = new ArrayList<>();

        for (List<Integer> digits : digitsOfTwoPages) {
            num.add(plusEachDigit(digits));
            num.add(multiplyEachDigit(digits));
        }

        return getMax(num);
    }

    public static Integer plusEachDigit(List<Integer> digits) {
        int sum = 0;
        for (Integer digit : digits) {
            sum += digit;
        }

        return sum;
    }

    public static Integer multiplyEachDigit(List<Integer> digits) {
        Integer sum = 1;
        for (Integer digit : digits) {
            sum *= digit;
        }

        return sum;
    }

    public static Integer getMax(List<Integer> num) {
        return Collections.max(num);
    }

    public static Integer getWinner(Integer pobiScore, Integer crongScore) {
        if (pobiScore > crongScore) {
            return POBI;
        }
        if (pobiScore < crongScore) {
            return CRONG;
        }

        return TIE;
    }

    public static boolean isInValidPageNumber(List<Integer> pages) {
        if (isValidPageRange(pages)) {
            return false;
        }
        if (isValidPageOddEven(pages)) {
            return false;
        }
        if (isValidPageSequence(pages)) {
            return false;
        }

        return true;
    }

    public static boolean isValidPageRange(List<Integer> pages) {
        for (Integer page : pages) {
            if (page < FIRST_PAGE || page > LAST_PAGE) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPageOddEven(List<Integer> pages) {
        int leftPage = pages.get(LEFT);
        int rightPage = pages.get(RIGHT);

        if (isEven(leftPage)) {
            return false;
        }
        if (!isEven(rightPage)) {
            return false;
        }

        return true;
    }

    private static boolean isEven(int num) {
        return (num % 2) == 0;
    }

    public static boolean isValidPageSequence(List<Integer> pages) {
        int leftPage = pages.get(LEFT);
        int rightPage = pages.get(RIGHT);

        if (++leftPage != rightPage) {
            return false;
        }

        return true;
    }
}