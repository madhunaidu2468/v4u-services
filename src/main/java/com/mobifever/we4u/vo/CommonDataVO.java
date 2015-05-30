package com.mobifever.we4u.vo;

import java.util.List;

public class CommonDataVO {

	// This variable is used when we return a list of data, list is set here
	private List dataList;

	// This variable is used when we are sending back a single object
	private Object data;

	// Optional status
	private StatusVO status;

	public CommonDataVO() {

	}

	public CommonDataVO(StatusVO statusVo) {
		this.setStatus(statusVo);
	}

	public CommonDataVO(List dataList, Object data, StatusVO status) {
		this.dataList = dataList;
		this.data = data;
		this.status = status;
	}

	public StatusVO getStatus() {
		return status;
	}

	public void setStatus(StatusVO status) {
		this.status = status;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CommonDataVO [dataList=" + dataList + ", data=" + data
				+ ", status=" + status + "]";
	}

}