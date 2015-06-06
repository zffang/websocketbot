/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.websocketbot;

import java.util.Calendar;
import java.util.List;
import javax.inject.Named;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.WoodInterface;

@Named
public class BotBean {

    private int day = 0;
    private String msg = "";
    /* Respond to a message from the chat */

    public static void main(String[] args) {
        new BotBean().respond("北京好热");
    }

    public String respond(String msg1) {
        String response = "对不起,我不明白您在说什么。您可以问我中国各个城市今天以及"
                + "未来5天的天气以及气温、紫外线指数、感冒指数、空调指数、污染指数、"
                + "运动指数、穿衣指数以及舒适指数这些与天气相关的问题!";
        JavaDB dB = new JavaDB();
        Weather w = new Weather();
        String city = "天津";
        List<Term> alist = null;
        /* Remove question marks */
        this.msg = msg1.toLowerCase().replaceAll("\\?", "");
            System.out.println("msg_" + msg);
         alist = ToAnalysis.parse(msg);
         System.out.println("alist" + alist.toString());
         for (int i = 0; i < alist.size(); i++) {
         if (dB.isCity(alist.get(i).getName())) {
         city = alist.get(i).getName();
         }
         }
       
        this.getDay();
        System.out.print("city_" + city);
        System.out.print("day_" + day);
        w = w.getweather(city, day);
        System.out.print("weather_" + w.toString());

        if (msg.contains("有雨吗") || msg.contains("下雨吗")) {
            if (w.getWeather().contains("雨")) {
                response = w.getWeather();
            } else {
                response = w.getWeather() + ",没有雨";
            }
        } else if (msg.contains("天气") || msg.contains("好热")) {
            if (w.getDirection1() == null) {
                response = w.getHigh() + "-" + w.getLow() + "℃," + w.getWeather() + "," + "风" + w.getPower1() + "级";
            } else {
                response = w.getHigh() + "-" + w.getLow() + "℃," + w.getWeather() + "," + w.getDirection1() + "风" + w.getPower1() + "级";
            }

        } else if (msg.contains("温度")) {
            response = w.getHigh() + "-" + w.getLow() + "℃";
        } else if (msg.contains("风")) {
            response = w.getDirection1() + "风" + w.getPower1() + "级";
        } else if (msg.contains("最高气温") || msg.contains("热吗")) {
            response = "最高气温：" + w.getHigh() + "℃";
        } else if (msg.contains("最低气温") || msg.contains("冷吗")) {
            response = "最低气温：" + w.getLow() + "℃";
        } else if (msg.contains("紫外线")) {
            response = w.getZwx_s();
        } else if (msg.contains("洗车")) {
            response = "洗车指数：" + w.getXcz() + "," + w.getXcz_s();
        } else if (msg.contains("感冒") || msg.contains("病")) {
            response = "感冒指数：" + w.getGm() + ",感冒" + w.getGm_l() + "," + w.getGm_s();
        } else if (msg.contains("风")) {
            response = w.getDirection1() + ",风" + w.getPower1();
        } else if (msg.contains("空气") || msg.contains("污染")) {
            response = "污染指数：" + w.getPollution() + "," + w.getPollution_l() + "," + w.getPollution_s();
        } else if (msg.contains("运动") || msg.contains("室外")) {
            response = "运动指数：" + w.getYd() + "," + w.getYd_l() + "运动," + w.getYd_s();
        } else if (msg.contains("舒适")) {
            response = "舒适指数：" + w.getSsd() + "," + w.getSsd_l() + "," + w.getSsd_s();
        } else if (msg.contains("衣") || msg.contains("穿")) {
            response = "穿衣指数：" + w.getChy() + ",穿衣类别：" + w.getChy_l() + ",穿衣说明" + w.getChy_shuoming();
        } else if (msg.contains("空调") ) {
            response = "空调指数：" + w.getKtk()+ "," + w.getKtk_s();
        }else {
            response = "对不起,我不明白您在说什么。您可以问我中国各个城市今天以及未来"
                    + "5天的天气以及气温、紫外线指数、感冒指数、空调指数、污染指数、"
                    + "运动指数、穿衣指数以及舒适指数这些与天气相关的问题！";
        }
//原来的代码
//        if (msg.contains("how are you")) {
//            response = "I'm doing great, thank you!";
//        } else if (msg.contains("how old are you")) {
//            Calendar dukesBirthday = new GregorianCalendar(1995, Calendar.MAY, 23);
//            Calendar now = GregorianCalendar.getInstance();
//            int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
//            response = String.format("I'm %d years old.", dukesAge);
//        } else if (msg.contains("when is your birthday")) {
//            response = "My birthday is on May 23rd. Thanks for asking!";
//        } else if (msg.contains("your favorite color")) {
//            response = "My favorite color is blue. What's yours?";
//        } else {
//            response = "Sorry, I did not understand what you said. ";
//            response += "You can ask me how I'm doing today; how old I am; or ";
//            response += "what my favorite color is.";
//        }
//        try {
//            Thread.sleep(1200);
//        } catch (InterruptedException ex) { }
        return response;
    }

