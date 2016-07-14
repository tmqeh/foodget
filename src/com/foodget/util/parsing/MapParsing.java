package com.foodget.util.parsing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.foodget.store.api.JosuChangeApi;
import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.utill.NumberCheck;
import com.foodget.utill.StringMethod;

public class MapParsing {
	static int seq;
	static MapParsing mapParsing;
	private String blogUrl;
	private String title;
	private String address;
	private String blogId;
	private int commentCount;
	private int likeCount;
	private int lenthBody;
	private int imageCount;
	private String BlogNumber;
	private BlogDto blogDto;

	public BlogDto getBlogDto() {
		return blogDto;
	}

	public void setBlogDto(BlogDto blogDto) {
		this.blogDto = blogDto;
	}

	public String getBlogNumber() {
		return BlogNumber;
	}

	public void setBlogNumber(String blogNumber) {
		BlogNumber = blogNumber;
	}
	static {
		mapParsing = new MapParsing();
	}

	public static MapParsing getMapParsing() {
		return mapParsing;
	}

	public String getTitle() {
		return title;
	}

	public String getAddress() {
		return address;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBlogRankInfo(String blogId, int commentCount, int lenthBody, int imageCount,String blogNumber) {
		BlogRankInfoDto blogRankInfoDto = mapParsing.getBlogDto().getBlogRankInfoDto();
		BlogDto blogDto = mapParsing.getBlogDto();
		
		String url = "http://blog.naver.com/"+blogId+"/"+blogNumber;
		blogDto.setUrl(url);
		blogRankInfoDto.setUrl(url);
		blogRankInfoDto.getBlogImgInfoDto().setUrl(url);
		blogRankInfoDto.setBlog_writer_id(blogId);
		
		blogRankInfoDto.setComment_count(commentCount);
		blogRankInfoDto.setBody_lenth(lenthBody);
		blogRankInfoDto.setImage_count(imageCount);
		blogDto.setBlog_number(blogNumber);
		blogDto.setBlogRankInfoDto(blogRankInfoDto);
		String newAddress= StringMethod.getStringMethod().stringToken(JosuChangeApi.getNewAddres(mapParsing.getAddress()));
		
		blogDto.setStore_name(mapParsing.getTitle());
		blogDto.setOld_address(mapParsing.getAddress());
		
		blogDto.setNew_address(newAddress);
		blogDto.setSearchWord(newAddress+" "+mapParsing.getTitle());
		blogDto.setStore_seq(seq++);
		mapParsing.setBlogDto(blogDto);
	}

	public Document getDocument(String src) {
		Document doc = null;
		try {
			doc = Jsoup.connect(src).get();
		} catch (IOException e) {
			return null;
		}
		return doc;
	}

	public String getFrameUrl(String src) {
		String url = null;
		Document doc;
		doc = getDocument(src);
		if(doc==null){
			return null;
		}
		Elements contents = doc.select("frame");

		int contentSize = contents.size();
		for (int i = 0; i < contentSize; i++) {
			Element content = contents.get(i);
			String attrSrc = content.attr("src");
			String id = content.id();
			if (!"".equals(attrSrc)) {
				if ("hiddenFrame".equals(id)) {
					attrSrc = content.attr("src");
					mapParsing.setBlogId(StringMethod.getStringMethod().getBlogIdToken(attrSrc));
				} else if ("screenFrame".equals(id)) {
					url = getFrameUrl(attrSrc);
				} else if ("mainFrame".equals(id)) {
					url = attrSrc;
					mapParsing.setBlogNumber(StringMethod.getStringMethod().getKeyAndValToUrl(attrSrc, "logNo"));
					url = "http://blog.naver.com/" + url;
				} else {
				}
			}
		}
		return url;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getLenthBody() {
		return lenthBody;
	}

	public void setLenthBody(int lenthBody) {
		this.lenthBody = lenthBody;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public static void setMapParsing(MapParsing mapParsing) {
		MapParsing.mapParsing = mapParsing;
	}

	public void setBlogCommnetCount(Document doc) {
		Elements Contetns = doc.select("a._cmtList");
		if(Contetns !=null){
			if (Contetns.size() > 0) {
				Element Contetn = Contetns.get(0);
				String commentCount = Contetn.text();
				mapParsing.setCommentCount(mapParsing.getBlogComentCount(commentCount));
			}
		}

	}
	public void getRealBlogUrl(Document doc) {
		Elements contents = doc.select("meta");
		String url = "";
		String attrProperty = "";
		int contentSize = contents.size();
		for (int i = 0; i < contentSize; i++) {
			Element content = contents.get(i);
			attrProperty = content.attr("property");
			if ("og:url".equals(attrProperty)) {
				url = content.attr("content");
				break;
			}
		}
		mapParsing.setblogUrl(url);

	}
	public void getBlogdetailInfo(Document doc) {
		mapParsing.setBlogCommnetCount(doc);
		mapParsing.getBlogLength(doc);
	}
	public String getMapUrl(String src) {
		String url = "";
		Document doc;
		doc = getDocument(src);
		mapParsing.getBlogdetailInfo(doc);
		String mapSrc = "http://mashup.map.naver.com";
		Elements contents = doc.select("iframe");
		int contentSize = contents.size();
		for (int i = 0; i < contentSize; i++) {
			Element content = contents.get(i);
			String attrSrc = content.attr("src");
			if (attrSrc.length() > 27) {
				if (mapSrc.equals(attrSrc.substring(0, 27))) {
					url = attrSrc;
					break;
				}
			}
		}
		return url;
	}

	public JSONObject getBusinessName(String src) {
		JSONObject json = null;
		Document doc;
		doc = getDocument(src);
		mapParsing.getBlogdetailInfo(doc);
		Elements contents = doc.select("script");
		Object title = "";
		int contentSize = contents.size();
		for (int i = 0; i < contentSize; i++) {
			Element content = contents.get(i);
			String attrSrc = content.attr("src");
			String attrLanguage = content.attr("language");
			if ("".equals(attrSrc) && "javascript".equals(attrLanguage)) {
				String data = content.data();
				data = data.substring(data.indexOf("{"), data.indexOf("[") - 12);
				data = data + "}";
				data = data.replace("\\", "");
				json = StringMethod.getStringMethod().stringToJson(data);
				title = json.get("title");
				if (title != null) {
					break;
				}
			}
		}
		return json;
	}
	public JSONObject noSearchMapUrl(String src) {
		JSONObject json = null;
		Document doc;
		doc = getDocument(src);
		mapParsing.getBlogdetailInfo(doc);
		Elements contents = doc.select("script.__se_module_data");
		int contentSize = contents.size();
		Object title = "";
		for (int i = 0; i < contentSize; i++) {
			Element content = contents.get(i);
			String data = content.toString();
			data = data.replace("&quot;", "\"");
			data = data.substring(data.indexOf("{") + 1, data.lastIndexOf("}") + 1);
			data = data.substring(data.indexOf("{"), data.lastIndexOf("}"));
			json = StringMethod.getStringMethod().stringToJson(data);
			title = json.get("title");
			if (title != null) {
				break;
			}
		}
		return json;
	}
	public String getDetailMenu(String src) {
		String url = "";
		Document doc;
		doc = mapParsing.getDocument(src);
		Elements contents = doc.select("script");
		String attrType = "text/javascript";
		String attrLanguage = "";
		String attrSrc = "";
		int size = contents.size();
		for (int i = 0; i < size; i++) {
			Element content = contents.get(i);
			if (content.attr("type").equals(attrType)) {
				if (content.attr("language").equals(attrLanguage)) {
					if (content.attr("src").equals(attrSrc)) {
					}
				}
			}

		}
		return url;
	}
	public int getBlogComentCount(String str) {
		int count = 0;
		String key = "";
		String value = "";
		StringTokenizer strToken = new StringTokenizer(str, " ");
		int size = strToken.countTokens();
		for (int i = 0; i < size; i++) {
			if (strToken.hasMoreTokens()) {
				key = strToken.nextToken();
				if ("댓글".equals(key)) {
					if (strToken.hasMoreTokens()) {
						value = strToken.nextToken();
						if (NumberCheck.isNumber(value)) {
							count = Integer.parseInt(value);
							break;
						}

					}
				}
			}
		}
		return count;
	}

	public void getBlogLength(Document doc) {
		Elements contentsNoSmart = doc.select("div#postViewArea");
		Element content = null;
		String blogNumber = mapParsing.getBlogNumber();
		int bodyLength = 0;
		if (contentsNoSmart.size() > 0) {
			content = contentsNoSmart.get(0);
			blogImgCount(content);
			bodyLength = content.text().length();
			mapParsing.setLenthBody(bodyLength);
		} else {
			Elements contentsSmart3 = doc.select("div#post-view" + blogNumber);
			if (contentsSmart3.size() > 0) {
				content = contentsSmart3.get(0);
				blogImgCount(content);
				bodyLength = content.text().length();
				mapParsing.setLenthBody(bodyLength);
			}
		}
	}

	public void test(String src) {
		String url = "";
		Document doc;
		doc = mapParsing.getDocument(src);
	}

	public void blogImgCount(Element content) {
		Elements subContents = content.select("img");
		int size = subContents.size();
		int count = 0;
		List<String> temp = new ArrayList<String>();
		BlogImgInfoDto blogImgInfoDto = new BlogImgInfoDto();
		BlogRankInfoDto blogRankInfoDto  = new BlogRankInfoDto();
		BlogDto blogDto = new BlogDto();
		for (int i = 0; i < size; i++) {
			Element subContent = subContents.get(i);
			if (!"".equals(subContent.id())) {
				String imgSrc = subContent.attr("src");
				temp.add(imgSrc);
				blogImgInfoDto.setImgSrcList(temp);
				blogRankInfoDto.setBlogImgInfoDto(blogImgInfoDto);
				blogDto.setBlogRankInfoDto(blogRankInfoDto);
				mapParsing.setBlogDto(blogDto);
				count++;
			}
		}
		mapParsing.setImageCount(count);
	}
	public static void main(String args[]) {
		// stringToTextFile(doc.select("body").toString());
		// http://blog.naver.com/juhyun852/220745302061
		String src = "http://yesican1.blog.me/220752560025";
		mapParsing.startParsing(src);
		// mapParsing.getDetailMenu(src);
		// mapParsing.test(src);
	}

	public boolean startParsing(String url) {
		String title = null;
		String address = null;
		boolean flag = false;
		String framUrl = mapParsing.getFrameUrl(url);
		String mapUrl = "";
		JSONObject businessInfo = null;
		if (framUrl != null) {
			mapUrl = mapParsing.getMapUrl(framUrl);
			if ("".equals(mapUrl)) {
				businessInfo = mapParsing.noSearchMapUrl(framUrl);
			} else {
				businessInfo = mapParsing.getBusinessName(mapUrl);
			}
		} else {
			flag = false;
		}
		if (businessInfo != null) {
			title = (String) businessInfo.get("title");
			address = (String) businessInfo.get("address");
			if (title == null || address == null) {
				flag = false;
			} else {
				mapParsing.setAddress(address);
				mapParsing.setTitle(title);
				flag = true;
			}
			flag = true;
		} else {
			flag = false;
		}
		//ok
		if (flag == true) {
			mapParsing.setBlogRankInfo( mapParsing.getBlogId(), mapParsing.getCommentCount(),
					mapParsing.getLenthBody(), mapParsing.getImageCount(),mapParsing.getBlogNumber() );
		}
		return flag;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setblogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
}