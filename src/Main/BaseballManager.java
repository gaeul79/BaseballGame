package Main;

import Common.InputHelper;
import Common.InvalidTypeInputException;
import Common.NumberUtil;
import ValueObject.BaseballMenuItem;
import ValueObject.BaseballRecordItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 야구 게임을 관리하는 메인 클래스입니다.
 * 메뉴 생성, 선택과 같은 게임 흐름을 담당합니다.
 *
 * @author 김현정
 */
public class BaseballManager {
    private boolean isExit = false;

    /** 메뉴 리스트 */
    private List<BaseballMenuItem> menuItems;
    /** 현제 선택된 메뉴 */
    private BaseballMenuItem currentMenuItem;
    /** 게임 플레이를 관리하는 객체 */
    private BaseballPlayManager playManager;
    /** 게임 결과를 관리하는 객체 */
    private BaseballResultManager resultManager;

    BaseballManager() {
        playManager = new BaseballPlayManager();
        resultManager = new BaseballResultManager();
        createMenuItems();
    }

    /**
     * 메뉴 항목들을 생성하여 menuItems 리스트에 추가합니다.
     *
     * @author 김현정
     */
    public void createMenuItems() {
        int id = 1;
        int parentId = 0;
        int tempId;

        menuItems = new ArrayList<>();
        menuItems.add(new BaseballMenuItem(id, parentId, "숫자 야구 게임 플레이", this::setFirstChildMenuItem));
        tempId = parentId;
        parentId = id;

        menuItems.add(new BaseballMenuItem(++id, parentId, "난이도 선택", this::selectLevel));
        menuItems.add(new BaseballMenuItem(++id, parentId, "야구 게임 플레이", this::play));
        menuItems.add(new BaseballMenuItem(++id, parentId, "뒤로", this::setParentMenuItem));
        parentId = tempId;

        menuItems.add(new BaseballMenuItem(++id, parentId, "기록 보기", this::printPlayRecord));
        menuItems.add(new BaseballMenuItem(++id, parentId, "종료", this::exit));
    }

    /**
     * 야구 게임을 실행합니다.
     *
     * @author 김현정
     */
    public void start() {
        while (!isExit) {
            selectMenu();
        }
    }

    /**
     * 메뉴를 출력하고 사용자로부터 메뉴를 선택받습니다.
     *
     * @author 김현정
     */
    public void selectMenu() {
        List<BaseballMenuItem> menuItems = getMenuItems(); // 메뉴 출력
        System.out.println("========= 숫자 야구 게임 =========");
        for (int idx = 0; idx < menuItems.size(); idx++) {
            System.out.println(idx + 1 + ". " + menuItems.get(idx).getName());
        }

        while (true) {
            try {
                int menuId = InputHelper.inputNumber("메뉴를 선택해주세요. >> ");
                if (NumberUtil.isInRange(1, menuItems.size(), menuId)) {
                    currentMenuItem = menuItems.get(menuId - 1);
                    currentMenuItem.execute(); // 각 메뉴와 연결된 함수 실행
                    break;
                }
            } catch (InvalidTypeInputException ex) {
                System.out.println(ex.getErrorMsg());
            }
        }
    }

    /**
     * 현재 선택한 메뉴와 같은 계층의 메뉴들을 반환합니다.
     *
     * @return 메뉴들의 리스트
     * @author 김현정
     */
    public List<BaseballMenuItem> getMenuItems() {
        int parentId = currentMenuItem != null ? currentMenuItem.getParentId() : 0;
        return menuItems.stream()
                .filter(item -> item.getParentId() == parentId).toList();
    }

    /**
     * 현재 선택된 메뉴를 현재 메뉴의 부모 메뉴로 설정 합니다.
     * 부모 메뉴로 이동합니다.
     *
     * @author 김현정
     */
    public void setParentMenuItem() {
        if (currentMenuItem != null) {
            for (BaseballMenuItem parentMenuItem : menuItems) {
                if (parentMenuItem.getId() == currentMenuItem.getParentId()) {
                    int parentId = parentMenuItem.getParentId();
                    currentMenuItem = menuItems.stream()
                            .filter(item -> item.getParentId() == parentId)
                            .findFirst().orElse(menuItems.get(0));
                }
            }
        }
    }

    /**
     * 현재 선택된 메뉴를 메뉴의 첫번째 자식 메뉴로 설정 합니다.
     * 자식 메뉴로 이동합니다.
     *
     * @author 김현정
     */
    public void setFirstChildMenuItem() {
        int id = currentMenuItem != null ? currentMenuItem.getId() : 0;
        currentMenuItem = menuItems.stream()
                .filter(item -> item.getParentId() == id).findFirst().orElse(null);
    }

    /**
     * 난이도를 선택합니다.
     *
     * @author 김현정
     */
    public void selectLevel() {
        playManager.selectLevel();
        play();
    }

    /**
     * 숫자 야구 게임의 플레이를 진행합니다.
     *
     * @author 김현정
     */
    public void play() {
        do {
            BaseballRecordItem resultItem = playManager.play();
            resultManager.addGameRecord(resultItem);
        } while (continueGame());
    }

    /**
     * 게임을 반복할지 여부를 사용자에게 묻는 함수
     *
     * @return 게임을 계속할 경우 true, 종료할 경우 false 반환.
     * @author 김현정
     */
    public boolean continueGame() {
        return !InputHelper.input("계속하시겠습니까? (exit 입력 시 메뉴로 돌아갑니다.) >> ").equals("exit");
    }

    /**
     * 지금까지 진행된 게임 기록을 출력합니다.
     *
     * @author 김현정
     */
    public void printPlayRecord() {
        resultManager.printGameRecords();
        waitPressEnterKey();
    }

    /**
     * Enter 키를 입력받을때까지 대기한다.
     *
     * @author 김현정
     */
    public void waitPressEnterKey() {
        InputHelper.input("Enter 키를 누르면 메뉴로 돌아갑니다.");
    }

    /**
     * 야구 게임 종료
     *
     * @author 김현정
     */
    public void exit() {
        isExit = true;
        System.out.println("==== 숫자 야구 게임을 종료합니다. ====");
    }
}