    private void getDay() {
        this.day = 0;
        Calendar ca = Calendar.getInstance();
        int dayofweek = ca.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            if (msg.contains("星期一") || msg.contains("星期1") || msg.contains("周一") || msg.contains("周1")) {
                this.day = 1;
            } else if (msg.contains("星期二") || msg.contains("星期2") || msg.contains("周二") || msg.contains("周2")) {
                this.day = 2;
            } else if (msg.contains("星期三") || msg.contains("星期3") || msg.contains("周三") || msg.contains("周3")) {
                this.day = 3;
            } else if (msg.contains("星期四") || msg.contains("星期4") || msg.contains("周四") || msg.contains("周4")) {
                this.day = 4;
            } else if (msg.contains("星期五") || msg.contains("星期5") || msg.contains("周五") || msg.contains("周5")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 2) {
            if (msg.contains("星期二") || msg.contains("星期2") || msg.contains("周二") || msg.contains("周2")) {
                this.day = 1;
            } else if (msg.contains("星期三") || msg.contains("星期3") || msg.contains("周三") || msg.contains("周3")) {
                this.day = 2;
            } else if (msg.contains("星期四") || msg.contains("星期4") || msg.contains("周四") || msg.contains("周4")) {
                this.day = 3;
            } else if (msg.contains("星期五") || msg.contains("星期5") || msg.contains("周五") || msg.contains("周5")) {
                this.day = 4;
            } else if (msg.contains("星期六") || msg.contains("星期6") || msg.contains("周六") || msg.contains("周6")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 3) {
            if (msg.contains("星期三") || msg.contains("星期3") || msg.contains("周三") || msg.contains("周3")) {
                this.day = 1;
            } else if (msg.contains("星期四") || msg.contains("星期4") || msg.contains("周四") || msg.contains("周4")) {
                this.day = 2;
            } else if (msg.contains("星期五") || msg.contains("星期5") || msg.contains("周五") || msg.contains("周5")) {
                this.day = 3;
            } else if (msg.contains("星期六") || msg.contains("星期6") || msg.contains("周六") || msg.contains("周6")) {
                this.day = 4;
            } else if (msg.contains("星期日") || msg.contains("周日")) {
                this.day = 1;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 4) {
            if (msg.contains("星期四") || msg.contains("星期4") || msg.contains("周四") || msg.contains("周4")) {
                this.day = 1;
            } else if (msg.contains("星期五") || msg.contains("星期5") || msg.contains("周五") || msg.contains("周5")) {
                this.day = 2;
            } else if (msg.contains("星期六") || msg.contains("星期6") || msg.contains("周六") || msg.contains("周6")) {
                this.day = 3;
            } else if (msg.contains("星期日") || msg.contains("周日")) {
                this.day = 4;
            } else if (msg.contains("下周一") || msg.contains("下周1") || msg.contains("下个星期一")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 5) {
            if (msg.contains("星期五") || msg.contains("星期5") || msg.contains("周五") || msg.contains("周5")) {
                this.day = 1;
            } else if (msg.contains("星期六") || msg.contains("星期6") || msg.contains("周六") || msg.contains("周6")) {
                this.day = 2;
            } else if (msg.contains("星期日") || msg.contains("周日")) {
                this.day = 3;
            } else if (msg.contains("下周一") || msg.contains("下周1") || msg.contains("下个星期一")) {
                this.day = 4;
            } else if (msg.contains("下周二") || msg.contains("下周2") || msg.contains("下个星期二")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 6) {
            if (msg.contains("星期六") || msg.contains("星期6") || msg.contains("周六") || msg.contains("周6")) {
                this.day = 1;
            } else if (msg.contains("星期日") || msg.contains("周日")) {
                this.day = 2;
            } else if (msg.contains("下周一") || msg.contains("下周1") || msg.contains("下个星期一")) {
                this.day = 3;
            } else if (msg.contains("下周二") || msg.contains("下周2") || msg.contains("下个星期二")) {
                this.day = 4;
            } else if (msg.contains("下周三") || msg.contains("下周3") || msg.contains("下个星期三")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 6) {
            if (msg.contains("星期日") || msg.contains("周日")) {
                this.day = 1;
            } else if (msg.contains("下周一") || msg.contains("下周1") || msg.contains("下个星期一")) {
                this.day = 2;
            } else if (msg.contains("下周二") || msg.contains("下周2") || msg.contains("下个星期二")) {
                this.day = 3;
            } else if (msg.contains("下周三") || msg.contains("下周3") || msg.contains("下个星期三")) {
                this.day = 4;
            } else if (msg.contains("下周四") || msg.contains("下周4") || msg.contains("下个星期四")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }
        if (dayofweek == 7) {
            if (msg.contains("下周一") || msg.contains("下周1") || msg.contains("下个星期一")) {
                this.day = 1;
            } else if (msg.contains("下周二") || msg.contains("下周2") || msg.contains("下个星期二")) {
                this.day = 2;
            } else if (msg.contains("下周三") || msg.contains("下周3") || msg.contains("下个星期三")) {
                this.day = 3;
            } else if (msg.contains("下周四") || msg.contains("下周4") || msg.contains("下个星期四")) {
                this.day = 4;
            } else if (msg.contains("下周五") || msg.contains("下周5") || msg.contains("下个星期五")) {
                this.day = 5;
            }
        }
        if (this.day != 0) {
            return;
        }

        ca.add(Calendar.DATE, 1);//明天
        if (msg.contains("明天") || msg.contains(ca.get(Calendar.DATE) + "日") || msg.contains(ca.get(Calendar.DATE) + "号")) {
            day = 1;
            return;
        }
        ca.add(Calendar.DATE, 1);//后天
        if (msg.contains("后天") || msg.contains(ca.get(Calendar.DATE) + "日") || msg.contains(ca.get(Calendar.DATE) + "号")) {
            day = 2;
            return;
        }
        ca.add(Calendar.DATE, 1);//后天
        if (msg.contains("大后天") || msg.contains(ca.get(Calendar.DATE) + "日") || msg.contains(ca.get(Calendar.DATE) + "号")) {
            day = 3;
            return;
        }
        ca.add(Calendar.DATE, 1);//后天
        if (msg.contains(ca.get(Calendar.DATE) + "日") || msg.contains(ca.get(Calendar.DATE) + "号")) {
            day = 4;
            return;
        }
        ca.add(Calendar.DATE, 1);//后天
        if (msg.contains(ca.get(Calendar.DATE) + "日") || msg.contains(ca.get(Calendar.DATE) + "号")) {
            day = 5;
            return;
        }

    }
}
