package Common;

/**
 * 사용자 입력값이 유효하지 않을 때 발생하는 예외처리 클래스
 *
 * @author 김현정
 */
public class InvalidTypeInputException extends Throwable {

    private String errorMsg = "잘못된 입력입니다! 올바른 값을 읿력해주세요.";

    public String getErrorMsg() {
        return errorMsg;
    }

    private Object inputValue;

    public Object getInputValue() {
        return inputValue;
    }

    private Object validInputValue;

    public Object getValidInputValue() {
        return validInputValue;
    }


    public InvalidTypeInputException() {
        super("잘못된 입력입니다! 올바른 값을 읿력해주세요.");
    }

    /**
     * 사용자 정의 오류 메시지 생성자
     *
     * @param errorMsg 사용자 정의 오류 메시지
     * @author 김현정
     */
    public InvalidTypeInputException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * 입력 값, 올바른 값, 오류 메시지를 모두 포함하는 생성자
     *
     * @param inputValue      잘못 입력한 값
     * @param validInputValue 올바른 값
     * @param errorMsg        오류 메시지
     * @author 김현정
     */
    public InvalidTypeInputException(Object inputValue, Object validInputValue, String errorMsg) {
        this.inputValue = "[입력한 값: " + inputValue + "]";
        this.validInputValue = "[올바른 값: " + validInputValue + "]";
        if (errorMsg != null && !errorMsg.isEmpty())
            this.errorMsg = errorMsg + " "
                    + this.inputValue + " "
                    + this.validInputValue;
        else
            this.errorMsg += " "
                    + this.inputValue + " "
                    + this.validInputValue;
    }
}
