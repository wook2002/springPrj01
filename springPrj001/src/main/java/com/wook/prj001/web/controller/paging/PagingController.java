package com.wook.prj001.web.controller.paging;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wook.prj001.web.paging.Paging;



@CrossOrigin(origins = "*")
@RestController("pagingController")
public class PagingController {
	
	private Paging paging;
	
//	@PathVariable("navNum") int navNum
	@RequestMapping("paging/{id}")
	public void paging(@PathVariable("id") int id){
		System.out.println("id여: " + id);
//		paging.setPageIndex(id);
	}
}
