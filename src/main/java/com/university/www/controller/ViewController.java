package com.university.www.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Arrays;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) throws InvalidNameException {
        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        X509Certificate cert = certs[0];

        String name = cert.getSubjectDN().getName();
        LdapName ln = new LdapName(name);
        String username = "";
        String certificate = "";
        for(Rdn rdn : ln.getRdns()){
            if(rdn.getType().equalsIgnoreCase("CN")){
                username = rdn.getValue().toString();
            }
            if(rdn.getType().equalsIgnoreCase("OU")){
                certificate = rdn.getValue().toString();
            }
        }

        ModelAndView view = new ModelAndView("login");
        view.addObject("username", username);
        view.addObject("certificate", certificate);

        return view;
    }

    @GetMapping("/denied")
    public String error403(){
        return "403";
    }

    @Secured("ROLE_STUDENT")
    @GetMapping("/student")
    public String studentView(){
        return "student";
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping("/professor")
    public String professorView(){
        return "professor";
    }

    @Secured("ROLE_ADMINISTRATOR")
    @GetMapping("/administrator")
    public String administratorView(Principal principal){
        return "administrator";
    }

}
