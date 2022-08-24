package infra;

import controller.Controller;

import java.util.Scanner;

public class Application {

    private Scanner sc = Container.sc;
    private boolean isActive = true;

    public void run(){

        while(isActive){
            System.out.print("명령어 : ");
            String inputUri = sc.nextLine().trim();

            if(inputUri.equals(".exit")){
                System.out.println("어플리케이션을 종료 합니다");
                break;
            }

            Request request = new Request(inputUri);

            if(!request.isValidRequest()){
                System.out.println("존재하지 않는 명령입니다");
                continue;
            }

            Controller controller = getController(request.getControllerCode());

            if(controller != null){
                controller.execute(request);
            }else{
                System.out.println("올바른 URI를 입력해주세요!");
            }
        }

    }

    public Controller getController(String code){
        switch (code){
            case "system":
                return Container.systemController;
            default:
                return null;
        }
    }

}