import java.util.HashMap;

public class AuthcodeData {
    public AuthcodeData(){
    }

    private HashMap<String,String> authCode = new HashMap<>(); //기존의 Integer, String에서 String, String으로 변환
    //앞의 String에 authCode가 들어가고 뒤의 String엔 "음료코드 개수" 형식으로 띄어쓰기해서
    //들어갈 예정. split 사용해서 쓰면 됨.
    //즉 authcode를 키값으로 찾을것임.

    public void insertAuthCode(int code, int count, String authCode) { //msg로 들어온 값 받아와서 해시맵에 저장
        // TODO implement here
        String items = Integer.toString(code);
        items += " "+ Integer.toString(count);
        this.authCode.put(authCode,items);
        System.out.println("insert authCode: "+authCode + "items: "+items);
    }

    public boolean checkAuthCode(String authCode) {
        // TODO implement here
        if(!this.authCode.containsKey(authCode)){
            System.out.println("인증코드 인증 실패");
            return false;
        }

        String code_count = this.authCode.get(authCode);
        int code = Integer.parseInt(code_count.split(" ")[0]);
        int count = Integer.parseInt(code_count.split(" ")[1]);

        //원래 여기 ItemOut이 있어서 code count값이 필요한데 없어서 필요가 없어짐 ㅋㅋ
        //그리고 이미 InsertCode에서 updateItemStock으로 재고를 줄여준 상태임. 또 줄이면 안됨.

        deleteAuthCode(authCode);

        return true;
    }
    public void deleteAuthCode(String authCode) {
        // TODO implement here
        System.out.println("인증코드: "+authCode+" 확인 및 삭제 완료");
        this.authCode.remove(authCode);
    }
}
