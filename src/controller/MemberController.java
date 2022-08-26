package controller;

import data.Member;
import infra.Container;
import infra.Request;
import service.MemberService;
import utils.Util;

import java.util.Scanner;

public class MemberController implements Controller{

    private Scanner sc;
    private MemberService memberService;

    public MemberController(){
        this.sc = Container.sc;
        this.memberService = Container.memberService;

    }

    @Override
    public void execute(Request request) {

        switch (request.getTarget()){
            case "join":
                saveMember();
                break;
            case "login":
                login(request);
                break;
            case "logout":
                logout(request);
                break;
            case "detail":
                detail(request);
            default:
                System.out.println("올바른 요청을 보내주시기 바랍니다");
                break;
        }
    }

    public void saveMember(){

        System.out.println("== 회원가입 ==");

        System.out.print("아이디 : ");
        String loginId = sc.nextLine().trim();

        if(!memberService.isExistsByLoginId(loginId)){
            System.out.println("존재하지 않는 계정입니다");
            return;
        }

        System.out.println("비밀번호 : ");
        String password = sc.nextLine().trim();

        if (memberService.isCorrectInfo(loginId , password)){
            System.out.println("아이디 혹은 비밀번호가 정확하지 않습니다");
            return;
        }

        System.out.println("이름 : ");
        String name = sc.nextLine().trim();

        int memberId = memberService.saveMember(loginId,password,name);

        System.out.println(memberId + "번재 회원님 환영합니다");


    }

    public void login(Request request){

        System.out.println("== 로그인 ==");

        System.out.print("아이디 :");
        String loginId = sc.nextLine().trim();

        System.out.println("비밀번호 : ");
        String password = sc.nextLine().trim();

        request.login(loginId);

        System.out.println(loginId + "님 반갑습니다.");


    }

    public void logout(Request request){


        String logonMember = request.getLogonMemberId();
        System.out.println(logonMember + "님 로그아웃 되었습니다");


        request.logout();
    }

    public void detail(Request request){

        String paramKey = "loginId";

        if(Util.hasParam(request,paramKey)){
            System.out.println(paramKey + "파라미터가 필요합니다");

        }

        String loginId = request.getParameterStrValue(paramKey);

        Member findMember = memberService.getMemberByLoginId(loginId);

        if(findMember == null){
            System.out.println("해당회원은 존재하지 않습니다");
            return;
        }

        System.out.println("==" + loginId + "님의 정보 ==");
        System.out.println("아이디 : " +loginId);
        System.out.println("이름 : " + findMember.getName());
        System.out.println("가입일 : " + findMember.getRegDate());

    }

}
