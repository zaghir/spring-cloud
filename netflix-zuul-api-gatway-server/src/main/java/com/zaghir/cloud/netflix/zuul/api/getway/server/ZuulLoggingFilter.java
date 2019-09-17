package com.zaghir.cloud.netflix.zuul.api.getway.server;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
 * 
 * @author yzaghir
 *
 * 
 * cette classe implementé dans la getway pour filtrer les requette 
 * elle herite des fonctionnes de faite deja dans Zuul
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean shouldFilter() {
		// faut il activer le filter ou pas => true or false 		
		return true;
	}

	@Override
	public Object run()  {
		/**
		 * la logic de filter , ce qu'il va faire au moment de l'interception de request 
		*/
		HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
		logger.info("ZuulLoggingFilter:run() Request ==> {} Request Uri ==> {}" , httpServletRequest , httpServletRequest.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		/**
		 * le return peut avoir plusieurs valeurs pour executer le filter : 
		 * 	pre   : avant l'execution de request 
		 * 	post  : apres l'execution de request
		 * 	error : ou seulement quand il y a une erreur 
		 * */
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
