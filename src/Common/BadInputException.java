package Common;

/**
 * 입력 관련 예외처리 클래스
 *
 * @author 김현정
 */
public class BadInputException extends Throwable {

    /**
     * 잘못된 입력이 발생했을 때 던지는 예외 클래스입니다.
     *
     * @param type 잘못된 입력의 종류
     * @author 김현정
     */
    public BadInputException(String type) {
        super("잘못된 입력입니다! " + type + "을(를) 입력해주세요 >> ");
    }
}
