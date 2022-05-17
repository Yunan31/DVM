public class VM extends dvm_gui{

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


    private void msgRequest(int type, int code, int count) {
        // TODO implement here
    }


    private void msgReturn(int type, int code, int xPos, int yPos) {
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