/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.sprintemplates.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author user
 */
public class ThaiFilter implements Filter {
    
      private ArrayList<String> urlList;
    
    public ThaiFilter() {
    }    

      @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
         try {
            request.setCharacterEncoding("UTF-8");
           /*  HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse res = (HttpServletResponse)response;
            
            
             String url = req.getServletPath();
            
             if (url.contains("login.jsp"))
		{
			chain.doFilter(request, response);
		}
		else
		{
	        if (req.getSession().getAttribute("profile") == null )
			{
                                req.getRequestDispatcher("login.jsp").forward(req, res);
				//chain.doFilter(req, res);
			}
			else
			{
				chain.doFilter(request, response);
			}
		}*/	
            
           chain.doFilter(request, response);

           
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ThaiFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig config)throws ServletException {        
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
  
        urlList = new ArrayList<String>();
  
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
  
        }
    }

}
