package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.service.CollageService;

@RequestMapping("/api/collage")
@Controller
public class ApiCollageController {
	
	@Autowired
	private CollageService collageService;
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> add(@RequestParam String name, HttpSession session){
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		return collageService.add(name, user);
	}
	
	@PostMapping("/change")
	@ResponseBody
	public Map<String, Object> change(@RequestParam Integer id, @RequestParam String name, HttpSession session){
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		return collageService.change(id, name, user);
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam Integer id, HttpSession session){
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		return collageService.delete(id, user);
	}
}
