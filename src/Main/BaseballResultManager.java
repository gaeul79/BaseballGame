package Main;

import ValueObject.BaseballResultItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 야구 게임 기록을 관리하는 클래스
 *
 * @author 김현정
 */
public class BaseballResultManager {
    /**
     * 게임 결과를 저장하는 리스트
     */
    private List<BaseballResultItem> baseballScoreItems;

    public BaseballResultManager() {
        baseballScoreItems = new ArrayList<>();
    }

    /**
     * 게임 결과를 리스트에 추가
     *
     * @param item 추가할 게임 결과 항목
     * @author 김현정
     */
    public void addResultItem(BaseballResultItem item) {
        baseballScoreItems.add(item);
    }

    /**
     * 저장된 모든 게임 기록을 출력한다.
     *
     * @author 김현정
     */
    public void printPlayLog() {
        System.out.println("========= 게임 기록 보기 =========");
        if (baseballScoreItems.isEmpty())
            System.out.println("History is empty...");
        else
            baseballScoreItems.forEach(BaseballResultItem::printGameLog);
        System.out.println("================================");
    }
}
