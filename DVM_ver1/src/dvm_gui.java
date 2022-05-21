import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//dvm_gui 하나의 클래스의 내부 프레임 함수들로 바꿈
//dvm_gui 안에 VM 객체를 하나 만들어서 운영하는게 제일 편할듯
//이에 따라 vm 안의 return값이 추가/변경되거나, private->public 으로 함수가 변경될 것도 생길듯
class dvm_gui {

    public void seleckMode(){
        JFrame frame = new JFrame();
        frame.setTitle("select mode");
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(0,3));

        JButton selectMode_item_btn = new JButton("음료 선택");
        JButton selectMode_prepay_btn = new JButton("선결제 수령");
        JButton selectMode_manager_btn = new JButton("매니저");

        selectMode_item_btn.addActionListener(new ActionListener() { //음료 선택
            @Override
            public void actionPerformed(ActionEvent e) {


                frame.dispose();
            }
        });

        selectMode_prepay_btn.addActionListener(new ActionListener() { //선결제 수령
            @Override
            public void actionPerformed(ActionEvent e) {


                frame.dispose();
            }
        });

        selectMode_manager_btn.addActionListener(new ActionListener() { //매니져 접근
            @Override
            public void actionPerformed(ActionEvent e) {


                frame.dispose();
            }
        });

        frame.add(selectMode_item_btn);
        frame.add(selectMode_prepay_btn);
        frame.add(selectMode_manager_btn);

