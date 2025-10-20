package oit.is.z3069.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import oit.is.z3069.kaizi.janken.model.Janken;
import oit.is.z3069.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @PostMapping("/login")
  public String Login(@RequestParam("userName") String userName, HttpSession session) {
    session.setAttribute("userName", userName);

    return "redirect:/janken";
  }

  @GetMapping("/janken")
  public String Janken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("userName", loginUser);

    return "janken.html";
  }

  @GetMapping("/jankenfight")
  public String Fight(@RequestParam String hand, HttpSession session, ModelMap model) {
    String userName = (String) session.getAttribute("userName");
    model.addAttribute("userName", userName);

    Janken match = new Janken(hand);

    model.addAttribute("matchResult", match);
    model.addAttribute("result_show", true);
    return "janken.html";
  }

}
