package pers.dzj0821.SchoolLeaveSystem.controller;

import java.security.KeyPair;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.dzj0821.SchoolLeaveSystem.Messages;

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
		return Messages.getString("LoginPage");
	}
}
