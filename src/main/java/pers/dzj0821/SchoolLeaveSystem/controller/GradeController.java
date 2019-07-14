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
import pers.dzj0821.SchoolLeaveSystem.service.GradeService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;

@Controller
@RequestMapping("/grade")
public class GradeController {
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = gradeService.list(user);
		if(result.getCode() == JSONCodeType.SUCCESS) {
			model.addAttribute("grades", result.getData().get("grades"));
			return "grade/list";
		}
		ModelAdapter modelAdapter = new ModelAdapter(model);
		modelAdapter.setErrorResult(result);
		return "error";
	}
}
