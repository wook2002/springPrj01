package com.wook.prj001.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("index")
	public void index() {
	}
	
}

//@RestController
//public class HomeController {
//  @RequestMapping(value="/", method= RequestMethod.GET)
//  public HashMap<String, Object> test3() {
//      HashMap<String, Object> map = new HashMap<>();
//      map.put("abcaa", "ddeeed");
//      return map;
// }
//}
