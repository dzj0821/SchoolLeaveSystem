package pers.dzj0821.SchoolLeaveSystem.controller;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.adapter.ModelAdapter;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.pojo.view.UserInfoView;
import pers.dzj0821.SchoolLeaveSystem.service.UserService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

/**
 * 用户模块页面的Controller
 * 
 * @author dzj0821
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 注册页面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/register")
	public String register(Model model, HttpSession session) {
		/* KeyPair 生成公钥的工具类 */
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair();
		// 将RSA公钥Base64加密后放入页面中
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
				sessionAdapter.getRSACreateTimestamp());
		return Messages.getString("RegisterPage"); //$NON-NLS-1$
	}

	@PostMapping("/register")
	public String registerRequest(@RequestParam String username, @RequestParam String password,
			@RequestParam String name, @RequestParam String telephone, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// 从session中获取用于解密密码的RSA密钥对
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair();
		JSONResult result = userService.register(username, password, name, telephone, keyPair.getPrivate());
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if (result.getCode() == JSONCodeType.SUCCESS) {
			try {
				response.sendRedirect("login");
			} catch (IOException e) {
				modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
				return "error";
			}
			return null;
		}
		modelAdapter.setErrorResult(result);
		return "error";
	}

	/**
	 * 批量注册注册页面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/batchRegister")
	public String batchRegister(Model model, HttpSession session) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair();
		// 将RSA公钥Base64加密后放入页面中
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
				sessionAdapter.getRSACreateTimestamp());

		return "user/batchRegister"; //$NON-NLS-1$
	}

	/**
	 * 登录页面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair();
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
				sessionAdapter.getRSACreateTimestamp());
		return Messages.getString("LoginPage"); //$NON-NLS-1$
	}

	@PostMapping("/login")
	public String loginRequest(@RequestParam String username, @RequestParam String password, HttpSession session,
			HttpServletResponse response, HttpServletRequest request, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair();
		JSONResult result = userService.login(username, password, keyPair.getPrivate());
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if (result.getCode() == JSONCodeType.SUCCESS) {
			// 如果登录成功，把User对象放入session中
			User loginedUser = (User) result.get("user");
			sessionAdapter.setUser(loginedUser);
			try {
				response.sendRedirect(request.getContextPath() + "/");
			} catch (IOException e) {
				modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
				return "error";
			}
			return null;
		}
		modelAdapter.setErrorResult(result);
		return "error";
	}

	/**
	 * 修改个人信息页面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/modify")
	@UserTypeRequired(UserType.NORMAL_USER)
	public String modify(Model model, HttpSession session) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair(); // $NON-NLS-1$
		String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		model.addAttribute(Messages.getString("PublicKeyModelName"), publicKey); //$NON-NLS-1$
		model.addAttribute(Messages.getString("RSACreateTimestampModelName"), //$NON-NLS-1$
				sessionAdapter.getRSACreateTimestamp());
		return Messages.getString("ModifyUserInfoPage"); //$NON-NLS-1$
	}
	
	@PostMapping("/modify")
	public String modifyRequest(@RequestParam String oldPassword, @RequestParam String newPassword,
			@RequestParam String name, @RequestParam String telephone, HttpSession session, HttpServletResponse response, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		KeyPair keyPair = sessionAdapter.getRSAKeyPair(); 
		User user = sessionAdapter.getUser(); 
		JSONResult result = userService.modify(user, oldPassword, newPassword, name, telephone, keyPair.getPrivate());
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if(result.getCode() == JSONCodeType.SUCCESS) {
			User loginedUser = (User) result.get("user");
			sessionAdapter.setUser(loginedUser);
			try {
				response.sendRedirect("info?id=" + user.getId());
			} catch (IOException e) {
				modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
				return "error";
			}
			return null;
		}
		modelAdapter.setErrorResult(result);
		return "error";
	}

	/**
	 * 个人信息页面
	 * 
	 * @param id      查看用户信息的id
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@GetMapping("/info")
	public String info(@RequestParam Integer id, Model model, HttpServletRequest request, HttpSession session) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		ModelAdapter modelAdapter = new ModelAdapter(model);
		JSONResult result = userService.getUserInfo(id, sessionAdapter.getUser());
		if (result.getCode() == JSONCodeType.SUCCESS) {
			// 如果查询成功，显示个人信息页面
			UserInfoView view = (UserInfoView) result.getData().get(Messages.getString("UserInfoDataUserName")); //$NON-NLS-1$
			modelAdapter.setUserInfoView(view);
			return Messages.getString("UserInfoPage"); //$NON-NLS-1$
		}
		// 失败显示错误页面
		modelAdapter.setErrorResult(result);
		return Messages.getString("ErrorPage"); //$NON-NLS-1$
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = userService.logout(user);
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if (result.getCode() == JSONCodeType.SUCCESS) {
			sessionAdapter.setUser(null);
			try {
				response.sendRedirect(request.getContextPath() + "/");
			} catch (IOException e) {
				modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
				return "error";
			}
			return null;
		}
		modelAdapter.setErrorResult(result);
		return "error";
	}
}
