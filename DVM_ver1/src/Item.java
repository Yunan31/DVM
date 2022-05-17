
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

    private int vmid[];
    private int mid;
    private int cardNum;
    private int cardBalance;
    private int item[];
    private boolean isNone;
    private HashMap<Integer, String> authCode;
    private boolean isValid;


    public void setMid(int mid) {
        // TODO implement here
    }


    public void setVmid(int vmid) {
        // TODO implement here
    }


    public void setCard(int cardNum, int cardBalance) {
        // TODO implement here
    }


    public void setItem(int code, int count) {
        // TODO implement here
    }


    public boolean checkItemStock(int code, int count) {
        // TODO implement here
        return false;
    }


    public void insertAuthCode(int code, int count, String authCode) {
        // TODO implement here
    }


    public void updateItemStock(int code, int count) {
        // TODO implement here
    }


    public boolean checkCard(int cardNum) {
        // TODO implement here
        return false;
    }


    public boolean checkAuthCode(String authCode) {
        // TODO implement here
        return false;
    }


    public void deleteAuthCode(String authCode) {
        // TODO implement here
    }


    public void update(int code, int count) {
        // TODO implement here
    }
}