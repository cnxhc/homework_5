package com.xhc.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.xhc.bean.BeanListHandler;
import com.xhc.bean.Good;
import com.xhc.bean.Order;

public class CreateOrderXml {

	public static void main(String[] args) {
		try {
			// 解析shopping.xml
			List<Good> goodList = getGoodList();

			// 计算shopping.xml文件中的水果的总价和数量
			Order order = calcOrder(goodList);

			// 创建order.xml文件
			outputOrderXml(order);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// 如果shopping.xml不存在，提示提示信息。
			System.out.println("没有找到，要读取得shopping.xml文件，请检查！谢谢使用！");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 计算商品的总价和总数量
	 * 
	 * @param goodList
	 *            商品列表
	 * @return 返回计算机结果 封装到Order对象里
	 */
	private static Order calcOrder(List<Good> goodList) {
		// 声明总价和数量
		double total = 0;
		int sums = 0;

		// 计算水果的总价和数量
		for (int i = 0; i < goodList.size(); i++) {
			double price = goodList.get(i).getPrice();
			int number = goodList.get(i).getNumber();
			total += price * number;
			sums += number;
		}
		System.out.println(total);
		System.out.println(sums);
		return new Order(total, sums);
	}

	/**
	 * 通过传入的order对象，生成order.xml
	 * 
	 * @param order
	 *            传入order对象
	 * @throws ParserConfigurationException
	 *             异常处理
	 * @throws TransformerFactoryConfigurationError
	 *             异常处理
	 * @throws TransformerConfigurationException
	 *             异常处理
	 * @throws TransformerException
	 *             异常处理
	 */
	private static void outputOrderXml(Order order) throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		double total = order.getTotal();
		int sums = order.getSums();
		// 获得DOM解析工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 获得具体的dom解析器
		DocumentBuilder builder = factory.newDocumentBuilder();

		// 创建order.xml文件
		Document createDocuement = builder.newDocument();
		Element root = createDocuement.createElement("order");
		Element orderTotal = createDocuement.createElement("total");
		orderTotal.setTextContent(total + "");
		Element orderSums = createDocuement.createElement("sums");
		orderSums.setTextContent(sums + "");
		root.appendChild(orderTotal);
		root.appendChild(orderSums);
		createDocuement.appendChild(root);
		// 生成order.xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(createDocuement), new StreamResult(new File("order.xml")));
	}

	/**
	 * 解析shopping.xml文件，获得商品列表goodList。
	 * 
	 * @return 返回商品列表goodList
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static List<Good> getGoodList() throws ParserConfigurationException, SAXException, IOException {
		// 创建解析工厂
		SAXParserFactory sFactory = SAXParserFactory.newInstance();
		// 得到解析器
		SAXParser sp = sFactory.newSAXParser();
		// 得到读取器
		XMLReader reader = sp.getXMLReader();
		// 设置内容处理器
		BeanListHandler handler = new BeanListHandler();
		reader.setContentHandler(handler);
		reader.parse("shopping.xml");
		List<Good> goodList = handler.getGoods();
		return goodList;
	}

}
