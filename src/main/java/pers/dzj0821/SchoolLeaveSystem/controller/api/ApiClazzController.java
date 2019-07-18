package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.service.ClazzService;

@Controller
@RequestMapping("/api/clazz")
public class ApiClazzController {
	@Autowired
	private ClazzService clazzService;
	
	private Logger logger = LogManager.getLogger(ApiClazzController.class);
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> add(@RequestParam Integer no, @RequestParam Integer majorId, @RequestParam Integer gradeId, HttpSession session){
		JSONResult result = null;
		try {
			result = clazzService.add(no, gradeId, majorId, new HttpSessionAdapter(session).getUser());
		} catch (Exception e) {
			logger.warn(e);
			result = JSONResult.SERVER_ERROR;
		}
		return result;
	}
	
	@PostMapping("/change")
	@ResponseBody
	public Map<String, Object> change(@RequestParam Integer id, @RequestParam Integer no, @RequestParam Integer majorId, @RequestParam Integer gradeId, HttpSession session){
		JSONResult result = null;
		try {
			result = clazzService.change(id, no, gradeId, majorId, new HttpSessionAdapter(session).getUser());
		} catch (Exception e) {
			logger.warn(e);
			result = JSONResult.SERVER_ERROR;
		}
		return result;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam Integer id, HttpSession session){
		JSONResult result = null;
		try {
			result = clazzService.delete(id, new HttpSessionAdapter(session).getUser());
		} catch (Exception e) {
			logger.warn(e);
			result = JSONResult.SERVER_ERROR;
		}
		return result;
	}
	
	@PostMapping("/addUser")
	@ResponseBody
	public Map<String, Object> addUser(@RequestParam Integer id, @RequestParam String username, HttpSession session){
		JSONResult result = null;
		try {
			result = clazzService.addUser(id, username, new HttpSessionAdapter(session).getUser());
		} catch (Exception e) {
			logger.warn(e);
			result = JSONResult.SERVER_ERROR;
		}
		return result;
	}
	
	@PostMapping("/removeUser")
	@ResponseBody
	public Map<String, Object> removeUser(@RequestParam Integer id, @RequestParam Integer userId, HttpSession session){
		JSONResult result = null;
		try {
			result = clazzService.removeUser(id, userId, new HttpSessionAdapter(session).getUser());
		} catch (Exception e) {
			logger.warn(e);
			result = JSONResult.SERVER_ERROR;
		}
		return result;
	}
}
