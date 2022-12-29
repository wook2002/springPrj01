package com.wook.prj01.web.board.dto;

import lombok.Data;

@Data
public class Page {
	
	
//	정렬
	private String sortBy = "no"; // 입력(->DB)
	private String sort = "ASC"; // 입력(->DB)
	
//	list
	private int countList; // 가져옴(<-DB)
	
	private int sizeList; 
	private int beginList; // 입력(->DB) 1, 11, 21, 31
	private int endList; // 입력(->DB) 10, 20, 30, 40
	
//	bar
	private int currentBar; 
	
	private int sizeBar;
	private int maxiumBar;
	private int beginBar; // 1, 6, 11, 16
	
	private int endBar; // 5, 10, 15, 20
	
	public Page() {
		// TODO Auto-generated constructor stub
	}

//	int currentBar, int sizeBar, int countList
	public Page(int currentBar, int countList, int sizeList, int sizeBar){
		this.currentBar = currentBar;
		this.countList = countList;
		this.sizeList = sizeList;
		this.sizeBar = sizeBar;
		
		this.endBar = (int)Math.ceil(currentBar/(double)sizeBar) * sizeBar;
		this.beginBar = this.endBar - (sizeBar - 1);
		this.maxiumBar = (int)(Math.ceil((double)countList / sizeList));
		
		this.endList = currentBar * sizeList;
		this.beginList = this.endList - (sizeList - 1);
		
		if(this.maxiumBar < this.endBar) {
			this.endBar = maxiumBar;
		}
	}
	
	@Override
	public String toString() {
		return " -- currentBar : " + currentBar
				+ ",( beginBar : " + beginBar 
				+ ", endBar : " + endBar 
				+ "), (maxiumBar : " + maxiumBar 
				+ "), ( beginList : " + beginList
				+ ", endList : " + endList + " )";
	}
	
	
	
	
}
