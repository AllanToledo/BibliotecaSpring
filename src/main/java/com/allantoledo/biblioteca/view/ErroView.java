package com.allantoledo.biblioteca.view;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErroView implements ErrorController {

	@ExceptionHandler(Exception.class)
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request, Exception e) {
		String erroMensagem = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();
		String erroCodigo = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		
		erroMensagem += " ";
		erroMensagem += erroCodigo;
		
		var view = new ModelAndView("error");
		view.addObject("erro", erroMensagem);
		return view;
	}
	
}
