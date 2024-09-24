package Common;

import java.util.Scanner;

/**
 * 사용자 입력을 받아 처리하는 유틸리티 클래스입니다.
 *
 * @author 김현정
 */
public class InputHelper {

    /**
     * 사용자에게 입력을 요청하고, 입력받은 값을 정수로 변환하여 반환합니다.
     *
     * @param prompt 사용자에게 표시될 입력 메시지
     * @return 사용자가 입력한 정수
     * @throws InvalidTypeInputException 입력 값이 숫자가 아닐 경우 발생
     * @author 김현정
     */
    public static int inputNumber(String prompt) throws InvalidTypeInputException {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        return (int) NumberUtil.parseNumber(sc.nextLine());
    }

    /**
     * 사용자에게 입력을 요청하고, 입력받은 문자열을 그대로 반환합니다.
     *
     * @param prompt 사용자에게 표시될 입력 메시지
     * @return 사용자가 입력한 문자열
     * @author 김현정
     */
    public static String input(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        return sc.nextLine();
    }
}
