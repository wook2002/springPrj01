package com.wook.prj001.web.paging;

import java.util.List;

//페이징 처리를 위한 클래스
//게시글 데이터와 페이징 관련 정보를 담고 있음
public class Paging {

	// pagination => API든 라이브러리든 그런걸로 
	
	// https://zorba91.tistory.com/263
	
	private int navNum; // 누른 navbar번호

	private int pageIndex; // 현재 페이지

	private int startRow;

	private int endRow;

	private int pageSize; // 페이지 크기

	private List<?> resultList;

	private int totalCount;

	// startRow, endRow를 계산한다.
	public void handlePaging(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
		this.pageSize = pageSize;
		this.startRow = ((pageIndex - 1) * pageSize);
		this.endRow = startRow + pageSize;
	}

	// 페이징 처리된 결과값을 담는 메서드
	public void handlePagingList(List<?> resultList, int totalCount) {
		this.resultList = resultList;
		this.totalCount = totalCount;
	}

	public void setNavNum(int navNum) {
		this.navNum = navNum;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
