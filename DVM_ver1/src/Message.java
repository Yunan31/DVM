import GsonConverter.Serializer;

public abstract class Message {
    public VMData vmData;
    public String type;
    public String code;
    public int count;
    public String dst;
    public String authCode;
    public int xPos;
    public int yPos;

    Message(VMData vmData, String type, String code, int count, String dst, String authCode, int xPos, int yPos){
        this.vmData = vmData;
        this.type = type;
        this.code = code;
        this.count = count;
        this.dst = dst;
        this.authCode = authCode;
        this.xPos = xPos;
        this.yPos = yPos;
        sendMsg();
    }

    final void sendMsg(){
        String msg = setMsgDescription();
        send(msg);
    }

    private String setMsgDescription() {
        Model.Message msg = new Model.Message();
        Model.Message.MessageDescription msgDesc = new Model.Message.MessageDescription();
        Serializer msg2json = new Serializer();


        code = String.format( "%1$02d" , Integer.parseInt(code));
        System.out.println("바꾼 code: "+code);

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
        return jsonMsg;
    }

    abstract void send(String msg);
}
