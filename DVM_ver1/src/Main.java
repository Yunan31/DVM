import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;

import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    static DVMServer server;
    public static void main(String[] args) throws InterruptedException {

        //GUI 테스트 부분
        //dvm_gui d = new dvm_gui();
        //d.setUp();
        VM vm = new VM();
        vm.start();

        System.out.println("wait ... Thread2 ");
        Thread1 thread = new Thread1();
        thread.start();

        System.out.println("wait ... Thread1 ");
        Thread.sleep(3000); //Thread 실행될때까지 대기
        //31까지 server socket test 부분. socket에서 메세지로 {srcId:"1", dstId:"2",msgType:"type",msgDescription:null} 이 값 넣으면 type 반환하고 삭제해줌.

//        while(true){
//            Thread.sleep(2000);
//            if(DVMServer.msgList.size()==0){
//                System.out.println("server not ok");
//                //DVMServer.msgList.get(DVMServer.msgList.size()-1).getDstID(); 이런 식으로 사용하면 되나.
//            }
//            else{
//                System.out.println(DVMServer.msgList.get(DVMServer.msgList.size()-1).getMsgType()); //이런 식으로 사용하면 되나.
//                DVMServer.msgList.remove(DVMServer.msgList.size()-1);
//            }
//        }


        //requestMsg("StockCheckRequest","12",3,"0","dlgusrb123",5,5); //==> 소켓으로 client port binding 오류가 나긴 하는데 되긴 됨
    }

    //임의로 테스트 하려고 가져온 코드 나중에 지울거임~
    private static void requestMsg(String type, String code, int count, String dst, String authCode, int xPos, int yPos) throws InterruptedException {
        Message msg = new Message();
        Message.MessageDescription msgDesc = new Message.MessageDescription();
        Serializer msg2json = new Serializer();
        //List<Integer> list = item.getVMId();

        if(type.equals("PrepaymentCheck")||type.equals("SalesCheckResponse")){
            msg.setSrcId(dst); //우리 Id는 5로 고정 => 얘도 item에 저장하고 가져오는 식으로 해야하나...? (귀찮음 ㅎㅎ)
            msg.setDstID("5");
        }
        else{
            msg.setSrcId("5"); //우리 Id는 5로 고정 => 얘도 item에 저장하고 가져오는 식으로 해야하나...? (귀찮음 ㅎㅎ)
            msg.setDstID(dst);
        }
        msg.setMsgType(type);
        msgDesc.setItemCode(code);
        msgDesc.setItemNum(count);
        msgDesc.setDvmXCoord(xPos); //이것도 위와 같은 의견. setup에서 정의하지 않음. 아마 연결하기 전에 값 줄건데 그걸로 고정
        msgDesc.setDvmYCoord(yPos); //이하동문.
        msgDesc.setAuthCode(authCode);
        msg.setMsgDescription(msgDesc);

        String jsonMsg = msg2json.message2Json(msg); //msg=>json

        DVMClient client = new DVMClient("localhost",jsonMsg);
        client.run();
    }


    //Thread로 서버 실행하기 위해 static으로 삽입.
    static class Thread1 extends Thread{
        @Override
        public void run(){
            server = new DVMServer();

            try{
                System.out.println("Server running...");
                server.run();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

