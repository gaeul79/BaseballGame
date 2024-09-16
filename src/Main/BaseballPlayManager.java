package Main;

import Common.BaseballUtils;
import Common.InvalidTypeInputException;

import java.util.Scanner;

public class BaseballPlayManager {
    private final int MIN_LEVEL = 3;
    private final int MAX_LEVEL = 5;
    private int currentLevel = MIN_LEVEL;

    /**
     * 난이도를 선택합니다.
     *
     * @author 김현정
     */
    public void selectLevel() {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputMsg = new StringBuilder();
        inputMsg.append("난이도를 선택해주세요.(");
        inputMsg.append(MIN_LEVEL);
        inputMsg.append("~");
        inputMsg.append(MAX_LEVEL + ") >> ");

        while (true) {
            System.out.println(inputMsg);
            try {
                int num = (int) BaseballUtils.parseNumber(sc.nextLine()); // 난이도 선택
                if (BaseballUtils.isInRange(MIN_LEVEL, MAX_LEVEL, num)) {
                    currentLevel = num;
                    break;
                }
            } catch (InvalidTypeInputException ex) {
                System.out.println(ex.getErrorMsg());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        play();
    }

    /**
     * 숫자 야구 게임의 플레이를 진행합니다.
     *
     * @author 김현정
     */
    public void play() {
        System.out.println("게임을 시작합니다.");
    }
}
