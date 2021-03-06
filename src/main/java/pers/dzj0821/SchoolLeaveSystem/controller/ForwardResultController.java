package pers.dzj0821.SchoolLeaveSystem.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.dzj0821.SchoolLeaveSystem.Messages;
import pers.dzj0821.SchoolLeaveSystem.annotation.OnlyForwardAccess;
import pers.dzj0821.SchoolLeaveSystem.pojo.json.JSONResult;

/**
 * 用于拦截器返回JSON结果
 * @author dzj0821
 *
 */
@Controller
public class ForwardResultController {
	private Logger logger = LogManager.getLogger(ForwardResultController.class);
	
	@RequestMapping("/result")
	@ResponseBody
	@OnlyForwardAccess
	public Map<String, Object> result(HttpServletRequest request){
		JSONResult result = (JSONResult) request.getAttribute(Messages.getString("NeedForwardResultRequestName")); //$NON-NLS-1$
		if(result == null) {
			logger.warn(Messages.getString("ForwardResultMissingResult"));
			return JSONResult.SERVER_ERROR;
		}
		return result;
	}
}
