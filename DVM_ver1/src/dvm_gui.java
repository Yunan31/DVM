import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//dvm_gui 하나의 클래스의 내부 프레임 함수들로 바꿈
//dvm_gui 안에 VM 객체를 하나 만들어서 운영하는게 제일 편할듯
//이에 따라 vm 안의 return값이 추가/변경되거나, private->public 으로 함수가 변경될 것도 생길듯
class dvm_gui {

    private VM vm;
    //결제에 쓰이는 음료 코드, 개수
    private int[] payItem = new int[2];
    //안내에 쓰이는 other VM 좌표
    private int[] position = new int[2];
    dvm_gui(){
        vm = new VM();
    }

    public void selectMode(){
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
                selectItem();
                frame.dispose();
            }
        });

        selectMode_prepay_btn.addActionListener(new ActionListener() { //선결제 수령
            @Override
            public void actionPerformed(ActionEvent e) {
                readPrepayAuthCode();
                frame.dispose();
            }
        });

        selectMode_manager_btn.addActionListener(new ActionListener() { //매니져 접근
            @Override
            public void actionPerformed(ActionEvent e) {
                readManagerAuthCode();
                frame.dispose();
            }
        });

        frame.add(selectMode_item_btn);
        frame.add(selectMode_prepay_btn);
        frame.add(selectMode_manager_btn);

        frame.setVisible(true);
    }

    public void setUp() throws InterruptedException {
        vm.start();
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

        checkA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(setUp_tf[0].getText().length()!=0)
                    vm.item.setVmid(Integer.parseInt(setUp_tf[0].getText()));
                setUp_tf[0].setText("");
            }
        });
        checkB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(setUp_tf[1].getText().length()!=0)
                    vm.item.setMid(Integer.parseInt(setUp_tf[1].getText()));
                setUp_tf[1].setText("");
            }
        });
        checkCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(setUp_tf[2].getText().length()!=0&&setUp_tf[3].getText().length()!=0)
                    vm.item.setCard(
                            Integer.parseInt(setUp_tf[2].getText()),
                            Integer.parseInt(setUp_tf[3].getText())
                    );
                setUp_tf[2].setText("");
                setUp_tf[3].setText("");
            }
        });
        checkEF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(setUp_tf[4].getText().length()!=0&&setUp_tf[5].getText().length()!=0)
                    vm.item.setItem(
                            Integer.parseInt(setUp_tf[4].getText()),
                            Integer.parseInt(setUp_tf[5].getText())
                    );
                setUp_tf[4].setText("");
                setUp_tf[5].setText("");
            }
        });

        checkA.setBounds(350, 30, 50, 30);
        checkB.setBounds(350, 80, 50, 30);
        checkCD.setBounds(350, 160, 50, 30);
        checkEF.setBounds(350, 260, 50, 30);

        frame.add(checkA);
        frame.add(checkB);
        frame.add(checkCD);
        frame.add(checkEF);

        JButton setUp_OK = new JButton("OK");
        setUp_OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });
        setUp_OK.setBounds(200, 330, 100, 30);
        frame.add(setUp_OK);

        frame.setVisible(true);
    }

    public void selectItem() {
        //음료 코드와 음료 개수
        int[] code = new int[1];
        int[] num = new int[1];
        //결제될 음료 코드와 음료 개수
        JFrame frame = new JFrame();
        final String[] item_no = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        JButton[] item_buttons = new JButton[20];

        frame.setTitle("select item");
        frame.setSize(800, 500);
        frame.setLayout(null);

        JLabel chosen_label = new JLabel("선택 항목");
        chosen_label.setBounds(350, 135, 100, 30);
        frame.add(chosen_label);

        JTextArea chosen_ta = new JTextArea("");
        chosen_ta.setBounds(300, 170, 200, 180);
        chosen_ta.setEditable(false);
        frame.add(chosen_ta);

        JLabel quant_label = new JLabel("개수");
        quant_label.setBounds(20, 200, 50, 30);
        frame.add(quant_label);

        JTextField quant_tf = new JTextField("");
        quant_tf.setBounds(80, 200, 100, 30);
        frame.add(quant_tf);

        JButton check_btn = new JButton("V");
        check_btn.setBounds(190, 200, 50, 30);
        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quant_tf.getText().length()!=0)
                {
                    num[0] = Integer.parseInt(quant_tf.getText());
                    chosen_ta.setText((code[0]+1) + "번 음료 / 개수 : " + num[0]);
                }
                quant_tf.setText("");
            }
        });
        frame.add(check_btn);

        JButton OK_btn = new JButton("OK");
        OK_btn.setBounds(140, 280, 100, 30);
        OK_btn.addActionListener(new ActionListener() {
            String itemStockCheck="";
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                     itemStockCheck = vm.selectItem(code[0], num[0]);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                payItem[0] = code[0];
                payItem[1] = num[0];
                if(itemStockCheck.equals("our")){
                    payment(0, payItem[0], payItem[1],"");
                }
                else if(itemStockCheck.equals("none")){ //요청을 보냈는데 다른 vm에도 없음
                    //코드 추가
                }
                else{ //다른 애에 있음

                    guideOtherMachine(vm.getPosition(),itemStockCheck);
                }
                frame.dispose();
            }
        });
        frame.add(OK_btn);

        for (int i = 0; i < item_buttons.length; i++) {
            item_buttons[i] = new JButton(item_no[i]);
            if (i < 10) {
                item_buttons[i].setBounds(i * 70 + 20, 20, 60, 30);
            } else {
                item_buttons[i].setBounds((i - 10) * 70 + 20, 60, 60, 30);
            }
            int finalI = i;
            item_buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    code[0] = finalI;
                }
            });
            frame.add(item_buttons[i]);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //temp 선언해둔것들 다 지움
    public void guideOtherMachine(int[] pos,String dstId){
        JFrame frame = new JFrame();
        frame.setTitle("guide other machine");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel guide_title = new JLabel("GUIDE");
        guide_title.setBounds(100,100,200,50);
        frame.add(guide_title);

        JLabel guide_setx = new JLabel("X : " + pos[0]);
        guide_setx.setBounds(150,220,30,30);
        frame.add(guide_setx);

        JLabel guide_sety = new JLabel("Y : " + pos[1]);
        guide_sety.setBounds(250,220,30,30);
        frame.add(guide_sety);

        JButton guideDVM_prepay = new JButton("선결제하기");
        guideDVM_prepay.setBounds(150,300,100,30);
        guideDVM_prepay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment(1, payItem[0], payItem[1],dstId);
                frame.dispose();
            }
        });


        JButton cancel_btn = new JButton("x");
        cancel_btn.setBounds(300,300,60,30);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });

        frame.add(guideDVM_prepay);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    //int payType 인자 추가
    //payType에 따라 선결제, 일반결제를 판단
    //0 -> 일반결제 , 1 -> 선결제
    //int code, int count 인자 추가
    public void payment(int payType, int code, int count,String dstID){
        JFrame frame = new JFrame();
        frame.setTitle("guide other machine");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel cardNum_label = new JLabel("카드 번호");
        cardNum_label.setBounds(150,150,100,30);
        JTextField cardNum_tf = new JTextField("");
        cardNum_tf.setBounds(255,150,100,30);
        frame.add(cardNum_label);
        frame.add(cardNum_tf);

        JButton payment_btn = new JButton("결제하기");
        payment_btn.setBounds(150,300,100, 40);
        payment_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cardNum_tf.getText().length()!=0) {
                    if (payType == 0) { //일반결제
                        boolean isValid = vm.normalPayment(Integer.parseInt(cardNum_tf.getText()), payItem[0], payItem[1]);
                        if (isValid) {
                            successUI();
                            frame.dispose();
                        }
                    } else { //선결제
                        String authCode = null;
                        try {
                            authCode = vm.prePayment(Integer.parseInt(cardNum_tf.getText()), payItem[0], payItem[1],dstID);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        if (authCode.length() != 0) {
                            prepaymentSuccess(authCode);
                            frame.dispose();
                        }
                    }
                    cardNum_tf.setText("");
                }
            }
        });

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(300,300,100, 40);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });
        frame.add(payment_btn);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    public void prepaymentSuccess(String authCode){
        JFrame frame = new JFrame();
        frame.setTitle("prepayment success");
        frame.setSize(500,500);
        frame.setLayout(null);

        JLabel authCode_label = new JLabel("인증코드 : " + authCode);
        authCode_label.setBounds(100,30,200,30);
        frame.add(authCode_label);

        JLabel set_Xpos = new JLabel("X : " + position[0]);
        set_Xpos.setBounds(100,130,30,30);

        JLabel set_Ypos = new JLabel("Y : " + position[1]);
        set_Ypos.setBounds(235,130,30,30);

        frame.add(set_Xpos);
        frame.add(set_Ypos);

        JButton prepaySuccess_OK_btn = new JButton("OK");
        prepaySuccess_OK_btn.setBounds(200,250,100,30);
        prepaySuccess_OK_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });

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
        inputCheck_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputAuthCode_tf.getText().length()!=0){
                    boolean isValid = vm.checkAuthCode(inputAuthCode_tf.getText());
                    if(isValid){
                        successUI();
                        frame.dispose();
                    }
                    inputAuthCode_tf.setText("");
                }
            }
        });

        frame.add(input_label);
        frame.add(inputAuthCode_tf);
        frame.add(inputCheck_btn);

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(200,300,100,30);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });
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
        successOK_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });

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
        inputCheck_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputAuthCode_tf.getText().length()!=0){
                    boolean isValid = vm.checkManagerAccess(Integer.parseInt(inputAuthCode_tf.getText()));
                    if(isValid){
                        managingVM();
                        frame.dispose();
                    }
                    inputAuthCode_tf.setText("");
                }
            }
        });

        frame.add(input_label);
        frame.add(inputAuthCode_tf);
        frame.add(inputCheck_btn);

        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(200,300,100,30);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });
        frame.add(cancel_btn);

        frame.setVisible(true);
    }

    public void managingVM(){
        JFrame frame = new JFrame();
        frame.setTitle("managing vm");
        frame.setSize(500,500);
        frame.setLayout(null);

        JButton manageVm_stock_btn = new JButton("Manage Stock");
        manageVm_stock_btn.setBounds(150,100,200,100);
        manageVm_stock_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageStock();
                frame.dispose();
            }
        });
        JButton manageVm_setUp_btn = new JButton("Set Up");
        manageVm_setUp_btn.setBounds(150,230,200,100);
        manageVm_setUp_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //리셋기능 필요한가 잘 모르겠음
                vm = new VM();
                try {
                    setUp();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
        JButton manageVm_cancel = new JButton("X");
        manageVm_cancel.setBounds(400,400,50,40);
        manageVm_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });


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
        manageStock_OK_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageStock_itemCode_tf.getText().length() != 0 && manageStock_itemQuant_tf.getText().length() != 0) {
                    vm.update(
                            Integer.parseInt(manageStock_itemCode_tf.getText()),
                            Integer.parseInt(manageStock_itemQuant_tf.getText())
                    );
                    manageStock_itemCode_tf.setText("");
                    manageStock_itemQuant_tf.setText("");
                }
            }
        });
        JButton cancel_btn = new JButton("X");
        cancel_btn.setBounds(300,300,100,30);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
                frame.dispose();
            }
        });

        frame.add(input_label1);
        frame.add(manageStock_itemCode_tf);
        frame.add(input_label2);
        frame.add(manageStock_itemQuant_tf);
        frame.add(manageStock_OK_btn);
        frame.add(cancel_btn);

        frame.setVisible(true);
    }
}