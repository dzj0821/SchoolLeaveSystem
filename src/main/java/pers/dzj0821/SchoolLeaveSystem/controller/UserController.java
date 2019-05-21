package pers.dzj0821.SchoolLeaveSystem.controller;

import java.security.KeyPair;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.UserService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public String register(Model model, HttpSession session) {
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
				session.getAttribute(Messages.getString("RSACreateTimestampSessionName"))); //$NON-NLS-1$
		return Messages.getString("RegisterPage"); //$NON-NLS-1$
	}

	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
				session.getAttribute(Messages.getString("RSACreateTimestampSessionName"))); //$NON-NLS-1$
		return Messages.getString("LoginPage"); //$NON-NLS-1$
	}
	
	@RequestMapping("/modify")
	@UserTypeRequired(UserType.NORMAL_USER)
	public String modify(Model model, HttpSession session) {
		KeyPair keyPair = (KeyPair) session.getAttribute(Messages.getString("RSAKeyPairSessionName")); //$NON-NLS-1$
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
		session.getAttribute(Messages.getString("RSACreateTimestampSessionName"))); //$NON-NLS-1$
		//TODO 添加nls标记
		return Messages.getString("ModifyUserInfoPage");
	}
	
	@GetMapping("/info")
	public String info(Integer id, Model model, HttpServletRequest request, HttpSession session) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		JSONResult result = userService.getUserInfo(id, sessionAdapter.getUser());
		if(result.getCode() == JSONCodeType.SUCCESS) {
			//TODO 封装model
			model.addAttribute("userInfoView", result.getData().get(Messages.getString("UserInfoDataUserName")));
			return "user/info";
		}
		model.addAttribute("result", result);
		return "error";
	}
}
