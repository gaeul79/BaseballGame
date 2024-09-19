package Main;

import Common.InvalidTypeInputException;
import Common.NumberUtil;
import ValueObject.BaseballMenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 야구 게임을 관리하는 메인 클래스입니다.
 * 메뉴 생성, 선택과 같은 게임 흐름을 담당합니다.
 *
 * @author 김현정
 */
public class BaseballManager {
    private final Scanner sc;
    private boolean isPlay = true;
    private List<BaseballMenuItem> menuItems;
    private BaseballMenuItem currentBaseballMenu; // 현재 선택한 메뉴
    private BaseballPlayManager baseballPlayManager;
    private BaseballScoreManager baseballScoreManager;

    BaseballManager() {
        sc = new Scanner(System.in);
        baseballPlayManager = new BaseballPlayManager();
        baseballScoreManager = new BaseballScoreManager();
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
        menuItems.add(new BaseballMenuItem(id, parentId, "숫자 야구 게임 플레이", this::selectChildMenu));
        tempId = parentId;
        parentId = id;

        menuItems.add(new BaseballMenuItem(++id, parentId, "난이도 선택", this::selectLevel));
        menuItems.add(new BaseballMenuItem(++id, parentId, "야구 게임 플레이", this::play));
        menuItems.add(new BaseballMenuItem(++id, parentId, "뒤로", this::selectParentMenu));
        parentId = tempId;

        menuItems.add(new BaseballMenuItem(++id, parentId, "기록 보기", this::printPlayLog));
        menuItems.add(new BaseballMenuItem(++id, parentId, "종료", this::exit));
    }

    /**
     * 야구 게임을 실행합니다.
     *
     * @author 김현정
     */
    public void start() {
        while (isPlay) {
            System.out.println("=== 숫자 야구 게임 ===");
            selectMenu(getMenuItems(currentBaseballMenu));
            if(isPlay)
                continueGame();
        }
        System.out.println("=== 숫자 야구 게임을 종료합니다. ===");
    }

    /**
     * 지정된 메뉴 항목과 같은 계층의 메뉴들을 반환합니다.
     *
     * @param menuItem 같은 계층의 메뉴를 찾을 대상 메뉴 항목
     * @return 메뉴들의 리스트
     * @author 김현정
     */
    public List<BaseballMenuItem> getMenuItems(BaseballMenuItem menuItem) {
        int parentId = menuItem != null ? menuItem.getParentId() : 0;
        return menuItems.stream()
                .filter(item -> item.getParentId() == parentId).toList();
    }

    /**
     * 지정된 메뉴 항목의 부모 메뉴들을 반환합니다.
     *
     * @param menuItem 부모 메뉴를 찾을 대상 메뉴 항목
     * @return 부모 메뉴들의 리스트
     * @author 김현정
     */
    public List<BaseballMenuItem> getParentMenuItems(BaseballMenuItem menuItem) {
        if(menuItem != null) {
            for(BaseballMenuItem parentMenuItem : menuItems) {
                if(parentMenuItem.getId() == menuItem.getParentId()) {
                    int parentId = parentMenuItem.getParentId();
                    return menuItems.stream()
                            .filter(item -> item.getParentId() == parentId).toList();
                }
            }
        }

        return menuItems.stream()
                .filter(item -> item.getId() == 0).toList();
    }

    /**
     * 지정된 메뉴 항목의 자식 메뉴들을 반환합니다.
     *
     * @param menuItem 자식 메뉴를 찾을 대상 메뉴 항목
     * @return 자식 메뉴들의 리스트
     * @author 김현정
     */
    public List<BaseballMenuItem> getChildMenuItems(BaseballMenuItem menuItem) {
        int id = menuItem != null ? menuItem.getId() : 0;
        return menuItems.stream()
                .filter(item -> item.getParentId() == id).toList();
    }

    /**
     * 메뉴를 출력하고 사용자로부터 메뉴를 선택받습니다.
     *
     * @param items 출력할 메뉴 항목들의 리스트
     * @author 김현정
     */
    public void selectMenu(List<BaseballMenuItem> items) {
        // 메뉴 출력
        for (int idx = 0; idx < items.size(); idx++) {
            System.out.println(idx + 1 + ". " + items.get(idx).getName());
        }

        while (true) {
            try {
                System.out.print("메뉴를 선택해주세요. >> ");
                int menuId = (int) NumberUtil.parseNumber(sc.nextLine());
                if (NumberUtil.isInRange(1, items.size(), menuId)) {
                    currentBaseballMenu = items.get(menuId - 1);
                    currentBaseballMenu.execute(); // 각 메뉴와 연결된 함수 실행
                    break;
                }
            } catch (InvalidTypeInputException ex) {
                System.out.println(ex.getErrorMsg());
            }
        }
    }

    /**
     * 현재 메뉴의 자식 메뉴로 이동합니다.
     *
     * @author 김현정
     */
    public void selectChildMenu() {
        selectMenu(getChildMenuItems(currentBaseballMenu));
    }

    /**
     * 현재 메뉴의 부모 메뉴로 이동합니다.
     *
     * @author 김현정
     */
    public void selectParentMenu() {
        selectMenu(getParentMenuItems(currentBaseballMenu));
    }

    /**
     * 난이도를 선택합니다.
     *
     * @author 김현정
     */
    public void selectLevel() {
        baseballPlayManager.selectLevel();
        play();
    }

    /**
     * 숫자 야구 게임의 플레이를 진행합니다.
     *
     * @author 김현정
     */
    public void play() {
        baseballPlayManager.play();
    }

    /**
     * 지금까지 진행된 게임 기록을 출력합니다.
     *
     * @author 김현정
     */
    public void printPlayLog() {
        baseballScoreManager.printPlayLog();
    }

    /**
     * 게임을 반복할지 여부를 사용자에게 묻는 함수
     *
     * @author 김현정
     */
    public void continueGame() {
        System.out.print("계속하시겠습니까? (exit 입력 시 종료) >> ");
        isPlay = !sc.nextLine().equals("exit");
    }

    /**
     * 야구 게임 종료
     *
     * @author 김현정
     */
    public void exit() {
        isPlay = false;
        currentBaseballMenu = null;
        baseballPlayManager = null;
        baseballScoreManager = null;
    }
}
