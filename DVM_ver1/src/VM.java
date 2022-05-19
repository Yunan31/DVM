import DVM_Client.DVMClient;
import GsonConverter.Serializer;
import Model.Message;

import java.util.List;

public class VM extends dvm_gui{

    Item item = new Item();
    /**
     * Default constructor
     */
    public VM(){
        isValid = false;
        position = new int[100][100];
        isNone = false;
    }

    private boolean isValid;
    private int position[][];
    private boolean isNone;
    private String authCode;


    private void selectItem(int code, int count) {
        // TODO implement here
    }


    private void showMenu() {
        // TODO implement here
    }


    private void getPrepayment() {
        // TODO implement here
    }


    private void readAuthenticationCode() {
        // TODO implement here
    }


    private void managerAccess() {
        // TODO implement here
    }


    private void showInputManagerAuthCode() {
        // TODO implement here
    }


    private boolean checkManagerAccess(int code) {
        // TODO implement here
        return false;
    }


    private void showSetUp() {
        // TODO implement here
    }


    private void setVmid(int vmid) {
        // TODO implement here
    }


    private void setMid(int mid) {
        // TODO implement here
    }


    private void setCard(int cardNum, int cardBalance) {
        // TODO implement here
    }


    private void done() {
        // TODO implement here
    }


    private void selectMode() {
        // TODO implement here
    }


    private boolean checkItemStock(int code, int count) {
        // TODO implement here
        return false;
    }


    private void guideOtherMachine(int xPos, int yPos) {
        // TODO implement here
    }


    private void normalPayment(int code, int count) {
        // TODO implement here
    }


    private void cancel() {
        // TODO implement here
    }


    private void reset() {
        // TODO implement here
    }


    private void msgRequest(String type, String code, int count, String dst, String authCode) throws InterruptedException { //1. int type => string type 2. int code => String code
                                                                                                // 3.type string은 추후에 바꿔야함~ 4. 목표 vmID인자로 추가~
                                                                                                //5. String authCode 인자로 추가.
        // TODO implement here

        //이 변수들도 다 추가해야하나...
        List<Integer> list = item.getVMId();

        if(type.equals("재고 확인 요청")){ //broadcast로 전부 보내야 함.
            for(int i=0;i<list.size();i++){ //src, dst를 하나하나 맵핑해서 반복문만큼 요청을 보내도 괜찮나?
                                            //혹은 src,dst를 안넣고 msgType으로 알아서 구분해서 전체로 보내나? => 이 경우에 src는 필요하긴 할텐데? 그래야 누군지 알고 반환하니까 ㅋㅋ

            }

        }

        if(type.equals("선결제 확인")){
            //전송할 메세지 설정
            requestMsg(type,code,count,dst,authCode, item.getXpos(), item.getyPos());
        }

        if(type.equals("음료 판매 확인")){ //얘도 broadcast라 일단 구현 안함.

        }

        if(type.equals("재고 확인 응답")){
            requestMsg(type,code,count,dst,authCode,item.getXpos(), item.getyPos());
        }

        if(type.equals("음료 판매 응답")){
            requestMsg(type,code,count,dst,authCode,item.getXpos(), item.getyPos());
        }
    }

    private void requestMsg(String type, String code, int count, String dst, String authCode, int xPos, int yPos) throws InterruptedException {
        Message msg = new Message();
        Message.MessageDescription msgDesc = new Message.MessageDescription();
        Serializer msg2json = new Serializer();
        List<Integer> list = item.getVMId();

        msg.setSrcId("5"); //우리 Id는 5로 고정 => 얘도 item에 저장하고 가져오는 식으로 해야하나...? (귀찮음 ㅎㅎ)
        msg.setDstID(dst);
        msg.setMsgType(type);
        msgDesc.setItemCode(code);
        msgDesc.setItemNum(count);
        msgDesc.setDvmXCoord(xPos); //이것도 위와 같은 의견. setup에서 정의하지 않음. 아마 연결하기 전에 값 줄건데 그걸로 고정
        msgDesc.setDvmYCoord(yPos); //이하동문.
        msgDesc.setAuthCode(authCode);
        msg.setMsgDescription(msgDesc);

        String jsonMsg = msg2json.message2Json(msg); //msg=>json

        DVMClient client = new DVMClient("8080",jsonMsg);
        client.run();
    }

    private void msgReturn(String type, String code, int count, String dst, String authCode, int xPos, int yPos) { //필요가 있나?
        // TODO implement here

    }


    private int[][] findNearVm() {
        // TODO implement here
        return null;
    }
    

    private void getCountMsg(int code, int count) {
        // TODO implement here
    }


    private void returnCountMsg(int code, int count, int xPos, int yPos) {
        // TODO implement here
    }


    private void getPrePayMsg(int code, int count, String authCode) {
        // TODO implement here
    }


    private void insertAuthCode(int code, int count, String authCode) {
        // TODO implement here
    }


    private void getItemSaleMsg(int code, int count) {
        // TODO implement here
    }


    private void returnItemsSaleCheckMsg(int code, int xPos, int yPos) {
        // TODO implement here
    }


    private void showOtherVm() {
        // TODO implement here
    }


    private void prePayment() {
        // TODO implement here
    }


    private void requestCard() {
        // TODO implement here
    }


    private boolean checkCard(int cardNum) {
        // TODO implement here
        return false;
    }


    private String createAuthCode() {
        // TODO implement here
        return "";
    }


    private void showAuthcode(String authCode) {
        // TODO implement here
    }


    private void showInputPreAuthcode() {
        // TODO implement here
    }


    private boolean checkAuthCode(String authCode) {
        // TODO implement here
        return false;
    }


    private void showManagingVm() {
        // TODO implement here
    }


    private void setup() {
        // TODO implement here
    }


    private void manageStock() {
        // TODO implement here
    }


    private void showManageStock() {
        // TODO implement here
    }


    private void update(int code, int count) {
        // TODO implement here
    }
}