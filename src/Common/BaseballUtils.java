package Common;

import java.util.regex.Pattern;

/**
 * 야구 게임에서 자주 사용되는 유틸리티 함수들을 제공하는 클래스입니다.
 * 숫자 검증, 랜덤 숫자 생성 등의 기능을 포함합니다.
 *
 * @author 김현정
 */
public class BaseballUtils {
    private static final String NUMBER_REG = "-?[0-9]*\\.?[0-9]*";

    /**
     * 입력받은 문자열이 숫자인지 검사하고, 숫자일 경우 double 타입으로 변환하여 반환합니다.
     *
     * @param strNum 검사할 문자열
     * @return 숫자로 변환된 값
     * @throws BadInputException 입력된 문자열이 숫자가 아닐 경우 발생
     * @author 김현정
     */
    public static double parseNumber(String strNum) throws BadInputException {
        if (Pattern.matches(NUMBER_REG, strNum)) {
            return Double.parseDouble(strNum);
        } else {
            throw new BadInputException("숫자");
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
     * @throws BadInputException 범위를 벗어난 숫자 입력시 발생
     * @return num이 min 이상이고 max 이하이면 true, 아니면 false를 반환합니다.
     */
    public static boolean isInRange(int min, int max, int num) throws BadInputException {
        if(num >= min && num <= max)
            return true;
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(min);
            sb.append("~");
            sb.append(max);
            sb.append(")");
            sb.append("사이의 값");
            throw new BadInputException(sb.toString());
        }
    }
}
