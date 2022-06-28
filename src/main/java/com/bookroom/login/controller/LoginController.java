package com.bookroom.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookroom.login.NaverLoginBO;
import com.bookroom.user.service.UserService;
import com.bookroom.user.vo.UserVo;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
@RequestMapping("/Login")
public class LoginController {

	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private UserService userService;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	// 네아로 이동
	@RequestMapping
	public String login(Model model, HttpSession session) {
		System.out.println("로그인/");
		// 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("url", naverAuthUrl);
		return "login";
	}

	// 네이버 로그인 성공시 callback호출 메소드
	// 성공한다면 콜백URL에 code값과 state값이 전송됨
	// code = 접근토큰 발급에 사용됨
	// state = 애플리케이션이 생성한 상태 토큰 (위조방지)
	@RequestMapping(value = "/Callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {

		System.out.println("여기는 callback");

		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);

		// String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;

		// 값 뽑기
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		String userid = (String) response_obj.get("id");
		String username = (String) response_obj.get("name");
		String email = (String) response_obj.get("email");
		String mobile = (String) response_obj.get("mobile");
		String gender = (String) response_obj.get("gender");
		String age = (String) response_obj.get("age");
		String birthyear = (String) response_obj.get("birthyear");

		// 세션에 값 넣기
		session.setAttribute("userid", userid);
		session.setAttribute("username", username);
		session.setAttribute("email", email);
		session.setAttribute("mobile", mobile);
		session.setAttribute("gender", gender);
		session.setAttribute("age", age);
		session.setAttribute("birthyear", birthyear);
		
		// db에 유저 insert
		UserVo userVo = new UserVo(userid, username, email, mobile, gender, age, birthyear);
		userService.insertUser(userVo);
		
		return "redirect:/";
	} // callback end

	// 로그아웃
	@RequestMapping(value = "/Logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) throws IOException {
		System.out.println("로그아웃:" + session);
		session.invalidate();
		return "redirect:/";
	}
}