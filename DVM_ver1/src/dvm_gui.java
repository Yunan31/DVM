import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dvm_gui extends JFrame {

    public dvm_gui()
    {
        super("select mode");
        setSize(500,500);
        setLayout(new GridLayout(0,3));

        JButton selectMode_item_btn = new JButton("음료 선택");
        JButton selectMode_prepay_btn = new JButton("선결제 수령");
        JButton selectMode_manager_btn = new JButton("매니저");

        selectMode_item_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame selectItem = new selectItem();
                selectItem.setVisible(true);

                dispose();
            }
        });

        selectMode_prepay_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame readPrepayAuthCode = new readPrepayAuthCode();
                readPrepayAuthCode.setVisible(true);

                dispose();
            }
        });

        selectMode_manager_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame readManagerAuthCode = new readManagerAuthCode();
                readManagerAuthCode.setVisible(true);

                dispose();
            }
        });

        this.add(selectMode_item_btn);
        this.add(selectMode_prepay_btn);
        this.add(selectMode_manager_btn);

        setVisible(true);
    }
}
class setUp extends JFrame{
    final String[] setUp_menus = {
            "Other DVM ID",
            "Manager ID",
            "카드 번호",
            "카드 잔액",
            "음료 코드",
            "음료 개수"
    };
    JLabel[] setUp_menu_label = new JLabel[6];
    JTextField[] setUp_tf = new JTextField[6];
    public setUp() {
        super("set up");
        setSize(500,500);
        setLayout(null);

        for(int i=0;i<setUp_menus.length;i++){
            setUp_menu_label[i] = new JLabel(setUp_menus[i]);
            setUp_menu_label[i].setBounds(60,i*50 +30,100,30);
            setUp_menu_label[i].setHorizontalAlignment(JLabel.RIGHT);
            setUp_tf[i] = new JTextField("");
            setUp_tf[i].setBounds(190,i*50+30, 150, 30);

            this.add(setUp_menu_label[i]);
            this.add(setUp_tf[i]);
        }

        JButton checkA = new JButton("V");
        JButton checkB = new JButton("V");
        JButton checkCD = new JButton("V");
        JButton checkEF = new JButton("V");

        checkA.setBounds(350,30,50,30);
        checkB.setBounds(350,80,50,30);
        checkCD.setBounds(350,160,50,30);
        checkEF.setBounds(350,260,50,30);

        this.add(checkA);
        this.add(checkB);
        this.add(checkCD);
        this.add(checkEF);

        JButton setUp_OK = new JButton("OK");
        setUp_OK.setBounds(200,330,100,30);
        this.add(setUp_OK);

       setVisible(true);

   }
}
class selectItem extends JFrame{
    final String[] item_no = {"1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20"};
    JButton[] item_buttons = new JButton[20];
    public selectItem(){
        super("select item");
        setSize(800,500);
        setLayout(null);

        for(int i=0;i<item_buttons.length;i++){
            item_buttons[i] = new JButton(item_no[i]);
            if(i<10) {
                item_buttons[i].setBounds(i * 70 + 20, 20, 60, 30);
            } else {
                item_buttons[i].setBounds((i-10) * 70 + 20, 60, 60, 30);
            }
            this.add(item_buttons[i]);
        }
        JLabel quant_label = new JLabel("개수");
        quant_label.setBounds(20,200,50,30);
        this.add(quant_label);

        JTextField quant_tf = new JTextField("");
        quant_tf.setBounds(80,200,100,30);
        this.add(quant_tf);

        JButton check_btn = new JButton("V");
        check_btn.setBounds(190,200,50,30);
        this.add(check_btn);

        JButton OK_btn = new JButton("OK");
        OK_btn.setBounds(140,280,100,30);
        this.add(OK_btn);

        JLabel chosen_label = new JLabel("선택 항목");
        chosen_label.setBounds(350,135,100,30);
        this.add(chosen_label);

        JTextArea chosen_ta = new JTextArea("");
        chosen_ta.setBounds(300,170,200,180);
        chosen_ta.setEditable(false);
        this.add(chosen_ta);

        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OK_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame dvm_gui = new dvm_gui();
                dvm_gui.setVisible(true);

                dispose();
            }
        });
    }
}
class guideOtherMachine extends JFrame{
    public guideOtherMachine(){
        super("guide other machine");
        setSize(500,500);
        setLayout(null);

        JLabel guide_title = new JLabel("GUIDE");
        guide_title.setBounds(100,100,200,50);
        this.add(guide_title);

        JLabel guide_set1 = new JLabel("DVM ID : ");
        guide_set1.setBounds(150,180,70,30);
        this.add(guide_set1);

        JLabel guide_DVMId = new JLabel("tempId");
        guide_DVMId.setBounds(230,180,100,30);
        this.add(guide_DVMId);

        JLabel guide_setx = new JLabel("X : ");
        guide_setx.setBounds(150,220,30,30);
        this.add(guide_setx);

        JLabel guide_sety = new JLabel("Y : ");
        guide_sety.setBounds(250,220,30,30);
        this.add(guide_sety);

        JLabel guide_x = new JLabel("temp");
        JLabel guide_y = new JLabel("temp");
        guide_x.setBounds(190,220,50,30);
        guide_y.setBounds(290,220,50,30);
        this.add(guide_x);
        this.add(guide_y);


        JButton guideDVM_prepay = new JButton("선결제하기");
        JButton cancel_btn = new JButton("x");
        guideDVM_prepay.setBounds(150,300,100,30);
        cancel_btn.setBounds(300,300,60,30);
        this.add(guideDVM_prepay);
        this.add(cancel_btn);

        setVisible(true);

    }
}
class payment extends JFrame{
    public payment(){
        super("guide other machine");
        setSize(500,500);
        setLayout(null);

        JLabel cardNum_label = new JLabel("카드 번호");
        cardNum_label.setBounds(150,150,100,30);
        JTextField cardNum_tf = new JTextField("temp num");
        cardNum_tf.setBounds(255,150,100,30);
        this.add(cardNum_label);
        this.add(cardNum_tf);

        JButton payment_btn = new JButton("결제하기");
        payment_btn.setBounds(150,300,100, 40);
        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(300,300,100, 40);
        this.add(payment_btn);
        this.add(cancel_btn);

        setVisible(true);
    }
}
class prepaymentSuccess extends JFrame{
    public prepaymentSuccess(){
        super("prepayment success");
        setSize(500,500);
        setLayout(null);

        JLabel authCode_label = new JLabel("인증코드 : ");
        authCode_label.setBounds(100,30,100,30);
        JLabel authCode = new JLabel("temp code");
        authCode.setBounds(255,30,100,30);
        this.add(authCode_label);
        this.add(authCode);

        JLabel DVMId_label = new JLabel("DVM ID : ");
        DVMId_label.setBounds(100,80,100,30);
        JLabel DVMId = new JLabel("temp id");
        DVMId.setBounds(255,80,100,30);
        this.add(DVMId_label);
        this.add(DVMId);

        JLabel set_Xpos = new JLabel("X : ");
        set_Xpos.setBounds(100,130,30,30);
        JLabel Xpos = new JLabel("temp x pos");
        Xpos.setBounds(135,130,70,30);

        JLabel set_Ypos = new JLabel("Y : ");
        set_Ypos.setBounds(235,130,30,30);
        JLabel Ypos = new JLabel("temp y pos");
        Ypos.setBounds(270,130,70,30);

        this.add(set_Xpos);
        this.add(set_Ypos);
        this.add(Xpos);
        this.add(Ypos);

        JButton prepaySuccess_OK_btn = new JButton("OK");
        prepaySuccess_OK_btn.setBounds(200,250,100,30);
        this.add(prepaySuccess_OK_btn);

        setVisible(true);

    }
}
class readPrepayAuthCode extends JFrame{
    public readPrepayAuthCode(){
        super("read authentication code");
        setSize(500,500);
        setLayout(null);

        JLabel input_label = new JLabel("인증코드 입력");
        input_label.setBounds(100,150,100,30);
        JTextField inputAuthCode_tf = new JTextField("");
        inputAuthCode_tf.setBounds(210,150,150,30);
        JButton inputCheck_btn = new JButton("V");
        inputCheck_btn.setBounds(370,150,50,30);

        this.add(input_label);
        this.add(inputAuthCode_tf);
        this.add(inputCheck_btn);

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(200,300,100,30);
        this.add(cancel_btn);

        setVisible(true);
    }
}
class successUI extends JFrame{
    public successUI(){
        super("success");
        setSize(500,500);
        setLayout(null);

        JLabel successMessage_label = new JLabel("성공");
        successMessage_label.setFont(new Font("",Font.PLAIN,40));
        successMessage_label.setBounds(200,130,100,70);
        JButton successOK_btn = new JButton("OK");
        successOK_btn.setBounds(200,250,100,50);

        this.add(successMessage_label);
        this.add(successOK_btn);

        setVisible(true);
    }
}
class readManagerAuthCode extends JFrame{
    public readManagerAuthCode(){
        super("read manager code");
        setSize(500,500);
        setLayout(null);

        JLabel input_label = new JLabel("Manager ID");
        input_label.setBounds(100,150,100,30);
        JTextField inputAuthCode_tf = new JTextField("");
        inputAuthCode_tf.setBounds(210,150,150,30);
        JButton inputCheck_btn = new JButton("V");
        inputCheck_btn.setBounds(370,150,50,30);

        this.add(input_label);
        this.add(inputAuthCode_tf);
        this.add(inputCheck_btn);

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(200,300,100,30);
        this.add(cancel_btn);

        setVisible(true);
    }
}
class managingVM extends JFrame{
    public managingVM(){
        super("managing vm");
        setSize(500,500);
        setLayout(null);

        JButton manageVm_stock_btn = new JButton("Manage Stock");
        manageVm_stock_btn.setBounds(200,100,100,100);
        JButton manageVm_setUp_btn = new JButton("Set Up");
        manageVm_setUp_btn.setBounds(200,230,100,100);
        JButton manageVm_cancel = new JButton("X");
        manageVm_cancel.setBounds(400,400,50,40);


        this.add(manageVm_stock_btn);
        this.add(manageVm_setUp_btn);
        this.add(manageVm_cancel);

        setVisible(true);
    }
}
class manageStock extends JFrame{
    public manageStock(){
        super("manage stock");
        setSize(500,500);
        setLayout(null);

        JLabel input_label1 = new JLabel("음료 코드");
        input_label1.setBounds(100,150,100,30);
        JTextField manageStock_itemCode_tf = new JTextField("");
        manageStock_itemCode_tf.setBounds(210,150,150,30);

        JLabel input_label2 = new JLabel("음료 개수");
        input_label2.setBounds(100,200,100,30);
        JTextField manageStock_itemQuant_tf = new JTextField("");
        manageStock_itemQuant_tf.setBounds(210,200,150,30);

        JButton manageStock_OK_btn = new JButton("OK");
        manageStock_OK_btn.setBounds(150,300,100,30);
        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(300,300,100,30);


        this.add(input_label1);
        this.add(manageStock_itemCode_tf);
        this.add(input_label2);
        this.add(manageStock_itemQuant_tf);
        this.add(manageStock_OK_btn);
        this.add(cancel_btn);

        setVisible(true);
    }
}