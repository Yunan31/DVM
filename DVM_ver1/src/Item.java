
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
    private int xPos = 5; //새로이 추가된 두 위아래 좌표놈들. setup에서 초기화 안해주므로 임의로 값 집어넣음.
    private int yPos = 10; //이하동문.
    private int cardNum;
    private int cardBalance;
    private int itemCount[]= new int[20]; //기본 int item[]을 사이즈 20으로 고정 선언, 변수명 변경 (itemPrice에 맞춰서 item => itemCount로)
    private int itemPrice[] = new int[20]; //아예 새로 추가한 항목임, 음료 코드랑 개수는 고려했지만 가격을 고려하지 못해서 추가함.
                                           //이건 그냥 선언과 동시에 초기화 해도 될듯함. (사용자 입력으로 받을 필요성을 느끼지 못함)
    private boolean isNone;
    private HashMap<String,String> authCode = new HashMap<>(); //기존의 Integer, String에서 String, String으로 변환
                                             //앞의 String에 authCode가 들어가고 뒤의 String엔 "음료코드 개수" 형식으로 띄어쓰기해서
                                             //들어갈 예정. split 사용해서 쓰면 됨.
                                             //즉 authcode를 키값으로 찾을것임.
    private boolean isValid;

    public List<Integer> getVMId(){ //msgRequest에 쓰이므로 get추가.
        return this.vmid;
    }

    public int getXpos(){ //위에 새로이 추가하면서 추가된 놈
        return this.xPos;
    }

    public int getyPos(){//위에 새로이 추가하면서 추가된 놈
        return this.yPos;
    }

    public int getMid() { return this.mid; }

    public void setMid(int mid) {
        // TODO implement here
        this.mid=mid;
    }


    public void setVmid(int vmid) {
        // TODO implement here
        this.vmid.add(vmid);
    }


    public void setCard(int cardNum, int cardBalance) {
        // TODO implement here
        this.cardNum=cardNum;
        this.cardBalance=cardBalance;
    }


    public void setItem(int code, int count) {
        // TODO implement here
        itemCount[code]=count;
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


    public void insertAuthCode(int code, int count, String authCode) { //msg로 들어온 값 받아와서 해시맵에 저장
        // TODO implement here
        String items = Integer.toString(code);
        items += " "+ Integer.toString(count);
        this.authCode.put(authCode,items);
        System.out.println("insert authCode: "+authCode + "items: "+items);
    }


    public void updateItemStock(int code, int count) { //해당 코드의 카운트만큼 감소
        // TODO implement here
        int tmpCount = itemCount[code];
        tmpCount -= count;
        itemCount[code]= tmpCount;
    }


    public boolean checkCard(int cardNum, int code, int count) { //cardNum 이외에 계산해야 하는 값이 필요함. (원래는 인자에 cardNum밖에 없었음)
                                            //1. code, count를 같이 전달해주기
        // TODO implement here
        if(this.cardNum != cardNum){
            return false;
        }

        if(this.cardBalance<itemPrice[code]*count){
            return false;
        }

        //itemOut() => 시퀀스에 있는데 굳이 필요 없어보임 (UI적인 느낌?)
        updateItemStock(code, count);
        return true;
    }


    public boolean checkAuthCode(String authCode) {
        // TODO implement here
        if(!this.authCode.containsKey(authCode)){
            return false;
        }

        String code_count = this.authCode.get(authCode);
        int code = Integer.parseInt(code_count.split(" ")[0]);
        int count = Integer.parseInt(code_count.split(" ")[1]);

        //원래 여기 ItemOut이 있어서 code count값이 필요한데 없어서 필요가 없어짐 ㅋㅋ
        //그리고 이미 InsertCode에서 updateItemStock으로 재고를 줄여준 상태임. 또 줄이면 안됨.

        deleteAuthCode(authCode);

        return true;
    }


    public void deleteAuthCode(String authCode) {
        // TODO implement here
        this.authCode.remove(authCode);
    }


    public void update(int code, int count) { //위의 updateItemStock은 ItemOut의 느낌, 얘는 매니징할때 추가하는것. (사실 두개 구분 안해도 됨)
        // TODO implement here
        itemCount[code]=count;
        //여기선 setup값으로 받아오기 때문에 증감이 아닌 대체.

    }
}