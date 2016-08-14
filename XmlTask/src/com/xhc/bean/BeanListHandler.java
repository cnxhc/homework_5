package com.xhc.bean;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BeanListHandler extends DefaultHandler {
	private List<Good> list=new ArrayList<Good>();
	private String currentTag;
	private Good good;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentTag=qName;
		if("goods".equals(currentTag)){
			good=new Good();
		}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if("name".equals(currentTag)){
			String value=new String(ch,start,length);
			good.setName(value);
		}
		if("price".equals(currentTag)){
			String value=new String(ch,start,length);
			double price=Double.parseDouble(value);
			good.setPrice(price);
		}
		if("number".equals(currentTag)){
			String value=new String(ch,start,length);
			int number=Integer.parseInt(value);
			good.setNumber(number);
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if("goods".equals(qName)){
			list.add(good);
			good=null;
		}
		currentTag=null;
	}
	public List<Good> getGoods(){
		return list;
	}
	
	
	
}
