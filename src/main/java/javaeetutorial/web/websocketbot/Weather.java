/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.web.websocketbot;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 *
 * @author admin
 */
public class Weather {

    private String city = "天津"; // 城市
    private int day = 0; // 哪一天的天气
    private String weather; // 保存天气情况
    private String high; // 保存当天最高温度
    private String low; // 保存当天最低温度
    private String chy;//穿衣指数
    private String chy_l;//穿衣指数数值
    private String chy_shuoming;//穿衣说明
    private String zwx_l;//紫外线强度
    private String zwx_s;//紫外线强度
    private String status1;
    private String status2;
    private String direction1;//风向
    private String power1;//风级   
    private String ssd;//舒适度
    private String ssd_l;//舒适度指数
    private String ssd_s;//舒适度说明
    private String pollution;//污染
    private String pollution_l;//污染强度
    private String pollution_s;//污染说明
    private String ktk;//空调指数
    private String ktk_s;//空调指数说明
    private String xcz;//洗车指数
    private String xcz_l;
    private String xcz_s;
    private String gm;//感冒指数
    private String gm_l;
    private String gm_s;
    private String yd;//运动指数
    private String yd_l;
    private String yd_s;
public static void main(String[] args) {
	 System.out.println("getweather");
     String city = "天津";
     int day = 0;
     Weather instance = new Weather();
     Weather expResult = instance.getweather(city, day);
     Weather result = instance.getweather(city, day);
     System.out.print(result.toString());
    
}
    public Weather getweather(String city, int day) // 获取天气函数
    {
        this.city = city;
        this.day = day;
        URL url;
        try {

            DocumentBuilderFactory domfac = DocumentBuilderFactory
                    .newInstance(); // 相关这个类的使用，可以去网上搜索，下同，不做详细介绍
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            Document doc;
            Element root;
            NodeList books;
            url = new URL("http://php.weather.sina.com.cn/xml.php?city="
                   + java.net.URLEncoder.encode(city, "GBK") + "&password=DJOYnieT8234jlsK&day=" + day);
             //url = new URL("http://php.weather.sina.com.cn/xml.php?city=天津&password=DJOYnieT8234jlsK&day=0");
            doc = dombuilder.parse(url.openStream());
            root = doc.getDocumentElement();
            books = root.getChildNodes();
            for (Node node = books.item(1).getFirstChild(); node != null; node = node
                    .getNextSibling()) {
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getNodeName().equals("status1")) {
                        status1 = node.getTextContent(); // 获取到天气情况
                    }else if (node.getNodeName().equals("status2")) {
                        status2 = node.getTextContent(); // 获取到最高温度
                    }  else if (node.getNodeName().equals("temperature1")) {
                        high = node.getTextContent(); // 获取到最高温度
                    } else if (node.getNodeName().equals("temperature2")) {
                        low = node.getTextContent(); // 获取到最低温度
                    } else if (node.getNodeName().equals("chy")) {
                        chy = node.getTextContent(); // 获取到穿衣指数
                    } else if (node.getNodeName().equals("chy_l")) {
                        chy_l = node.getTextContent(); // 获取到穿衣指数数值
                    } else if (node.getNodeName().equals("chy_shuoming")) {
                        chy_shuoming = node.getTextContent(); // 获取穿衣说明
                    } else if (node.getNodeName().equals("pollution_l")) {
                        pollution_l = node.getTextContent(); // 获取到污染强度
                    } else if (node.getNodeName().equals("zwx_l")) {
                        zwx_l = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("zwx_s")) {
                        zwx_s = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("power1")) {
                        power1 = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("ssd")) {
                        ssd = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("ssd_l")) {
                        ssd_l = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("ssd_s")) {
                        ssd_s = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("pollution")) {
                        pollution = node.getTextContent(); // 获取到紫外线强度
                    } else if (node.getNodeName().equals("pollution_s")) {
                        pollution_s = node.getTextContent();
                    } else if (node.getNodeName().equals("ktk")) {
                        ktk = node.getTextContent();
                    } else if (node.getNodeName().equals("ktk_s")) {
                        ktk_s = node.getTextContent();
                    } else if (node.getNodeName().equals("xcz")) {
                        xcz = node.getTextContent();
                    } else if (node.getNodeName().equals("xcz_l")) {
                        xcz_l = node.getTextContent();
                    } else if (node.getNodeName().equals("xcz_s")) {
                        xcz_s = node.getTextContent();
                    } else if (node.getNodeName().equals("gm")) {
                        gm = node.getTextContent();
                    } else if (node.getNodeName().equals("gm_l")) {
                        gm_l = node.getTextContent();
                    } else if (node.getNodeName().equals("gm_s")) {
                        gm_s = node.getTextContent();
                    } else if (node.getNodeName().equals("yd")) {
                        yd = node.getTextContent();
                    } else if (node.getNodeName().equals("yd_l")) {
                        yd_l = node.getTextContent();
                    } else if (node.getNodeName().equals("yd_s")) {
                        yd_s = node.getTextContent();
                    }
                }
            }
            if(status1.equals(status2)){
                weather=status1;
            }else{
                weather=status1+"转"+status2;
            }
        } catch (Exception e) {
            System.out.println("获取天气失败:" + e);
        }
        this.toString();
        return this;
    }

    @Override
    public String toString() {
        return this.getChy() + this.getChy_shuoming() + " " + this.getDirection1() + " " + this.getPower1()
                + " " + this.getHigh()
                + " " + this.getLow()
                + " " + this.getGm()
                + " " + this.getGm_s()
                + " " + this.getZwx_l()
                + " " + this.getYd()
                + " " + this.getYd_s()
                + " " + this.getKtk()
                + " " + this.getKtk_s()
                + " " + this.getSsd()
                + " " + this.getSsd_s()
                + " " + this.getPollution()
                + " " + this.getPollution_s()
                + " " + this.getXcz()
                + " " + this.getXcz_s()
                + " " + this.chy
                + " " + this.chy_shuoming;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDay() {
        return day;
    }

    public String getZwx_s() {
        return zwx_s;
    }

    public void setZwx_s(String zwx_s) {
        this.zwx_s = zwx_s;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getChy() {
        return chy;
    }

    public void setChy(String chy) {
        this.chy = chy;
    }

    public String getChy_l() {
        return chy_l;
    }

    public void setChy_l(String chy_l) {
        this.chy_l = chy_l;
    }

    public String getChy_shuoming() {
        return chy_shuoming;
    }

    public void setChy_shuoming(String chy_shuoming) {
        this.chy_shuoming = chy_shuoming;
    }

    public String getZwx_l() {
        return zwx_l;
    }

    public void setZwx_l(String zwx_l) {
        this.zwx_l = zwx_l;
    }

    public String getDirection1() {
        return direction1;
    }

    public void setDirection1(String direction1) {
        this.direction1 = direction1;
    }

    public String getPower1() {
        return power1;
    }

    public void setPower1(String power1) {
        this.power1 = power1;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getSsd_l() {
        return ssd_l;
    }

    public void setSsd_l(String ssd_l) {
        this.ssd_l = ssd_l;
    }

    public String getSsd_s() {
        return ssd_s;
    }

    public void setSsd_s(String ssd_s) {
        this.ssd_s = ssd_s;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getPollution_l() {
        return pollution_l;
    }

    public void setPollution_l(String pollution_l) {
        this.pollution_l = pollution_l;
    }

    public String getPollution_s() {
        return pollution_s;
    }

    public void setPollution_s(String pollution_s) {
        this.pollution_s = pollution_s;
    }

    public String getKtk() {
        return ktk;
    }

    public void setKtk(String ktk) {
        this.ktk = ktk;
    }

    public String getKtk_s() {
        return ktk_s;
    }

    public void setKtk_s(String ktk_s) {
        this.ktk_s = ktk_s;
    }

    public String getXcz() {
        return xcz;
    }

    public void setXcz(String xcz) {
        this.xcz = xcz;
    }

    public String getXcz_l() {
        return xcz_l;
    }

    public void setXcz_l(String xcz_l) {
        this.xcz_l = xcz_l;
    }

    public String getXcz_s() {
        return xcz_s;
    }

    public void setXcz_s(String xcz_s) {
        this.xcz_s = xcz_s;
    }

    public String getGm() {
        return gm;
    }

    public void setGm(String gm) {
        this.gm = gm;
    }

    public String getGm_l() {
        return gm_l;
    }

    public void setGm_l(String gm_l) {
        this.gm_l = gm_l;
    }

    public String getGm_s() {
        return gm_s;
    }

    public void setGm_s(String gm_s) {
        this.gm_s = gm_s;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public String getYd_l() {
        return yd_l;
    }

    public void setYd_l(String yd_l) {
        this.yd_l = yd_l;
    }

    public String getYd_s() {
        return yd_s;
    }

    public void setYd_s(String yd_s) {
        this.yd_s = yd_s;
    }

}
