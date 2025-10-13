package oit.is.z3069.kaizi.janken.model;

public class Janken {
  private String userHand;
  private String cpuHand = "Gu"; // CPUは常にグー
  private String result;

  public Janken(String userHand) {
    this.userHand = userHand;
    this.result = this.judge(userHand);
  }

  private String judge(String user) {
    if (user.equals("Goo")) {
      return "Draw"; // 引き分け
    } else if (user.equals("Choki")) {
      return "You Lose!"; // 負け
    } else {
      return "You Win!"; // 勝ち
    }
  }

  public String getUserHand() {
    return userHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
