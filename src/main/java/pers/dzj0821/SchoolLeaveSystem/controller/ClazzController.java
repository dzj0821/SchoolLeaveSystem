package pers.dzj0821.SchoolLeaveSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.adapter.ModelAdapter;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.ClazzService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/clazz")
public class ClazzController {
	@Autowired
	private ClazzService clazzService;
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = clazzService.list(user);
		if(result.getCode() == JSONCodeType.SUCCESS) {
			model.addAttribute("map", result.getData().get("map"));
			model.addAttribute("majors", result.getData().get("majors"));
			return "clazz/list";
		}
		ModelAdapter modelAdapter = new ModelAdapter(model);
		modelAdapter.setErrorResult(result);
		return "error";
	}
	
	@GetMapping("/info")
	public String info(@RequestParam Integer id, HttpSession session, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = clazzService.info(id, user);
		if(result.getCode() == JSONCodeType.SUCCESS) {
			model.addAttribute("users", result.getData().get("users"));
			return "clazz/info";
		}
		ModelAdapter modelAdapter = new ModelAdapter(model);
		modelAdapter.setErrorResult(result);
		return "error";
	}
}
