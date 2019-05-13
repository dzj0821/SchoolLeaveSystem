package pers.dzj0821.SchoolLeaveSystem.controller;

import java.security.KeyPair;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@Controller
@RequestMapping("/user")
public class UserController {

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
	
	@RequestMapping("/info")
	public String info(Integer id, Model model) {
		//TODO 调用service层，完成视图
		return Messages.getString("UserInfoPage"); //$NON-NLS-1$
	}
}
