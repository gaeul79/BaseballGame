package Main;

import Common.InputHelper;
import Common.NumberUtil;
import Common.InvalidTypeInputException;
import ValueObject.BaseballRecordItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 야구 게임을 진행하는 클래스
 * 랜덤한 숫자를 맞추는 야구 게임을 진행한다.
 *
 * @author 김현정
 */
public class BaseballPlayManager {
    private final int MIN_LEVEL = 3;
    private final int MAX_LEVEL = 5;
    private int currentLevel = MIN_LEVEL;
    private int playCount = 1;

    /**
     * 난이도를 선택합니다.
     *
     * @author 김현정
     */
    public void selectLevel() {
        StringBuilder inputMsg = new StringBuilder();
        inputMsg.append("난이도를 선택해주세요.(");
        inputMsg.append(MIN_LEVEL);
        inputMsg.append("~");
        inputMsg.append(MAX_LEVEL + ") >> ");

        while (true) {
            try {
                int num = InputHelper.inputNumber(inputMsg.toString()); // 난이도 선택
                if (NumberUtil.isInRange(MIN_LEVEL, MAX_LEVEL, num)) {
                    currentLevel = num;
                    break;
                }
            } catch (InvalidTypeInputException ex) {
                System.out.println(ex.getErrorMsg());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * 숫자 야구 게임의 플레이를 진행합니다.
     *
     * @author 김현정
     */
    public BaseballRecordItem play() {
        System.out.println("<<<<< 숫자 야구 게임을 시작합니다 >>>>>");
        int count = 0;
        List<Integer> baseballNumbers = createBaseballNumber(); // 숫자 생성

        while (true) {
            List<Integer> inputNumbers = inputBaseballNumber(); // 숫자 입력
            count++;

            if (isStrike(baseballNumbers, inputNumbers)) { // 숫자 체크
                printGameResult(baseballNumbers, count); // 맞췄을 시 문구 출력 및 시도 횟수 출력
                break;
            }
        }

        BaseballRecordItem resultItem = new BaseballRecordItem();
        resultItem.setPlayCount(playCount++);
        resultItem.setTryCount(count);
        return resultItem;
    }

    /**
     * 난이도에 따라 랜덤한 숫자를 생성하여 리스트에 담아 반환한다.
     *
     * @return 랜덤한 숫자들로 구성된 리스트
     * @author 김현정
     */
    public List<Integer> createBaseballNumber() {
        List<Integer> baseballNumbers = new ArrayList<>();
        while (baseballNumbers.size() < currentLevel) {
            int randomNumber = NumberUtil.randomNumber(1, 9);
            boolean isSame = baseballNumbers.stream()
                    .anyMatch(number -> number == randomNumber);
            if (!isSame)
                baseballNumbers.add(randomNumber);
        }
        return baseballNumbers;
    }

    /**
     * 사용자로부터 숫자 야구 게임에 맞는 입력값을 받는다.
     *
     * @return 사용자가 입력한 숫자들로 구성된 리스트
     */
    public List<Integer> inputBaseballNumber() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("숫자를 입력하세요. >> ");
            try {
                String inputMsg = sc.nextLine();
                return NumberUtil.parseBaseballNumber(inputMsg, currentLevel);
            } catch (InvalidTypeInputException ex) {
                System.out.println(ex.getErrorMsg());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * 사용자가 입력한 숫자와 컴퓨터가 생성한 숫자를 비교하여 스트라이크, 볼, 아웃을 판정하는 메소드입니다.
     *
     * @param baseballNumbers 컴퓨터가 생성한 숫자들의 리스트
     * @param inputNumbers 사용자가 입력한 숫자들의 리스트
     * @return 사용자가 입력한 숫자와 컴퓨터가 생성한 숫자가 모두 일치하면 true, 그렇지 않으면 false 반환
     */
    public boolean isStrike(List<Integer> baseballNumbers, List<Integer> inputNumbers) {
        if(inputNumbers == null)
            return false;

        int ball = 0;
        int strike = 0;
        int out;

        for (int baseballIdx = 0; baseballIdx < baseballNumbers.size(); baseballIdx++) {
            for (int inputIdx = 0; inputIdx < inputNumbers.size(); inputIdx++) {
                if (baseballNumbers.get(baseballIdx).intValue() == inputNumbers.get(inputIdx).intValue()) {
                    if (baseballIdx == inputIdx) {
                        strike++;
                    } else {
                        ball++;
                    }
                }
            }
        }

        out = currentLevel - strike - ball;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ").append(strike).append(" 스트라이크 ] ");
        sb.append("[ ").append(ball).append(" 볼 ] ");
        sb.append("[ ").append(out).append(" 아웃 ]");
        System.out.println(sb);
        return strike == currentLevel;
    }

    /**
     * 게임 결과를 출력하는 메소드입니다.
     *
     * @param baseballNumbers 컴퓨터가 생성한 숫자들의 리스트
     * @param count 시도 횟수
     */
    public void printGameResult(List<Integer> baseballNumbers, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("<<<<<<<<<< 정답입니다!!!! >>>>>>>>>>\n");
        sb.append("[ 정답: ");
        for(Integer number : baseballNumbers) {
            sb.append(number);
        }
        sb.append(" ]");
        sb.append("[ 시도 횟수: ").append(count).append(" ]");
        System.out.println(sb);
    }
}
