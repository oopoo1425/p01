package com.pcwk.ehr.member;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pcwk.ehr.cmn.Box;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class Main implements PLog {

	MemberDTO member01;
	MemberDTO member02;
	MemberDTO member03;
	
	Box<MemberDTO> box;
	
	MemberDao     dao;
	
	public Main() {
		member01 = new MemberDTO("james01", "이상무01", "a4321", "james01@naver.com", "010-1234-5678", "2024-04-19", "admin");
		member02 = new MemberDTO("james02", "이상무02", "a4321", "james02@naver.com", "010-1234-5679", "2024-04-19", "admin");
		member03 = new MemberDTO("james03", "이상무03", "a4321", "james03@naver.com", "010-1234-5680", "2024-04-19", "admin");
		
		box = new Box<MemberDTO>();
		box.add(member01);
		box.add(member02);
		box.add(member03);
		
		
		dao = new MemberDao();
		
		//항상 3명 Member로 시작하기 위해
		dao.setListBox(box.getListBox());
		dao.doSaveFile();
	}
	
	public void doSave() {
		LOG.debug("doSave()");
		int flag = dao.doSave(new MemberDTO("james01", "이상무04", "a4321", "james04@naver.com", "010-1234-5681", "2024-04-19", "admin"));
		
		if(flag==1) {
			LOG.debug("-----------");
			LOG.debug("단건 등록 성공!");
			LOG.debug("-----------");
		}else {
			LOG.debug("단건 등록 실패! flag:"+flag);
		}
		
				
	}
	
	public void doDelete() {
		LOG.debug("doDelete()");
		int flag =dao.doDelete(member03);
		if(flag==1) {
			LOG.debug("-----------");
			LOG.debug("단건 삭제 성공!");
			LOG.debug("-----------");
		}else {
			LOG.debug("단건 삭제 실패!");
		}
				
	}
	
	public void doSelectOne() {
		LOG.debug("doSelectOne()");
		MemberDTO dto = dao.doSelectOne(member01);
		
		if(null != dto) {
			LOG.debug("-----------");
			LOG.debug("단건 조회 성공!");
			LOG.debug(dto);
			LOG.debug("-----------");			
		}else {
			LOG.debug("단건 조회 실패!");
		}
		
	}
	
	public void doUpdate() {
		LOG.debug("doUpdate()");
		
		int flag = dao.doUpdate(new MemberDTO("james03", "이상무33", "a4321_33", "james33@naver.com", "010-1234-5683", "2024-04-23", "admin_33"));
		if( 2==flag) {
			LOG.debug("-----------");
			LOG.debug("수정 성공!");	
			LOG.debug("-----------");
		}else {
			LOG.debug("-----------");
			LOG.debug("수정 실패!");	
			LOG.debug("-----------");			
		}
	}
	
	public void doRetrieve() {
		LOG.debug("doRetrieve()");
		List<MemberDTO> list=dao.doRetrieve(null);
		
		if(3==list.size()) {
			LOG.debug("----------");
			LOG.debug("-목록조회 성공!-");
			LOG.debug("----------");
		}else {
			LOG.debug("----------");
			LOG.debug("-목록조회 실패!-");
			LOG.debug("----------");			
		}
		dispList(list);

		
	}
	
	public void dispList(List<MemberDTO> list) {
		for(MemberDTO dto   :list) {
			LOG.debug(dto);
		}		
	}
	
	
	public void doRetrieveName() {
		LOG.debug("doRetrieveName()");
		
		//SearchDTO search=new SearchDTO("10", "이상무02");
		SearchDTO search=new SearchDTO("20", "james0");
		List<MemberDTO> list = dao.doRetrieve(search);
		dispList(list);
		   
	}
	public static void main(String[] args) {
		Main m=new Main();
		m.doRetrieveName();
		
		//목록조회
		//m.doRetrieve();
		
		//수정 
		//m.doUpdate();
		//단건 조회
		//m.doSelectOne();
		
		//단건 삭제
		//m.doDelete();
		
		
		//단건 등록
		//m.doSave();
		
		
		//객체 to json file로 기록
		m.dao.doSaveFile();
	}

}
