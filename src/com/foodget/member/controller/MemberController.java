package com.foodget.member.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.foodget.member.model.MemberDto;
import com.foodget.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value="/member.html", method=RequestMethod.POST)
	public ModelAndView member(MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		memberService.join(memberDto);
		emailSMTP(memberDto.getEmail(), memberDto.getName());
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
	
	@RequestMapping(value="/idcheck.html", method=RequestMethod.GET)
	public @ResponseBody String idcheck(@RequestParam("email")String email) {
		int cnt = memberService.idCheck(email);
		JSONObject json = new JSONObject();
		json.put("cnt", cnt);
		json.get("cnt");
//		System.out.println("cnt : " +json.get("cnt") );
//		System.out.println("tostring : " +  json.toJSONString());
		return json.toJSONString();
	}
	
	@RequestMapping(value="/emailauth.html", method=RequestMethod.GET)
	public @ResponseBody String emailauth(@RequestParam("email")String email) {
		memberService.emailauth(email);
		return "/index";
	}
	
	@RequestMapping(value="/login.html", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("email")String email, @RequestParam("password")String password, Model model) {
		ModelAndView mav = new ModelAndView();
		MemberDto memberDto = memberService.login(email, password);
		System.out.println(email);
		if(memberDto != null) {
			model.addAttribute("userInfo", memberDto);
		} else {
			mav.addObject("loginFail", "loginFail");
		}
		
		mav.setViewName("/index");
		return mav;
	}
	
	@RequestMapping(value="/logout.html", method=RequestMethod.POST)
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "/index";
	}
	
	public String emailSMTP(String receiver, String name) {
		
		Properties p = new Properties();
		

		// SMTP 서버의 계정 설정
		// Naver와 연결할 경우 네이버 아이디 지정
		// Google과 연결할 경우 본인의 Gmail 주소
		p.put("mail.smtp.user", "tmqehh");

		// SMTP 서버 정보 설정
		// 네이버일 경우 smtp.naver.com
		// Google일 경우 smtp.gmail.com
		p.put("mail.smtp.host", "smtp.gmail.com");
		    
		// 아래 정보는 네이버와 구글이 동일하므로 수정하지 마세요.
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try {
		    Authenticator auth = new SMTPAuthenticator();
		    System.out.println(auth);
		    Session ses = Session.getInstance(p, auth);

		    // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
		    ses.setDebug(true);
		        
		    // 메일의 내용을 담기 위한 객체
		    MimeMessage msg = new MimeMessage(ses);

		    // 제목 설정
		    msg.setSubject(name+"님 환영합니다! 이메일을 인증해주세요");
		        
		    // 보내는 사람의 메일주소
		    Address fromAddr = new InternetAddress("tmqeh@naver.com");
		    msg.setFrom(fromAddr);
		        
		    // 받는 사람의 메일주소
		    Address toAddr = new InternetAddress(receiver);
		    msg.addRecipient(Message.RecipientType.TO, toAddr);
		        
		    // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
		    String content = "환영합니다! "+name+"님의 이메일 주소를 인증해 주세요.<br>";
		    content += name+ "님, 푸드득에 가입하신 것을 환영해요!<br>";
		    content += "아래 링크를 눌러 이메일 인증을 완료해주세요. :)<br>";
		    content += "<a href=\"http://192.168.12.121/foodget/member/emailauth.html?email="+receiver+"\">이메일 인증하기</a>";
		    msg.setContent(content, "text/html;charset=UTF-8");
		        
		    // 발송하기
		    Transport.send(msg);
		        
		} catch (Exception mex) {
		    mex.printStackTrace();
//		    System.out.println("실패");
		    return "/index";
		}
//		System.out.println("성공");
		return "/index";
	}
	
	public class SMTPAuthenticator extends javax.mail.Authenticator{
		
		public PasswordAuthentication getPasswordAuthentication() {
	        // 네이버나 Gmail 사용자 계정 설정.
	        // Gmail의 경우 @gmail.com을 제외한 아이디만 입력한다.
			System.out.println("계정 입력");
	        return new PasswordAuthentication("tmqehh", "wlsdn201");
		}
	}
}
