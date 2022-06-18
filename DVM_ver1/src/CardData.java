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
        return this.cardNum != cardNum;
    }
}
