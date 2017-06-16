package life.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class IndexController {
	/**
	 * 业务系统管理主页
	 */
	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public String login() {
		return "lina/login";
	}
	/**
	 * 登录
	 */
	@RequestMapping("/loginIn")
	public String loginIn() {
		return "lina/login";
	}
	
}
