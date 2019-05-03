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
		model.addAttribute("publicKey", publicKey);
		return "register";
	}
}
