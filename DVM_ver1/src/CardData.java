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

/*    public String checkCard(int cardNum, int code, int count) { //cardNum 이외에 계산해야 하는 값이 필요함. (원래는 인자에 cardNum밖에 없었음)
        //원래 item 에 있던거
        // TODO implement here
        if(this.cardNum != cardNum){
            System.out.println("카드 번호가 유효하지 않습니다.");    // 테스트용
            return "false";
        }

        if(this.cardBalance<itemPrice[code]*count){
            System.out.println("결제 금액 :"+itemPrice[code]*count+", 보유 금액 :"+this.cardBalance);      // 테스트용
            return false;
        }

        //itemOut() => 시퀀스에 있는데 굳이 필요 없어보임 (UI적인 느낌?)
        updateItemStock(code, count);
        this.cardBalance -= itemPrice[code]*count;      // 카드 잔액 차감
        System.out.println("잔액 :"+this.cardBalance);    // 테스트용
        return true;
    }*/

    public boolean isNotValidCard(int cardNum){
        // 이건 isValidCard로 해도 상관없는데 저 편한대로 했어요
        if(this.cardNum != cardNum){
            // System.out.println("카드 번호가 유효하지 않습니다.");    // 테스트용
            return true;
        }
        else return false;
    }

}
