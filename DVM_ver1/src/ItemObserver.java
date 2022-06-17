import javax.swing.*;

public class ItemObserver implements Observer{
    @Override
    public void update(int code, int count) {
        JOptionPane jOptionPane = new JOptionPane();
        jOptionPane.showMessageDialog(null, "음료 코드: " + code + ", 음료 개수: " + count, "재고 업데이트됨", JOptionPane.INFORMATION_MESSAGE);
    }
}
