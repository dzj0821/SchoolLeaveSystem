package pers.dzj0821.SchoolLeaveSystem.controller.api;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;

@RequestMapping("/api/leave")
@Controller
public class ApiLeaveController {

	@PostMapping("/create")
	@UserTypeRequired(UserType.NORMAL_USER)
	public Map<String, Object> create(@RequestParam Integer startYear, @RequestParam Integer startMonth,
			@RequestParam Integer startDay, @RequestParam Integer endYear, @RequestParam Integer endMonth,
			@RequestParam Integer endDay, String reason, @RequestParam(value = "image", required = false) CommonsMultipartFile[] image) {
		
		return JSONResult.ACCESS_DENIED;
	}
}
