package com.pcwk.ehr.member;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pcwk.ehr.cmn.Box;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class MemberDao implements WorkDiv<MemberDTO> {
	
	private final String fileName = "member.json"; //저장 파일명
	
	private List<MemberDTO> listBox = null; //json -> 객체
	
	public MemberDao() {
		//초기화 :
		listBox = new ArrayList<MemberDTO>();
		
		int count = doReadFile();
		LOG.debug("member count : " + count);
	}

	@Override
	public List<MemberDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(MemberDTO param) {
		int flag = 0;
		
		flag = this.listBox.add(param) == true?1:0;
		
		LOG.debug("flag : " + flag);
		return flag;
	}

	@Override
	public int doUpdate(MemberDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(MemberDTO param) {
		int flag = 0;
		
		for(MemberDTO member :listBox) {
			if(member.getMemberId().equals(param.getMemberId())) {
				flag = listBox.remove(member) == true?1:0;
				break;
			}
		}
		LOG.debug("flag : " + flag);
		
		return flag;
	}

	@Override
	public MemberDTO doSelectOne(MemberDTO param) {
		MemberDTO memberDTO = null;
		for(MemberDTO member :listBox) {
			if(member.getMemberId().equals(param.getMemberId())) {
				memberDTO = member;
				break;
			}
		}
		LOG.debug("memberDTO : " + memberDTO);
		
		return memberDTO;
	}

	@Override
	public int doSaveFile() {
		int count = 0;
		
		//PrettyPrinting 된 JSON 생성
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try(FileWriter writer = new FileWriter(fileName)){
			
			gson.toJson(listBox, writer);
			
			count = listBox.size();
		} catch(IOException e) {
			LOG.debug("IOException : " + e.getMessage());
			count = 0;
		}
		LOG.debug("doSaveFile count : " + count);
		return count;
	}

	@Override
	public int doReadFile() {
		LOG.debug("doReadFile()");
		try(FileReader fr = new FileReader(fileName)){
			
			//Gson 객체 생성
			Gson gson = new Gson();
			//JSON 데이터를 Box<MemberDTO> 클래스로 변환
			Type type = new TypeToken<List<MemberDTO>>() {}.getType();
			
			List<MemberDTO> tmpBox =  gson.fromJson(fr, type);
			
			if(null != tmpBox) {
				listBox = tmpBox;
			}
			
		}catch(IOException e) {
			LOG.debug("IOException : " + e.getMessage());
		}
		LOG.debug("listBox.size() : " + listBox.size());
		
		return listBox.size();
	}

	public List<MemberDTO> getListBox() {
		return listBox;
	}

	public void setListBox(List<MemberDTO> listBox) {
		this.listBox = listBox;
	}

	public String getFileName() {
		return fileName;
	}

	
}