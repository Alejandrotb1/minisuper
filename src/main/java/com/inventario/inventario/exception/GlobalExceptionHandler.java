package com.inventario.inventario.exception;




import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "psgina solicitada no fue encontrada.");
        return "404";
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("404");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("404");
    }


    @ExceptionHandler(SecurityException.class)
    public String handle401(SecurityException ex, Model model) {
        model.addAttribute("errorMessage", "sin  permiso para acceder a esta pagina.");
        return "/401";
    }

    @ExceptionHandler(Exception.class)
    public String handle500(Exception ex, Model model) {
        model.addAttribute("errorMessage", " error interno en el servidor.");
        return "/500";
    }


    //    @ExceptionHandler(EntityNotFoundException.class) // Entidad no encontrada
//    public String handleEntityNotFound(EntityNotFoundException ex, Model model) {
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "404";



}
