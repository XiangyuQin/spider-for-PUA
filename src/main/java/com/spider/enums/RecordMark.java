package com.spider.enums;

public enum RecordMark {
	
	NORECORD(0),
	
	RECORD(1);
	
	private final int mark;
	
	RecordMark(int mark){
		this.mark = mark;
	}
	
	public int getMark() {
		return mark;
	}
}
