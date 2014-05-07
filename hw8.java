

import java.io.*;
import javax.servlet.*;
import java.util.*;
import java.net.*;
import javax.servlet.http.*;


import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML; 
import org.apache.commons.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;


/*import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.*;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;*/

public class hw8 extends HttpServlet 
{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
	{
		//request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String loc = request.getParameter("location");
		String t = request.getParameter("type");
		String u=request.getParameter("tempUnit");
		loc=URLEncoder.encode(loc, "UTF-8");
		
		String urlString="http://default-environment-xbgia2enan.elasticbeanstalk.com/?location="+loc+"&type="+t+"&tempUnit="+tempUnit;
		
		PrintWriter out = response.getWriter ();
		//out.println(urlString);
		//out.println("hello");
		URL	url	= new URL(urlString);	
		URLConnection urlConnection	= url.openConnection();	
		urlConnection.setAllowUserInteraction(false);	
		InputStream urlStream =	url.openStream();
		
		/*StringBuilder builder =  new StringBuilder();
		int ptr = 0;
        while ((ptr = urlStream.read()) != -1 )
        {
            builder.append((char) ptr);
        }
		String xml  = builder.toString();
		//out.println(urlStream);
		*/
		String xmlString =IOUtils.toString(urlStream);
		//out.println(xmlString);
		String jsonurl = XMLtoJSON(xmlString);
		out.println(jsonurl);

		
	}
	/*public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
			doGet(request,response);
    }*/
	
	public String XMLtoJSON(String xml) 
	{
			JSONObject jsonObj = XML.toJSONObject(xml);
			String json = jsonObj.toString();
		return json;
	}
}