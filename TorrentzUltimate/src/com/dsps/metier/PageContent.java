package com.dsps.metier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dsps.beans.BMediaItem;

/**
 * BMediaItem example
 *
 *<dl>
 *	<dt>
 *		<a href="/2552169e161a1e766aa1ddc5f03b16ac5cb50f68">The Maze Runner 2014 
 *			<b>1080p</b> BrRip x264 YIFY
 *		</a> 
 *		&#187; action movies hd highres
 *	</dt>
 *	<dd>
 *		<span class="v" style="color:#fff;background-color:#69BD43">7</span>
 *		<span class="a"><span title="Sun, 30 Nov 2014 23:32:01">1 month</span></span>
 *		<span class="s">1685 MB</span> 
 *		<span class="u">13,929</span>
 *		<span class="d">3,038</span>
 *	</dd>
 *</dl>
 */

public class PageContent {

	public static final String DOMAIN = "http://torrentz.eu";
	private List<BMediaItem> mediaItems;
	private String URL;

	public List<BMediaItem> getMediaItems() {
		return mediaItems;
	}

	public void setMediaItems(List<BMediaItem> mediaItems) {
		this.mediaItems = mediaItems;
	}

	public PageContent(){
		//		no-args construct needed for jsp:usebean
	}

	public PageContent(String URL){
		this.URL = URL;
	}

	public void getContent(){
		Document htmlDoc = getDOMDoc();
		BMediaItem mediaItem = null;
		mediaItems = new ArrayList<BMediaItem>();
		//		Parse le contenu des balises <dl>...</dl>
		NodeList dlNodeList = htmlDoc.getElementsByTagName("DL");
		//		Commence à partir du 3ème DL => for(int=3 ...) les 3 premiers ne sont pas des médias
		if(dlNodeList!=null){
			for(int i=3;i<dlNodeList.getLength();i++){
				//			bean liens torrentz.eu 
				mediaItem = new BMediaItem();

				NodeList dlChildNodes = dlNodeList.item(i).getChildNodes();
				NodeList dtChildNodes= dlChildNodes.item(0).getChildNodes();
				NodeList dtFChNList = dtChildNodes.item(0).getChildNodes();


				//			DT part
				//			 DT.A href
				Node aNode = dtChildNodes.item(0);
				if(aNode.getNodeName().equals("A")){
					if(aNode.hasAttributes()){
						mediaItem.setLink(DOMAIN+aNode.getAttributes().getNamedItem("href").getNodeValue());
					}
				}
				//			Title, feature, type, quality
				if(dtChildNodes.item(1)!=null){
					mediaItem.setTitle(dtFChNList.item(0).getTextContent());
					mediaItem.setFeatures(dtChildNodes.item(0).getTextContent().replace(dtFChNList.item(0).getTextContent(), ""));
					mediaItem.setType(dtChildNodes.item(1).getTextContent());
					if(dtFChNList.item(1)!=null)
						mediaItem.setQuality(dtFChNList.item(1).getTextContent());
				}

				/* 
				 * DL part
				 */

				NodeList ddNodeList = dlChildNodes.item(1).getChildNodes();
				for(int j=0;j<ddNodeList.getLength();j++){
					if(ddNodeList.item(j).hasAttributes()){
						NamedNodeMap attr = ddNodeList.item(j).getAttributes();
						for(int count=0;count<attr.getLength();count++){
							Node classAttrNameValue = attr.getNamedItem(attr.item(count).getNodeName());
							String classAttrName = attr.item(count).getNodeValue();
							String classAttrValue = ddNodeList.item(j).getTextContent(); 

							if(!classAttrNameValue.getNodeName().equalsIgnoreCase("style")){
								mediaItem.setAttributes(classAttrName,classAttrValue);
							}
						}
					}
				}

				if(mediaItem!=null && mediaItem.getTitle()!=null){
					mediaItems.add(mediaItem);
				}
			}
		}
	}

	private Document getDOMDoc(){
		org.cyberneko.html.parsers.DOMParser dp = new DOMParser();
		try {
			dp.parse(URL);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return dp.getDocument();
	}
}
