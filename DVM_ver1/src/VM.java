import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class VM {

    Item item = new Item();
    CardData cardData = new CardData();
    AuthcodeData authcode = new AuthcodeData();
    VMData vmData = new VMData();
    Scanner sc = new Scanner(System.in);

    /**
     * Default constructor
     */
    public VM() {
        isValid = false;
        position = new int[2];
        isNone = false;

        Thread2 thread = new Thread2();
        thread.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValid;
    private int position[];
    private boolean isNone;
    //"192.168.66.176","192,168.67.11","192.168.67.30","192.168.65.204","our","192.168.64.242"
    //private String authCode;
    /*
    private String[] vmIp= {"192.168.157.16","null","null","null","our","null"};
    private String srcId ="Team5";
    VMData로 이동
     */

    public int[] getPosition(){
        return this.position;
    }
    private class Thread2 extends Thread{
        @Override
        public void run(){
            try{
                while(true){
                    Thread.sleep(3000);

                    //System.out.println("present msgList size: "+Integer.toString(DVMServer.msgList.size()));

                    for (int i = 0; i < DVMServer.msgList.size(); i++) {
                        if (DVMServer.msgList.get(i).getMsgType().equals("StockCheckRequest")) { //음료 코드_음료 개수_dst id_dst 좌표

                            ArrayList<Message> msg = returnMsg("StockCheckRequest");

                            for(int j=0;j<msg.size();j++){
                                int checkCode = Integer.parseInt(msg.get(j).getMsgDescription().getItemCode());
                                int checkNum = msg.get(j).getMsgDescription().getItemNum();

                                if(item.checkItemStock(checkCode, checkNum)){
                                    System.out.println("request StockCheckResponse start");
                                    requestMsg("StockCheckResponse",Integer.toString(checkCode),checkNum,msg.get(j).getSrcId(),"",vmData.getXpos(),vmData.getyPos());
                                    System.out.println("request StockCheckResponse done");
                                }
                                //여기여기여기여기

                            }
                            continue;
                        }

                        if (DVMServer.msgList.get(i).getMsgType().equals("SalesCheckRequest")) { //음료코드 dstId, dst 좌표
                            ArrayList<Message> msg = returnMsg("SalesCheckRequest");
                            for(int j=0;j<msg.size();j++){
                                int checkCode = Integer.parseInt(msg.get(j).getMsgDescription().getItemCode());
                                int checkNum = msg.get(j).getMsgDescription().getItemNum();

                                if(item.checkItemStock(checkCode, checkNum)){
                                    System.out.println("request SalesCheckResponse start");
                                    requestMsg("SalesCheckResponse",Integer.toString(checkCode),0,msg.get(j).getSrcId(),"",vmData.getXpos(),vmData.getyPos());
                                    System.out.println("request SalesCheckResponse done");
                                }

                            }
                            continue;
                        }

                        if (DVMServer.msgList.get(i).getMsgType().equals("PrepaymentCheck")) {
                            ArrayList<Message> msg = returnMsg("PrepaymentCheck");
                            for(int j=0;j<msg.size();j++){
                                System.out.println("insertAuthCode start");
                                insertAuthCode(Integer.parseInt(msg.get(j).getMsgDescription().getItemCode()),msg.get(j).getMsgDescription().getItemNum(),msg.get(j).getMsgDescription().getAuthCode());
                                item.updateItemStock(Integer.parseInt(msg.get(j).getMsgDescription().getItemCode()),msg.get(j).getMsgDescription().getItemNum());
                                // 남의 기계에서 선결제
                                System.out.println("insertAuthCode done");
                            }
                            continue;
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //private -> public GUI selectItem()에서 사용
    //return void -> boolean 값에 따라 띄우는 화면이 달라짐
    public String selectItem(int code, int count) throws InterruptedException {
        String itemStockCheck = checkItemStock(code, count);
        return itemStockCheck;
    }

//    public void start() throws InterruptedException { //초기에 vm시작할때 시작되어야함. 새로 추가한것.
//       // setup();
//
//
//        Thread2 thread = new Thread2();
//        thread.start();
//
//
//        Thread.sleep(3000);
//    }

//    private void showMenu() {
//        // TODO implement here
//
//    }


//    private void getPrepayment() {
//        // TODO implement here
//    }


//    private void readAuthenticationCode() {
//        // TODO implement here
//    }


//    private void managerAccess() {
//        // TODO implement here
//    }


//    private void showInputManagerAuthCode() {
//        // TODO implement here
//    }


    //private -> public
    public boolean checkManagerAccess(int code) {
        // TODO implement here
        return code == item.getMid();
    }


//    private void showSetUp() {
//
//    }


    public void setVmid(int vmid) {
        // TODO implement here
        vmData.setVmid(vmid);
    }
    public void setVmIp(int vmid, String ip) {
        // TODO implement here
            vmData.setVmIp(vmid-1,ip);
    }


    public void setMid(int mid) {
        // TODO implement here
        item.setMid(mid);
    }


    public void setCard(int cardNum, int cardBalance) {
        // TODO implement here
        cardData.setCard(cardNum,cardBalance);
    }


//    private void done() {
//        // TODO implement here
//    }


//    private void selectMode() {
//        // TODO implement here
//    }


    private String checkItemStock(int code, int count) throws InterruptedException { //
        // TODO implement here

        if(item.checkItemStock(code,count)){
            return "our"; //우리꺼에서 체크
        }

        //System.out.println("보내는 음료 코드 :"+ String.format( "%1$02d" , code));
        requestMsg("StockCheckRequest",String.format( "%1$02d" , code),count,"0","",vmData.getXpos(),vmData.getyPos());
        System.out.println("sending StockCheckResponse");

        //Thread.sleep(5000);
        ArrayList<Message> msg = new ArrayList<>();

        for(int i=0;i<1000000;i++){

        }

        for(int i=0;i<5;i++){
            Thread.sleep(1000);
            msg = returnMsg("StockCheckResponse");
            System.out.println("checking StockCheckResponse");
            if(msg.size()!= 0){
                break;
            }
        }


        if(msg.size()==0){
            System.out.println("No StockCheckRequest response");
            return "none"; //응답을 받은게 없음
        }

        String dstId = findNearVm(msg,count);

        return dstId; //남에꺼 체크
    }

    //private -> public
    //void -> int[]
    //인자 없앰 (int xPos, yPos)
    //메세지 안에서 실행해줘야할듯
//    public int[] guideOtherMachine() {
//        // TODO implement here
//        int[] pos = new int[2];
//        //테스트
//        pos[0] = 1; pos[1] = 2;
//        return pos;
//    }


    //private -> public
    //return void -> boolean(isValid)
    //인자에 카드번호 추가
    public boolean normalPayment(int cardNum, int code, int count) {
        // TODO implement here
        isValid = checkCard(cardNum, code, count);
        return isValid;
    }   // 이거 인자 전달 잘 되는지 확인 상희 > 문제될 건 없어보임?


//    private void cancel() {
//        // TODO implement here
//    }


//    private void reset() {
//        // TODO implement here
//    }


//    private void msgRequest(String type, String code, int count, String dst, String authCode) throws InterruptedException { //1. int type => string type 2. int code => String code
//        // 3.type string은 추후에 바꿔야함~ 4. 목표 vmID인자로 추가~
//        //5. String authCode 인자로 추가.
//        // TODO implement here
//        // magType = "StockCheckRequest" or "StockCheckResponse" or "PrepaymentCheck" or "SalesCheckRequest" or "SalesCheckResponse
//
//        //이 변수들도 다 추가해야하나...
//        //List<Integer> list = item.getVMId();
//
//        requestMsg(type, code, count, dst, authCode, item.getXpos(), item.getyPos()); //이거 하나로 다 가능해보임. 안돼면 필요 없는 인자갑ㅅ들 ㅣㅂ워주거ㅏㄴ null값으로 바꿔줘야함.
//
////        if(type.equals("재고 확인 요청")){ //broadcast로 전부 보내야 함.
////            requestMsg(type,code,count,"0",null,0,0); //dst를 0으로 줘서 broadcast를 보냄.
////
////        }
////
////        if(type.equals("선결제 확인")){
////            //전송할 메세지 설정
////            requestMsg(type,code,count,dst,authCode, item.getXpos(), item.getyPos());
////        }
////
////        if(type.equals("음료 판매 확인")){ //얘도 broadcast라 일단 구현 안함.
////
////        }
////
////        if(type.equals("재고 확인 응답")){
////            requestMsg(type,code,count,dst,authCode,item.getXpos(), item.getyPos());
////        }
////
////        if(type.equals("음료 판매 응답")){
////            requestMsg(type,code,count,dst,authCode,item.getXpos(), item.getyPos());
////        }
//    }

    private void requestMsg(String type, String code, int count, String dst, String authCode, int xPos, int yPos) throws InterruptedException {
        Message msg = new Message();
        Message.MessageDescription msgDesc = new Message.MessageDescription();
        Serializer msg2json = new Serializer();


        code = String.format( "%1$02d" , Integer.parseInt(code));
        System.out.println("바꾼 code: "+code);
        //List<Integer> list = item.getVMId();

//        if (type.equals("PrepaymentCheck") || type.equals("SalesCheckResponse")) {
//            msg.setSrcId(dst); //우리 Id는 5로 고정 => 얘도 item에 저장하고 가져오는 식으로 해야하나...? (귀찮음 ㅎㅎ)
//            msg.setDstID("5");
//        } else {
//            msg.setSrcId("5"); //우리 Id는 5로 고정 => 얘도 item에 저장하고 가져오는 식으로 해야하나...? (귀찮음 ㅎㅎ)
//            msg.setDstID(dst);
//        }

        msg.setSrcId(vmData.srcId); //우리 Id는 5로 고정 => 얘도 item에 저장하고 가져오는 식으로 해야하나...? (귀찮음 ㅎㅎ)
        msg.setDstID(dst);

        msg.setMsgType(type);
        msgDesc.setItemCode(code);
        msgDesc.setItemNum(count);
        msgDesc.setDvmXCoord(xPos); //이것도 위와 같은 의견. setup에서 정의하지 않음. 아마 연결하기 전에 값 줄건데 그걸로 고정
        msgDesc.setDvmYCoord(yPos); //이하동문.
        msgDesc.setAuthCode(authCode);
        msg.setMsgDescription(msgDesc);

        String jsonMsg = msg2json.message2Json(msg); //msg=>json
        
        if(dst.equals("0")){
            for(int i=0;i< vmData.getVmIpLength();i++){
                if(vmData.getVmIp(i).equals("our")||vmData.getVmIp(i).equals("null")){
                    continue;
                }
                
                DVMClient client = new DVMClient(vmData.getVmIp(i), jsonMsg);
                client.run();
            }
        }
        else{

            String tmpDst = Character.toString(dst.charAt(dst.length()-1));

            System.out.println("tmpDst: "+tmpDst);
            DVMClient client = new DVMClient(vmData.getVmIp(Integer.parseInt(tmpDst)-1), jsonMsg);
            client.run();
        }
    }

    private ArrayList<Message> returnMsg(String msgType) { //msgList에서 원하는 타입의 반환값 가져오기 int code, int count, int xPos, int yPos 뺌
        //returnCountMsg에서 returnMsg로 변경, return void 에서 return ArrayList<Message>로 변경
        // TODO implement here
        ArrayList<Message> msgList = new ArrayList();

        for (int i = 0; i < DVMServer.msgList.size(); i++) {
            if (DVMServer.msgList.get(i).getMsgType().equals(msgType)) {
                msgList.add(DVMServer.msgList.get(i));
                DVMServer.msgList.remove(i);
                i--;
            }
        }
        return msgList;
    }

    private String findNearVm(ArrayList<Message> msgList, int count) { //returnMsg에서 지정한 type의 메세지를 가져와서 findNearVm에 넣으면 됨.
        //그리고 반환 타입이 int[][]인데 int[]로 바꿈
        // TODO implement here
        int dist = 99 * 99 * 99 * 99;
        String id = "null";
        int xPos = -1;
        int yPos = -1;

        for (int i = 0; i < msgList.size(); i++) {
            if(msgList.get(i).getMsgDescription().getItemNum()<count){
                continue;
            }

            int xDiff = msgList.get(i).getMsgDescription().getDvmXCoord() - vmData.getXpos();
            int yDiff = msgList.get(i).getMsgDescription().getDvmYCoord() - vmData.getyPos();
            if (xDiff * xDiff * yDiff * yDiff < dist) {
                dist = xDiff * xDiff * yDiff * yDiff;

                id = msgList.get(i).getSrcId();

                xPos = msgList.get(i).getMsgDescription().getDvmXCoord();
                yPos = msgList.get(i).getMsgDescription().getDvmYCoord();
            }
        }
        if (id.equals("null")) { //요청에 대한 리턴 값이 없음.
            System.out.println("해당 재고를 지닌 vm 없음");
            return null;
        }

        this.position[0]=xPos;
        this.position[1]=yPos;
        
        System.out.println("가장 가까운 id: "+id+"위치: "+Integer.toString(xPos)+", "+Integer.toString(yPos));


        return id;
    }


//    private void getCountMsg(int code, int count) {//msg관련된거  requestMsg, returnMsg로 퉁
//        // TODO implement here
//
//
//    }




//
//    private void getPrePayMsg(int code, int count, String authCode) { //msg관련된거 위에 두개로 퉁
//        // TODO implement here
//    }


    private void insertAuthCode(int code, int count, String authCode) {
        // TODO implement here
        authcode.insertAuthCode(code, count, authCode);
    }


//    private void getItemSaleMsg(int code, int count) { //msg관련된거 위에 두개로 퉁
//        // TODO implement here
//    }
//
//
//    private void returnItemsSaleCheckMsg(int code, int xPos, int yPos) { //msg관련된거 위에 두개로 퉁
//        // TODO implement here
//    }
//
//
//    private void showOtherVm() {
//        // TODO implement here
//    }


    //private -> public
    //return void -> String 인증 코드를 리턴함
    //결제 성공 시 인증코드는 문자열, 실패 시 ""로 리턴
    //카드번호, 음료 코드, 개수 인자 추가
    public String prePayment(int cardNum, int code, int count,String dstId) throws InterruptedException {
        // TODO implement here
        isValid = checkCard(cardNum, code, count);
        if(isValid){
            String authCode =createAuthCode();
            requestMsg("PrepaymentCheck" ,Integer.toString(code), count,dstId,authCode,vmData.getXpos(),vmData.getyPos()); //dst
            return authCode;
        }


        else
            return "";
    }   //여기도 인자전달 잘 되는지 확인 상희  > 여기도 문제 없을듯


//    private void requestCard() {
//        // TODO implement here
//
//    }


    private boolean checkCard(int cardNum, int code, int count) { //item에서의 부분과 마찬가지로 인자 축.
        // TODO implement here
        // 여기여기
        if(cardData.isNotValidCard(cardNum)){
            System.out.println("카드 번호가 유효하지 않습니다.");
            return false;
        }   // 카드번호가 유효함

        if(cardData.getCardBalance()<item.getItemPrice(code)*count){
            System.out.println("결제 금액 :"+item.getItemPrice(code)*count+", 보유 금액 :"+cardData.getCardBalance());      // 테스트용
            return false;
        }   // 결제 요청 금액이 보유 금액보다 큼


        cardData.withdrawBalance(item.getItemPrice(code)*count);


        System.out.println("잔액 :"+cardData.getCardBalance());

        return true;
    }


    private String createAuthCode() {
        // TODO implement here
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }


//    private void showAuthcode(String authCode) {
//        // TODO implement here
//    }
//
//
//    private void showInputPreAuthcode() {
//        // TODO implement here
//    }


    //private -> public
    public boolean checkAuthCode(String authCode) {
        // TODO implement here
        return authcode.checkAuthCode(authCode);
    }

    public void setItem(int code, int count, int price){
        item.setItem(code, count, price);
    }

//    private void showManagingVm() {
//        // TODO implement here
//    }


//    private void setup() {
//        // TODO implement here
//        // cli 작성부분 주석 처리
//        System.out.println("=======Set Up========");
//        int tmp=0;
//        int tmp2=0;
//        int tmp3=0;
//        while(true){
//            System.out.print("다른 vmId를 입력해주세요: (종료: -1)");
//            tmp=sc.nextInt();
//            System.out.println();
//            if(tmp==-1){
//                break;
//            }
//            item.setVmid(tmp);
//            System.out.println("vmID: "+Integer.toString(tmp)+" setup 완료");
//        }
//
//        System.out.print("매니저 ID를 입력해주세요(Int): ");
//        tmp=sc.nextInt();
//        item.setMid(tmp);
//        System.out.println("매니저 ID: "+Integer.toString(tmp)+" setup 완료");
//
//        System.out.print("카드번호를 입력해주세요(Int): ");
//        tmp=sc.nextInt();
//        System.out.println();
//        System.out.print("카드잔고를 입력해주세요(Int): ");
//        tmp2=sc.nextInt();
//        item.setCard(tmp,tmp2);
//
//        System.out.println("카드번호: "+Integer.toString(tmp)+" 카드잔고: "+Integer.toString(tmp2)+" setup완료");
//
//        while(true){
//            System.out.print("음료 코드를 입력해주세요(Int): (종료: -1)");
//            tmp=sc.nextInt();
//            System.out.println();
//            if(tmp==-1){
//                break;
//            }
//
//            System.out.print("음료 개수를 입력해주세요(Int): (종료: -1)");
//            tmp2= sc.nextInt();
//            System.out.println();
//            if(tmp2==-1){
//                break;
//            }
//
//            System.out.print("음료 금액을 입력해주세요(Int): (종료: -1)");
//            tmp3= sc.nextInt();
//            System.out.println();
//            if(tmp3==-1){
//                break;
//            }
//
//            item.setItem(tmp,tmp2,tmp3);
//            System.out.println("음료코드: "+Integer.toString(tmp)+" 음료개수: "+Integer.toString(tmp2)+
//                    "음료금액: "+Integer.toString(tmp3)+ " setup완료");
//        }
//
//        System.out.println("Set Up을 종료합니다...");
//
//    }





//    private void showManageStock() {
//        // TODO implement here
//    }


    //private -> public
    public void update(int code, int count) { //managestock
        // TODO implement here
        item.update(code, count);
    }

}