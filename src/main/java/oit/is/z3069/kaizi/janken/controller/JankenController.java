package oit.is.z3069.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import oit.is.z3069.kaizi.janken.model.Janken;
import oit.is.z3069.kaizi.janken.model.Match;
import oit.is.z3069.kaizi.janken.model.MatchMapper;
import oit.is.z3069.kaizi.janken.model.User;
import oit.is.z3069.kaizi.janken.model.UserMapper;
import oit.is.z3069.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchMapper matchMapper;

  @PostMapping("/login")
  public String Login(@RequestParam("userName") String userName, HttpSession session) {
    session.setAttribute("userName", userName);

    return "redirect:/janken";
  }

  @GetMapping("/janken")
  public String Janken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    ArrayList<User> users = userMapper.selectALLUser();
    ArrayList<Match> matches = matchMapper.selectAllMatchs();

    model.addAttribute("entry", this.entry);
    model.addAttribute("userName", loginUser);
    model.addAttribute("matches", matches);
    model.addAttribute("entryUsers", users);

    return "janken.html";
  }

  @Transactional
  @GetMapping("/jankenfight")
  public String Fight(@RequestParam String hand, Principal prin, ModelMap model) {
    String userName = prin.getName();
    User loginUser = userMapper.selectUserByName(userName);
    User cpuUser = userMapper.selectUserByName("CPU");

    Janken match = new Janken(hand);
    Match newMatch = new Match();

    newMatch.setUser1(loginUser.getId());
    newMatch.setUser2(cpuUser.getId());

    newMatch.setUser1Hand(hand);
    newMatch.setUser2Hand("Goo");

    matchMapper.insertMatch(newMatch);

    model.addAttribute("userName", userName);
    model.addAttribute("matchResult", match);
    model.addAttribute("result_show", true);

    return "match.html";
  }

  @GetMapping("/match")
  public String Match(@RequestParam int id, Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    User user = userMapper.selectUserById(id);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("userid", user.getUserName());
    return "match.html";
  }
}
