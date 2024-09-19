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
    private List<BaseballResultItem> baseballScoreItems;

    public BaseballResultManager() {
        baseballScoreItems = new ArrayList<>();
    }

    public void addResultItem(BaseballResultItem item) {
        baseballScoreItems.add(item);
    }

    public void printPlayLog() {
        System.out.println("=== 게임 기록 보기 ===");
        if(baseballScoreItems.isEmpty())
            System.out.println("History is empty...");
        else
            baseballScoreItems.forEach(BaseballResultItem::printGameLog);
        System.out.println("====================");
    }
}
