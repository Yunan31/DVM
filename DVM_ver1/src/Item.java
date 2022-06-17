
import java.util.*;

/**
 *
 */
public class Item {

    /**
     * Default constructor
     */
    public Item() {

    }

    private List<Integer> vmid = new ArrayList<>(); //int [] => list<int>로 형변환
    private int mid;

    private int itemCount[]= new int[21]; //기본 int item[]을 사이즈 20으로 고정 선언, 변수명 변경 (itemPrice에 맞춰서 item => itemCount로)
    private int itemPrice[] = new int[21]; //아예 새로 추가한 항목임, 음료 코드랑 개수는 고려했지만 가격을 고려하지 못해서 추가함.
                                           //이건 그냥 선언과 동시에 초기화 해도 될듯함. (사용자 입력으로 받을 필요성을 느끼지 못함)
    private HashMap<String,String> authCode = new HashMap<>(); //기존의 Integer, String에서 String, String으로 변환
                                             //앞의 String에 authCode가 들어가고 뒤의 String엔 "음료코드 개수" 형식으로 띄어쓰기해서
                                             //들어갈 예정. split 사용해서 쓰면 됨.
                                             //즉 authcode를 키값으로 찾을것임.

    public List<Integer> getVMId(){ //msgRequest에 쓰이므로 get추가.
        return this.vmid;
    }

    public int getMid() { return this.mid; }

    public void setMid(int mid) {
        // TODO implement here
        System.out.println("Set Manager Id: "+Integer.toString(mid));
        this.mid=mid;
    }

    public void setItem(int code, int count,int price) {
        // TODO implement here
        System.out.println("Set Item 코드:  "+Integer.toString(code)+" 개수: "+Integer.toString(count)+"가격: "+Integer.toString(price));
        itemCount[code]=count;
        itemPrice[code]=price;
    }

    public int getItemPrice(int code) {
        return itemPrice[code];
    }
    public int getItemCount(int code) {
        return itemCount[code];
    }

    public boolean checkItemStock(int code, int count) {
        // TODO implement here
        System.out.print("code: "+Integer.toString(code)+" count:" +Integer.toString(count));
        if(itemCount[code]>=count){ //재고 확인해서 주문수량 보다 크거나 같으면 true return
            System.out.print("있음");
            System.out.println();
            return true;
        }
        System.out.print("없음");
        System.out.println();
        return false;
    }

    public void updateItemStock(int code, int count) { //해당 코드의 카운트만큼 감소
        // TODO implement here
        System.out.print("code: "+Integer.toString(code)+" count:" +Integer.toString(count)+"만큼 재고 감량");
        int tmpCount = itemCount[code];
        tmpCount -= count;
        itemCount[code]= tmpCount;
    }

    public void update(int code, int count) { //위의 updateItemStock은 ItemOut의 느낌, 얘는 매니징할때 추가하는것. (사실 두개 구분 안해도 됨)
        // TODO implement here
        System.out.print("code: "+Integer.toString(code)+" count:" +Integer.toString(count)+"만큼 추가하였습니다.");
        itemCount[code]=count;
        //여기선 setup값으로 받아오기 때문에 증감이 아닌 대체.
    }
}