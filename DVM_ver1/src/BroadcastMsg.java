import DVM_Client.DVMClient;

public class BroadcastMsg extends Message {

    BroadcastMsg(VMData vmData, String type, String code, int count, String dst, String authCode, int xPos, int yPos) {
        super(vmData, type, code, count, dst, authCode, xPos, yPos);
    }

    @Override
    void send(String msg) {
        for(int i=0;i< vmData.getVmIpLength();i++){
            if(vmData.getVmIp(i).equals("our")||vmData.getVmIp(i).equals("null")){
                continue;
            }

            DVMClient client = new DVMClient(vmData.getVmIp(i), msg);
            try {
                client.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
