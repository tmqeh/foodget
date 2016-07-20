package com.foodget.member.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.foodget.member.model.MemberDto;
import com.foodget.member.service.MemberService;
import com.foodget.utill.StringMethod;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
@SessionAttributes("userInfo")
@RequestMapping("/member")
public class MemberController {
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value="/member.html", method=RequestMethod.POST)
	public ModelAndView member(MemberDto memberDto, @RequestParam String kakaoJson) {
		ModelAndView mav = new ModelAndView();
		if(kakaoJson!=null) {
			memberDto = makeKakaoMemberDto(kakaoJson);
		}
		else {
			emailSMTP(memberDto.getEmail(), memberDto.getName());
		}
		memberService.join(memberDto);
		mav.setViewName("redirect:/index.jsp");
		return mav;
	}
	
	@RequestMapping(value="/idcheck.html", method=RequestMethod.GET)
	public @ResponseBody String idcheck(@RequestParam("email")String email) {
		int cnt = memberService.idCheck(email);
		JSONObject json = new JSONObject();
		json.put("cnt", cnt);
		json.get("cnt");
		return json.toJSONString();
	}
	
	@RequestMapping(value="/emailauth.html", method=RequestMethod.GET)
	public @ResponseBody String emailauth(@RequestParam("email")String email) {
		memberService.emailauth(email);
		return "/index";
	}
	
	@RequestMapping(value="/login.html", method=RequestMethod.POST)
	public ModelAndView login(MemberDto memberDto, Model model, @RequestParam("kakaoJson") String kakaoJson, @RequestParam("kakaoflag") String  kakaoflag ) {
		ModelAndView mav = new ModelAndView();
		System.out.println("제이슨 : " + kakaoJson);
		if(kakaoflag.equals("kakao")) {
			memberDto = makeKakaoMemberDto(kakaoJson);
			int cnt = memberService.idCheck(memberDto.getEmail());
			if(cnt==0) {
				memberService.join(memberDto);
			}
		} 
		memberDto = memberService.login(memberDto, kakaoflag);
		if(memberDto != null) {
			model.addAttribute("userInfo", memberDto);
		} else {
			mav.addObject("loginFail", "loginFail");
		}		
		mav.setViewName("/index");
		return mav;
	}
	
	@RequestMapping(value="/logout.html", method=RequestMethod.GET)
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/modify.html", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, HttpSession session) throws IOException {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		
		String realPath = request.getSession().getServletContext().getRealPath("/picture");
		int maxSize = 3 * 1024 * 1024;
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(new Date());
		String saveDirectory = realPath + File.separator + today;
		
		System.out.println(saveDirectory);
		
		File file = new File(saveDirectory);
		if(!file.exists()) {
			file.mkdirs();
		} 
		
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8" ,new DefaultFileRenamePolicy());
		
		String member_originimg = multi.getOriginalFileName("picture");
		String member_saveimg = multi.getFilesystemName("picture");
		memberDto.setMember_originimg(member_originimg);
		memberDto.setMember_saveimg(member_saveimg);
		memberDto.setMember_savefolder(today);
		
		memberService.modify(memberDto);
//		sessionStatus.setComplete();
//		model.addAttribute("userInfo", memberDto);
		return "memberInfo";
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
	
	//카카오 Json MemberDto 변환
	public MemberDto makeKakaoMemberDto(String kakaoJson) {
		MemberDto memberDto = new MemberDto();
		JSONObject json = new JSONObject();
		json = StringMethod.getStringMethod().stringToJson(kakaoJson);
		memberDto.setEmail(json.get("id")+"");
		json = StringMethod.getStringMethod().stringToJson(json.get("properties")+"");
		memberDto.setName(json.get("nickname")+"");
		memberDto.setMember_saveimg(json.get("profile_image")+"");
		memberDto.setMember_originimg(json.get("profile_image")+"");
		memberDto.setPassword("1234");
		return memberDto;
	}
}
