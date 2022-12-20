package com.wook.prj001.web.controller.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wook.prj001.web.entity.FreeBoard;
import com.wook.prj001.web.service.FreeBoardService;

@CrossOrigin(origins = "*")
@RestController("freeBoardController")
@RequestMapping("/freeBoard/")
public class FreeController {

	@Autowired
	private FreeBoardService service;

	@RequestMapping("list")
	public List<FreeBoard> getList() throws SQLException {
		return service.getList();
	}

	@RequestMapping("detail/{id}")
	public List<FreeBoard> getDetail(@PathVariable("id") int id) throws SQLException {
		return service.getDetail(id);
	}

	@RequestMapping("delete/{id}")
	public int deleteList(@PathVariable("id") int id) throws SQLException {

		return service.deleteList(id);

	}

//	, produces = "application/json"

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insertList(@RequestBody HashMap<String, Object> param){
		
		System.out.println("param : " + param);
		System.out.println("됨123");

	}

//	@RequestMapping("list")
//	public List<FreeBoard> list() throws SQLException {
//		System.out.println("controller어쩌고Ʈselect");
//		return service.getSelect();
//	}

	// @RestController
	// public class HomeController {
	// @RequestMapping(value="/", method= RequestMethod.GET)
	// public HashMap<String, Object> test3() {
//	      HashMap<String, Object> map = new HashMap<>();
//	      map.put("abcaa", "ddeeed");
//	      return map;
	// }

	@PostMapping("setList")
	public List<FreeBoard> insertList(@RequestBody Map messageBody) throws SQLException, IOException {

		System.out.println("messageBody : " + messageBody);
		System.out.println(messageBody.get("name"));

		return service.setList(messageBody);

		/*
		 * StringBuffer jb = new StringBuffer(); String line = null; try {
		 * BufferedReader reader = request.getReader(); while ((line =
		 * reader.readLine()) != null) jb.append(line); } catch (Exception e) { }
		 * 
		 * try { JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
		 * System.out.println(jsonObject); System.out.println("Controller�׽�Ʈinsert1 : "
		 * + jsonObject.get("Method"));
		 * 
		 * Object method = jsonObject.get("Method"); System.out.println("�� method " +
		 * method); System.out.println(method.getClass().getName());
		 * System.out.println(method.equals("name"));
		 * 
		 * } catch (JSONException e) { // crash and burn throw new
		 * IOException("Error parsing JSON request string"); }
		 */

	}
}
