package ValueObject;

import Interface.BaseballFunctionInterface;

/**
 * 야구 메뉴 항목 클래스
 *
 * @author 김현정
 */
public class BaseballMenuItem {
    /**
     * 메뉴 항목의 고유 식별 번호
     */
    private int id;

    public int getId() {
        return id;
    }

    /**
     * 상위 메뉴 항목의 고유 식별 번호
     */
    private int parentId;

    public int getParentId() {
        return parentId;
    }

    private String name;

    public String getName() {
        return name;
    }

    /**
     * 메뉴 항목 실행 시 동작을 담당하는 인터페이스
     */
    private BaseballFunctionInterface functionInterface;

    /**
     * 생성자: 메뉴 항목 정보 설정
     *
     * @param id                메뉴 항목 고유 식별 번호
     * @param parentId          상위 메뉴 항목 고유 식별 번호
     * @param name              메뉴 항목 이름
     * @param functionInterface 메뉴 항목 실행 시 동작을 담당하는 인터페이스 객체
     * @author 김현정
     */
    public BaseballMenuItem(int id, int parentId, String name, BaseballFunctionInterface functionInterface) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.functionInterface = functionInterface;
    }

    /**
     * 메뉴 항목 실행 (functionInterface 인터페이스의 execute() 메서드 호출)
     *
     * @author 김현정
     */
    public void execute() {
        if(functionInterface != null) {
            functionInterface.execute();
        }
    }
}
