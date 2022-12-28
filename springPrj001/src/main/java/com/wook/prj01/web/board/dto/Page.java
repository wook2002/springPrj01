package com.wook.prj01.web.board.dto;

public class Page {
	
	
//	정렬
	private String sortBy = "no"; // 입력(->DB)
	private String sort = "ASC"; // 입력(->DB)
	
//	list
	private int countList = 35; // 가져옴(<-DB)
	
	private int sizeList = 10; 
	private int beginList; // 입력(->DB) 1, 11, 21, 31
	private int endList; // 입력(->DB) 10, 20, 30, 40
	
//	bar
	private int currentBar = 2; 
	
	private int sizeBar = 5;
	private int maxiumBar;
	private int beginBar; // 1, 6, 11, 16
	private int endBar; // 5, 10, 15, 20
	

//	int currentBar, int sizeBar, int countList
	public Page(){
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
