package ValueObject;

/**
 * 야구 경기 상태를 나타내는 열거형
 * @author 김현정
 */
public enum BaseballState {
    /** 아웃 상태 */
    OUT("아웃"),
    /** 볼 상태 */
    BALL("볼"),
    /** 스트라이크 상태 */
    STRIKE("스트라이크");

    private String name;

    /**
     * 생성자
     *
     * @param name 한글 이름
     * @author 김현정
     */
    BaseballState(String name) {
        this.name = name;
    }

    /**
     * 상태의 한글 이름을 반환합니다.
     *
     * @return 상태의 한글 이름
     * @author 김현정
     */
    public String getName() { // 문자를 받아오는 함수
        return name;
    }
}
