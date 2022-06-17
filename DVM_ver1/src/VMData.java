import java.util.ArrayList;
import java.util.List;

public class VMData {
    public VMData(){}

    private List<Integer> vmid = new ArrayList<>();
    //이거 Team1 Team2 이런식으로 저장할거면 <String>으로 바꿔야하나요

    //"192.168.66.176","192,168.67.11","192.168.67.30","192.168.65.204","our","192.168.64.242"
    //192.168.157.16
    private String[] vmIp= {"127.0.0.1","null","null","null","our","null"};
    // ip 좌표 초기화 필요.. 근데 걍 초기화 안하고 값으로 박아버리면 안되나요

    public String srcId ="Team5";
    private int xPos = 5; //새로이 추가된 두 위아래 좌표놈들. setup에서 초기화 안해주므로 임의로 값 집어넣음.
    private int yPos = 10; //이하동문.
    //위 좌표 두개 초기화 필요

  /*  public List<Integer> getVMId(){ //msgRequest에 쓰이므로 get추가.
       return this.vmid;
    }*/   //안쓰이는 것 같은데,,, 지워도 될듯

    public void setVmid(int vmid) {
        // TODO implement here
        System.out.println("Set vm Id: "+vmid);
        this.vmid.add(vmid);
    }
    public void setVmIp(int index, String Ip){
        vmIp[index] = Ip;

        for(int i=0;i<vmIp.length;i++){
            System.out.print(vmIp[i] );
        }
        System.out.print("\n");
    }

    public int getVmIpLength(){
        return this.vmIp.length;
    }

    public String getVmIp(int i){
        return this.vmIp[i];
    }

    public int getXpos(){ //위에 새로이 추가하면서 추가된 놈
        return this.xPos;
    }
    public int getyPos(){//위에 새로이 추가하면서 추가된 놈
        return this.yPos;
    }

    public void setXYPos(int x, int y){
        this.xPos = x;
        this.yPos = y;
        System.out.println("xpos : "+xPos);
        System.out.println("ypos : "+yPos);
    }

}
