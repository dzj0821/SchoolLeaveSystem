package pers.dzj0821.SchoolLeaveSystem.controller;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.adapter.HttpSessionAdapter;
import pers.dzj0821.SchoolLeaveSystem.adapter.ModelAdapter;
import pers.dzj0821.SchoolLeaveSystem.annotation.UserTypeRequired;
import pers.dzj0821.SchoolLeaveSystem.pojo.Leave;
import pers.dzj0821.SchoolLeaveSystem.pojo.LeaveImage;
import pers.dzj0821.SchoolLeaveSystem.pojo.User;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.pojo.view.LeaveInfoView;
import pers.dzj0821.SchoolLeaveSystem.service.LeaveService;
import pers.dzj0821.SchoolLeaveSystem.type.JSONCodeType;
import pers.dzj0821.SchoolLeaveSystem.pojo.view.LeaveListView;
import pers.dzj0821.SchoolLeaveSystem.type.LeaveType;
import pers.dzj0821.SchoolLeaveSystem.type.UserType;
//TODO 使用自定义EL函数在JSP中取值
/**
 * 请假模块页面的Controller
 * @author dzj0821
 *
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	private Logger logger = LogManager.getLogger(LeaveController.class);
	
	/**
	 * 创建假条页面
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	@UserTypeRequired(UserType.NORMAL_USER)
	public String create(HttpSession session, Model model) {
		if(new HttpSessionAdapter(session).getUser().getType() != UserType.NORMAL_USER) {
			ModelAdapter modelAdapter = new ModelAdapter(model);
			modelAdapter.setErrorResult(JSONResult.ACCESS_DENIED);
			return "error";
		}
		Calendar calendar = Calendar.getInstance();
		int[] years = new int[3];
		//获取当前年份
		int currentYear = calendar.get(Calendar.YEAR);
		//提供当前年份到3年后（如2019~2021）的时间
		for(int i = 0; i < years.length; i++) {
			years[i] = currentYear++;
		}
		//月份从0开始
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		int currentDay = calendar.get(Calendar.DATE);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		model.addAttribute("years", years);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDay", currentDay);
		model.addAttribute("lastDay", lastDay);
		return Messages.getString("CreateLeavePage");
	}
	
	@PostMapping("/create")
	public String createRequest(@RequestParam int startYear, @RequestParam int startMonth,
			@RequestParam int startDay, @RequestParam int startLesson, @RequestParam int endYear,
			@RequestParam int endMonth, @RequestParam int endDay, @RequestParam int endLesson,
			@RequestParam String reason, @RequestParam(value = "image", required = false) CommonsMultipartFile[] images,
			HttpSession session, Model model, HttpServletResponse response) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = leaveService.create(user, startYear, startMonth, startDay, startLesson, endYear, endMonth, endDay, endLesson,
				reason, images, session.getServletContext().getRealPath("/"));
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if(result.getCode() != JSONCodeType.SUCCESS) {
			modelAdapter.setErrorResult(result);
			return "error";
		}
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
			return "error";
		}
		return null;
	}
	
	
	@GetMapping("/list")
	public String list(Integer clazzId, Integer userId, LeaveType type, HttpSession session, Model model) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = null;
		try {
			result = leaveService.list(user, clazzId, userId, type);
		} catch (Exception e) {
			logger.warn(e);
			result = JSONResult.SERVER_ERROR;
		}
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if(result.getCode() != JSONCodeType.SUCCESS) {
			modelAdapter.setErrorResult(result);
			return "error";
		}
		@SuppressWarnings("unchecked")
		List<Leave> leaves  = (List<Leave>) result.getData().get("leaves");
		List<LeaveListView> leaveListViews = new ArrayList<>(leaves.size());
		for (Leave leave : leaves) {
			leaveListViews.add(new LeaveListView(leave));
		}
		modelAdapter.setLeaveList(leaveListViews);
		return "leave/list";
	}
	
	/**
	 * 审核界面
	 * 
	 */
	@GetMapping("/info")
	public String info(@RequestParam int id, Model model, HttpSession session){
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = leaveService.info(user, id);
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if(result.getCode() != JSONCodeType.SUCCESS) {
			modelAdapter.setErrorResult(result);
			return "error";
		}
		Leave leave = (Leave) result.getData().get("leave");
		@SuppressWarnings("unchecked")
		List<LeaveImage> leaveImages = (List<LeaveImage>) result.getData().get("leaveImages");
		LeaveInfoView leaveInfoView = new LeaveInfoView(leave, leaveImages);
		model.addAttribute("leaveInfoView", leaveInfoView);
		return "leave/info";
	}
	
	@PostMapping("/review")
	public String review(@RequestParam int id, @RequestParam boolean access, HttpSession session, Model model, HttpServletResponse response) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = leaveService.review(user, id, access);
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if(result.getCode() != JSONCodeType.SUCCESS) {
			modelAdapter.setErrorResult(result);
			return "error";
		}
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
			return "error";
		}
		return null;
	}
	
	@GetMapping("/cancel")
	public String cancel(@RequestParam int id, HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request) {
		HttpSessionAdapter sessionAdapter = new HttpSessionAdapter(session);
		User user = sessionAdapter.getUser();
		JSONResult result = leaveService.cancel(user, id);
		ModelAdapter modelAdapter = new ModelAdapter(model);
		if(result.getCode() != JSONCodeType.SUCCESS) {
			modelAdapter.setErrorResult(result);
			return "error";
		}
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			modelAdapter.setErrorResult(JSONResult.SERVER_ERROR);
			return "error";
		}
		return null;
		
	}
	
}
