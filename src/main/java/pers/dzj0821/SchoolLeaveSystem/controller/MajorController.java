package pers.dzj0821.SchoolLeaveSystem.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.adapter.ModelAdapter;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.MajorService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;

@Controller
@RequestMapping("/major")
public class MajorController {
	@Autowired
	private MajorService majorService;
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = majorService.list(user);
		if(result.getCode() == JSONCodeType.SUCCESS) {
			model.addAttribute("map", result.getData().get("map"));
			model.addAttribute("collages", result.getData().get("collages"));
			return "major/list";
		}
		ModelAdapter modelAdapter = new ModelAdapter(model);
		modelAdapter.setErrorResult(result);
		return "error";
	}
}
