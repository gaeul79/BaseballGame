package Common;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 야구 게임에서 자주 사용되는 함수들을 제공하는 클래스입니다.
 * 숫자 검증, 랜덤 숫자 생성 등의 기능을 포함합니다.
 *
 * @author 김현정
 */
public class NumberUtil {
    private static final String NUMBER_REG = "-?[0-9]*\\.?[0-9]*";

    /**
     * 입력받은 문자열이 숫자인지 검사하고, 숫자일 경우 double 타입으로 변환하여 반환합니다.
     *
     * @param strNum 검사할 문자열
     * @return 숫자로 변환된 값
     * @throws InvalidTypeInputException 입력된 문자열이 숫자가 아닐 경우 발생
     * @author 김현정
     */
    public static double parseNumber(String strNum) throws InvalidTypeInputException {
        if (Pattern.matches(NUMBER_REG, strNum)) {
            return Double.parseDouble(strNum);
        } else {
            throw new InvalidTypeInputException(strNum, "숫자", null);
        }
    }

    /**
     * 입력된 문자열이 숫자 야구 게임 규칙에 맞는 형식인지 검사합니다.
     *
     * @param strInputNum 입력받은 숫자 문자열
     * @param level       숫자 야구 게임의 난이도 (숫자의 자릿수)
     * @return 숫자 리스트
     * @throws InvalidTypeInputException 입력된 문자열이 유효하지 않은 경우 발생
     * @author 김현정
     */
    public static List<Integer> parseBaseballNumber(String strInputNum, int level) throws InvalidTypeInputException {
        String correctValueMsg = level + "자리의 " + "1~9사이 숫자";
        String baseballNumberReg = "[1-9]{" + level + "}";
        if (!Pattern.matches(baseballNumberReg, strInputNum)) {
            throw new InvalidTypeInputException(strInputNum, correctValueMsg, null);
        }

        int inputNum = Integer.parseInt(strInputNum);
        List<Integer> inputNumbers = NumberUtil.ConvertIntToIntegerList(inputNum);
        if (inputNumbers.size() != inputNumbers.stream().distinct().count()) {
            throw new InvalidTypeInputException(strInputNum, correctValueMsg, "중복된 숫자가 있습니다.");
        } else {
            return inputNumbers;
        }
    }

    /**
     * 지정된 범위 내의 랜덤한 정수를 생성합니다.
     *
     * @param min 최솟값 (포함)
     * @param max 최댓값 (포함)
     * @return 생성된 랜덤 정수
     * @author 김현정
     */
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * 주어진 숫자가 지정된 범위 내에 있는지 확인합니다.
     *
     * @param min 범위의 최솟값
     * @param max 범위의 최댓값
     * @param num 확인할 숫자
     * @return num이 min 이상이고 max 이하이면 true, 아니면 false 반환.
     * @throws InvalidTypeInputException 범위를 벗어난 숫자 입력시 발생
     * @author 김현정
     */
    public static boolean isInRange(int min, int max, int num) throws InvalidTypeInputException {
        if (num >= min && num <= max)
            return true;
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(min);
            sb.append("~");
            sb.append(max);
            sb.append(")");
            sb.append("사이의 값");
            throw new InvalidTypeInputException(num, sb.toString(), "잘못된 입력입니다! " + sb + "을 입력해주세요.");
        }
    }

    /**
     * 정수 값을 각 자리 숫자로 분리하여 리스트에 담아 반환하는 메소드입니다.
     *
     * @param number 분리할 정수 값
     * @return 분리된 숫자들의 리스트 (Integer 타입)
     * @author 김현정
     */
    public static List<Integer> ConvertIntToIntegerList(int number) {
        int[] numbers = Integer.toString(number)
                .chars()
                .map(num -> Integer.parseInt(Character.toString(num)))
                .toArray();
        return Arrays.stream(numbers).boxed().toList();
    }
}
