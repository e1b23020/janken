package oit.is.z3069.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class JankenController {

  // 【修正】メソッドを削除。static/index.html に処理を任せることで、Java側のエラーを排除。
  /*
   * @GetMapping("/")
   * public String index() {
   * return "index.html";
   * }
   */

  @PostMapping("/login")
  public String Login(@RequestParam("userName") String userName, HttpSession session) {
    session.setAttribute("userName", userName);

    return "redirect:/janken";
  }

  @GetMapping("/janken")
  public String Janken(HttpSession session, ModelMap model) {

    String userName = (String) session.getAttribute("userName");

    if (userName != null) {
      model.addAttribute("userName", userName);
    }
    return "janken.html";
  }
}
