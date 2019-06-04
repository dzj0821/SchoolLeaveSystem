package pers.dzj0821.SchoolLeaveSystem.adapter;

import java.util.List;

import org.springframework.ui.Model;

import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;
import pers.dzj0821.SchoolLeaveSystem.pojo.view.LeaveListView;
import pers.dzj0821.SchoolLeaveSystem.pojo.view.UserInfoView;

public class ModelAdapter {
	private Model model;
	public ModelAdapter(Model model) {
		this.model = model;
	}
	public void setUserInfoView(UserInfoView view) {
		model.addAttribute("userInfoView", view);
	}
	public void setResult(JSONResult result) {
		model.addAttribute("result", result);
	}
	
	public void setLeaveList(List<LeaveListView> leaveListViews) {
		model.addAttribute("leaveListViews", leaveListViews);
	}
}
