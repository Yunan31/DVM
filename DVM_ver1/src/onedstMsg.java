import DVM_Client.DVMClient;

public class onedstMsg extends requestMsg{
    onedstMsg(VMData vmData, String type, String code, int count, String dst, String authCode, int xPos, int yPos) {
        super(vmData, type, code, count, dst, authCode, xPos, yPos);
    }

    @Override
    void send(String msg) {
        String tmpDst = Character.toString(dst.charAt(dst.length()-1));

        System.out.println("tmpDst: "+tmpDst);
        DVMClient client = new DVMClient(vmData.getVmIp(Integer.parseInt(tmpDst)-1), msg);
        try {
            client.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
