import DVM_Server.DVMServer;

public class Main {
    static DVMServer server;
    public static void main(String[] args) throws InterruptedException {

        //GUI 테스트 부분
        new DVM_GUI();

        System.out.println("wait ... Thread2 ");
        Thread1 thread = new Thread1();
        thread.start();

        System.out.println("wait ... Thread1 ");
        Thread.sleep(3000); //Thread 실행될때까지 대기
    }

    //Thread로 서버 실행하기 위해 static으로 삽입.
    static class Thread1 extends Thread{
        @Override
        public void run(){
            server = new DVMServer();

            try{
                System.out.println("Server running...");
                server.run();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