        frame.setVisible(true);
    }

    public void setUp() {
        JFrame frame = new JFrame();
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
        frame.setTitle("set up");
        frame.setSize(500, 500);
        frame.setLayout(null);

        for (int i = 0; i < setUp_menus.length; i++) {
            setUp_menu_label[i] = new JLabel(setUp_menus[i]);
            setUp_menu_label[i].setBounds(60, i * 50 + 30, 100, 30);
            setUp_menu_label[i].setHorizontalAlignment(JLabel.RIGHT);
            setUp_tf[i] = new JTextField("");
            setUp_tf[i].setBounds(190, i * 50 + 30, 150, 30);

            frame.add(setUp_menu_label[i]);
            frame.add(setUp_tf[i]);
        }

        JButton checkA = new JButton("V");
        JButton checkB = new JButton("V");
        JButton checkCD = new JButton("V");
        JButton checkEF = new JButton("V");

        checkA.setBounds(350, 30, 50, 30);
        checkB.setBounds(350, 80, 50, 30);
        checkCD.setBounds(350, 160, 50, 30);
        checkEF.setBounds(350, 260, 50, 30);

        frame.add(checkA);
        frame.add(checkB);
        frame.add(checkCD);
        frame.add(checkEF);

        JButton setUp_OK = new JButton("OK");
        setUp_OK.setBounds(200, 330, 100, 30);
        frame.add(setUp_OK);

        frame.setVisible(true);
    }

    public void selectItem() {
        JFrame frame = new JFrame();
        final String[] item_no = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        JButton[] item_buttons = new JButton[20];
        frame.setTitle("select item");
        frame.setSize(800, 500);
        frame.setLayout(null);

        for (int i = 0; i < item_buttons.length; i++) {
            item_buttons[i] = new JButton(item_no[i]);
            if (i < 10) {
                item_buttons[i].setBounds(i * 70 + 20, 20, 60, 30);
            } else {
                item_buttons[i].setBounds((i - 10) * 70 + 20, 60, 60, 30);
            }
            frame.add(item_buttons[i]);
        }
        JLabel quant_label = new JLabel("개수");
        quant_label.setBounds(20, 200, 50, 30);
        frame.add(quant_label);

        JTextField quant_tf = new JTextField("");
        quant_tf.setBounds(80, 200, 100, 30);
        frame.add(quant_tf);

        JButton check_btn = new JButton("V");
        check_btn.setBounds(190, 200, 50, 30);
        frame.add(check_btn);

        JButton OK_btn = new JButton("OK");
        OK_btn.setBounds(140, 280, 100, 30);
        frame.add(OK_btn);

        JLabel chosen_label = new JLabel("선택 항목");
        chosen_label.setBounds(350, 135, 100, 30);
        frame.add(chosen_label);

        JTextArea chosen_ta = new JTextArea("");
        chosen_ta.setBounds(300, 170, 200, 180);
        chosen_ta.setEditable(false);
        frame.add(chosen_ta);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OK_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
    }

    public void guideOtherMachine(){
        JFrame frame = new JFrame();
        frame.setTitle("guide other machine");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel guide_title = new JLabel("GUIDE");
        guide_title.setBounds(100,100,200,50);
        frame.add(guide_title);

        JLabel guide_set1 = new JLabel("DVM ID : ");
        guide_set1.setBounds(150,180,70,30);
        frame.add(guide_set1);

        JLabel guide_DVMId = new JLabel("tempId");
        guide_DVMId.setBounds(230,180,100,30);
        frame.add(guide_DVMId);

        JLabel guide_setx = new JLabel("X : ");
        guide_setx.setBounds(150,220,30,30);
        frame.add(guide_setx);

        JLabel guide_sety = new JLabel("Y : ");
        guide_sety.setBounds(250,220,30,30);
        frame.add(guide_sety);

        JLabel guide_x = new JLabel("temp");
        JLabel guide_y = new JLabel("temp");
        guide_x.setBounds(190,220,50,30);
        guide_y.setBounds(290,220,50,30);
        frame.add(guide_x);
        frame.add(guide_y);


        JButton guideDVM_prepay = new JButton("선결제하기");
        JButton cancel_btn = new JButton("x");
        guideDVM_prepay.setBounds(150,300,100,30);
        cancel_btn.setBounds(300,300,60,30);
        frame.add(guideDVM_prepay);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    public void payment(int payType){
        JFrame frame = new JFrame();
        frame.setTitle("guide other machine");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel cardNum_label = new JLabel("카드 번호");
        cardNum_label.setBounds(150,150,100,30);
        JTextField cardNum_tf = new JTextField("temp num");
        cardNum_tf.setBounds(255,150,100,30);
        frame.add(cardNum_label);
        frame.add(cardNum_tf);

        JButton payment_btn = new JButton("결제하기");
        payment_btn.setBounds(150,300,100, 40);
        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(300,300,100, 40);
        frame.add(payment_btn);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    public void prepaymentSuccess(){
        JFrame frame = new JFrame();
        frame.setTitle("prepayment success");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel authCode_label = new JLabel("인증코드 : ");
        authCode_label.setBounds(100,30,100,30);
        JLabel authCode = new JLabel("temp code");
        authCode.setBounds(255,30,100,30);
        frame.add(authCode_label);
        frame.add(authCode);

        JLabel DVMId_label = new JLabel("DVM ID : ");
        DVMId_label.setBounds(100,80,100,30);
        JLabel DVMId = new JLabel("temp id");
        DVMId.setBounds(255,80,100,30);
        frame.add(DVMId_label);
        frame.add(DVMId);

        JLabel set_Xpos = new JLabel("X : ");
        set_Xpos.setBounds(100,130,30,30);
        JLabel Xpos = new JLabel("temp x pos");
        Xpos.setBounds(135,130,70,30);

        JLabel set_Ypos = new JLabel("Y : ");
        set_Ypos.setBounds(235,130,30,30);
        JLabel Ypos = new JLabel("temp y pos");
        Ypos.setBounds(270,130,70,30);

        frame.add(set_Xpos);
        frame.add(set_Ypos);
        frame.add(Xpos);
        frame.add(Ypos);

        JButton prepaySuccess_OK_btn = new JButton("OK");
        prepaySuccess_OK_btn.setBounds(200,250,100,30);
        frame.add(prepaySuccess_OK_btn);

        frame.setVisible(true);
    }

    public void readPrepayAuthCode(){
        JFrame frame = new JFrame();
        frame.setTitle("read authentication code");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel input_label = new JLabel("인증코드 입력");
        input_label.setBounds(100,150,100,30);
        JTextField inputAuthCode_tf = new JTextField("");
        inputAuthCode_tf.setBounds(210,150,150,30);
        JButton inputCheck_btn = new JButton("V");
        inputCheck_btn.setBounds(370,150,50,30);

        frame.add(input_label);
        frame.add(inputAuthCode_tf);
        frame.add(inputCheck_btn);

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(200,300,100,30);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    public void successUI(){
        JFrame frame = new JFrame();
        frame.setTitle("success");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel successMessage_label = new JLabel("성공");
        successMessage_label.setFont(new Font("",Font.PLAIN,40));
        successMessage_label.setBounds(200,130,100,70);
        JButton successOK_btn = new JButton("OK");
        successOK_btn.setBounds(200,250,100,50);

        frame.add(successMessage_label);
        frame.add(successOK_btn);

        frame.setVisible(true);
    }

    public void readManagerAuthCode(){
        JFrame frame = new JFrame();
        frame.setTitle("read manager code");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel input_label = new JLabel("Manager ID");
        input_label.setBounds(100,150,100,30);
        JTextField inputAuthCode_tf = new JTextField("");
        inputAuthCode_tf.setBounds(210,150,150,30);
        JButton inputCheck_btn = new JButton("V");
        inputCheck_btn.setBounds(370,150,50,30);

        frame.add(input_label);
        frame.add(inputAuthCode_tf);
        frame.add(inputCheck_btn);

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(200,300,100,30);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    public void managingVM(){
        JFrame frame = new JFrame();
        frame.setTitle("managing vm");
        frame.setSize(500,500);
        frame.setLayout(null);

        JButton manageVm_stock_btn = new JButton("Manage Stock");
        manageVm_stock_btn.setBounds(200,100,100,100);
        JButton manageVm_setUp_btn = new JButton("Set Up");
        manageVm_setUp_btn.setBounds(200,230,100,100);
        JButton manageVm_cancel = new JButton("X");
        manageVm_cancel.setBounds(400,400,50,40);


        frame.add(manageVm_stock_btn);
        frame.add(manageVm_setUp_btn);
        frame.add(manageVm_cancel);

        frame.setVisible(true);
    }

    public void manageStock(){
        JFrame frame = new JFrame();
        frame.setTitle("manage stock");
        frame.setSize(500,500);
        frame.setLayout(null);

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


        frame.add(input_label1);
        frame.add(manageStock_itemCode_tf);
        frame.add(input_label2);
        frame.add(manageStock_itemQuant_tf);
        frame.add(manageStock_OK_btn);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }
}