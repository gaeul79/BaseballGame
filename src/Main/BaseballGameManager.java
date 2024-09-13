package Main;

import Common.BadInputException;
import Common.BaseballUtils;
import ValueObject.BaseballMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 야구 게임을 관리하는 메인 클래스입니다.
 * 메뉴 생성, 선택, 게임 실행 등 게임의 전체적인 흐름을 담당합니다.
 *
 * @author 김현정
 */
public class BaseballGameManager {
    private final int MIN_LEVEL = 3;
    private final int MAX_LEVEL = 5;
    private int currentLevel = MIN_LEVEL;
    private List<BaseballMenuItem> menuItems;
    private Scanner sc;

    BaseballGameManager() {
        createMenuItems();
        sc = new Scanner(System.in);
    }

    /**
     * 메뉴 항목들을 생성하여 menuItems 리스트에 추가합니다.
     *
     * @author 김현정
     */
    public void createMenuItems() {
        int id = 1;
        int tempId = 0;
        int parentId = 0;

        menuItems = new ArrayList<>();
        menuItems.add(new BaseballMenuItem(id, parentId, "숫자 야구 게임 플레이", null));
        tempId = parentId;
        parentId = id;

        menuItems.add(new BaseballMenuItem(++id, parentId, "난이도 선택", this::selectLevel));
        menuItems.add(new BaseballMenuItem(++id, parentId, "야구 게임 플레이", this::play));
        parentId = tempId;

        menuItems.add(new BaseballMenuItem(++id, parentId, "기록 보기", this::printPlayLog));
        menuItems.add(new BaseballMenuItem(++id, parentId, "종료", this::finish));
    }

    /**
     * 게임을 시작하고 메뉴를 선택합니다.
     *
     * @author 김현정
     */
    public void start() {
        System.out.println("=== 숫자 야구 게임을 시작합니다. ===");
        int menuId = selectMenu(0);
        menuItems.get(menuId).execute(); // 각 메뉴와 연결된 함수 실행
    }

    /**
     * 메뉴를 출력하고 사용자로부터 메뉴를 선택받습니다.
     *
     * @param parentId 부모 메뉴의 ID
     * @return 선택된 메뉴의 ID
     * @author 김현정
     */
    public int selectMenu(int parentId) {
        // 메뉴 출력
        List<BaseballMenuItem> items = menuItems.stream()
                .filter(item -> item.getParentId() == parentId).toList();
        for (int idx = 0; idx < items.size(); idx++) {
            System.out.println(idx + 1 + ". " + items.get(idx).getName());
        }

        String inputMsg = "메뉴를 선택해주세요. >> ";
        while (true) {
            try {
                // 메뉴 선택
                System.out.print(inputMsg);
                int num = (int) BaseballUtils.parseNumber(sc.nextLine());
                ;
                if (BaseballUtils.isInRange(1, items.size(), num)) {
                    // 하위 항목이 있는지 검색
                    List<BaseballMenuItem> childItems = menuItems.stream()
                            .filter(item -> item.getParentId() == num).toList();

                    // 있다면 하위항목 메뉴를 선택한다.
                    if (!childItems.isEmpty())
                        selectMenu(num);
                    else
                        return num;
                }
            } catch (BadInputException ex) {
                inputMsg = ex.getMessage();
            }
        }
    }

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
        inputMsg.append(MAX_LEVEL);
        inputMsg.append(MAX_LEVEL + ") >> ");

        while (true) {
            System.out.println(inputMsg);
            try {
                int num = (int) BaseballUtils.parseNumber(sc.nextLine()); // 난이도 선택
                if (BaseballUtils.isInRange(MIN_LEVEL, MAX_LEVEL, num)) {
                    currentLevel = num;
                    play();
                }
            } catch (BadInputException ex) {
                inputMsg.setLength(0);
                inputMsg.append(ex.getMessage());
            }
        }
    }

    /**
     * 숫자 야구 게임의 본격적인 플레이를 진행합니다.
     *
     * @author 김현정
     */
    public void play() {
        System.out.println("게임을 시작합니다.");
    }

    /**
     * 지금까지 진행된 게임 기록을 출력합니다.
     *
     * @author 김현정
     */
    public void printPlayLog() {
        System.out.println("게임 기록을 출력합니다.");
    }

    /**
     * 게임을 반복할지 여부를 사용자에게 묻고, 사용자의 입력에 따라 boolean 값을 반환합니다.
     *
     * @return 게임을 반복할 경우 true, 종료할 경우 false를 반환합니다.
     * @author 김현정
     */
    public boolean repeat() {
        System.out.print("계속하시겠습니까? (exit 입력 시 종료) >> ");
        return !sc.nextLine().equals("exit");
    }

    /**
     * 게임을 종료합니다.
     *
     * @author 김현정
     */
    public void finish() {
        sc.close();
        System.out.println("=== 숫자 야구 게임을 종료합니다. ===");
    }
}
