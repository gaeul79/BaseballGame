package Main;

import ValueObject.BaseballScoreItem;

import java.util.List;

/**
 * 야구 게임 기록을 관리하는 클래스
 *
 * @author 김현정
 */
public class BaseballScoreManager {
    List<BaseballScoreItem> baseballScoreItems;

    public void printPlayLog() {
        System.out.println("게임 기록을 출력합니다.");
    }
}
