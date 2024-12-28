package com.inventario.inventario.exception;




import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class) // Error 404
    public String handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "La página solicitada no fue encontrada.");
        return "404"; // Redirige a tu vista 404.html
    }

//    @ExceptionHandler(EntityNotFoundException.class) // Entidad no encontrada
//    public String handleEntityNotFound(EntityNotFoundException ex, Model model) {
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "404"; // Redirige también a tu vista 404.html
//    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("404"); // Redirige a la vista 404.html
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("404"); // Redirige a la vista 404.html
    }


    @ExceptionHandler(SecurityException.class) // Error 401
    public String handle401(SecurityException ex, Model model) {
        model.addAttribute("errorMessage", "No tienes permiso para acceder a esta página.");
        return "/401"; // Redirige a tu vista 401.html
    }

    @ExceptionHandler(Exception.class) // Error 500
    public String handle500(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Ocurrió un error interno en el servidor.");
        return "/500"; // Redirige a tu vista 500.html
    }
}
