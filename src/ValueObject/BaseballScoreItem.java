package ValueObject;

/**
 * 야구 게임의 한 번의 플레이에 대한 정보를 나타내는 클래스입니다.
 * 게임을 플레이한 횟수와 시도 횟수를 관리합니다.
 *
 * @author 김현정
 */
public class BaseballScoreItem {
    /**
     * 게임을 플레이한 횟수
     */
    private int playCount;
    /**
     * 게임을 플레이하는 동안 시도한 횟수
     */
    private int tryCount;

    /**
     * 게임을 플레이한 횟수를 가져옵니다.
     *
     * @return 게임을 플레이한 횟수
     * @author 김현정
     */
    public int getPlayCount() {
        return playCount;
    }

    /**
     * 게임을 플레이한 횟수를 설정합니다.
     *
     * @param playCount 게임을 플레이한 횟수
     * @author 김현정
     */
    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    /**
     * 게임을 플레이하는 동안 시도한 횟수를 가져옵니다.
     *
     * @return 게임을 플레이하는 동안 시도한 횟수
     * @author 김현정
     */
    public int getTryCount() {
        return tryCount;
    }

    /**
     * 게임을 플레이하는 동안 시도한 횟수를 설정합니다.
     *
     * @param tryCount 게임을 플레이하는 동안 시도한 횟수
     * @author 김현정
     */
    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    /**
     * 게임 로그를 출력합니다.
     *
     * @author 김현정
     */
    public void printGameLog() {
        String sb = playCount + " 번째 게임 : 시도 횟수 - " + tryCount;
        System.out.println(sb);
    }
}
