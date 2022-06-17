public class CardData {
    public CardData(){
    }

    private int cardNum;
    private int cardBalance;

    public void setCard(int cardNum, int cardBalance) {
        // TODO implement here
        System.out.println("카드 번호: "+Integer.toString(cardNum)+" 카드 잔액: "+Integer.toString(cardBalance));
        this.cardNum=cardNum;
        this.cardBalance=cardBalance;
    }

    public void withdrawBalance(int payAmount){
        this.cardBalance -= payAmount;
    }
    public int getCardBalance() {
        return cardBalance;
    }

    public boolean isNotValidCard(int cardNum){
        // 이건 isValidCard로 해도 상관없는데 저 편한대로 했어요
        if(this.cardNum != cardNum){
            // System.out.println("카드 번호가 유효하지 않습니다.");    // 테스트용
            return true;
        }
        else return false;
    }

}
