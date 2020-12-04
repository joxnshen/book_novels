//package cn.book.bus.test;
//
//import cn.book.bus.utils.FictionUtil;
//import cn.book.bus.utils.JsoupUtil;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//
//import java.util.*;
//
//public class Test {
//    public static void main(String[] args) {
////        Test test=new Test();
////        test.getFiction("重生之都市狂仙",3);
////        String s = "150,150,204,200";
////        String[] as = s.split(",");
////        for (int i = 0; i < as.length; i++) {
////            System.out.println(as[i]);
////        }
//
//
//// 传入的所有用户 ID
//        String userIds = "1,2,3,4";
//// 遍历移除用户 ID，并打印到控制台
//        System.out.println(FictionUtil.removeOne(userIds,"2"));
//
////        System.out.println(addOne("1,2","3"));
//    }
//
//    /**
//     * 从书架删除用户添加的书
//     * @param ids 书架id集合
//     * @param id 小说id
//     * @return 从新拼接
//     */
//    public static String removeOne(String ids, String id) {
//        String string = "";
//        String[] result = ids.split(",");
//        for (String s : result) {
//            if (!s.equals(",")) {
//                if (!s.equals(id)) {
//                    if (string.length() > 0) {
//                        string = string.concat("," + s);
//                    } else {
//                        string = string.concat(s);
//                    }
//                }
//            }
//        }
//        return string;
//    }
//
//    /**
//     * 添加书籍到书架
//     * @param ids 书架id
//     * @param id 添加的id
//     * @return 书架id集合
//     */
//    public static String addOne(String ids, String id) {
//        if (null!=ids) {
//            ids = ids.concat("," + id);
//        } else {
//            ids = id;
//        }
//        return ids;
//    }
//
//
//    /**
//     * @param name 小说名称
//     * @param i    分类标识
//     * @return
//     */
//    public String getFiction(String name, int i) {
//
//        //首页
//        String url = "http://www.shuquge.com/category/" + i + "_1.html";
//
//        Document doc = JsoupUtil.getDoc(url);
//        //尾页数
//        Elements e1 = doc.select("div.l");
//        Elements e2 = e1.select("a:eq(12)");
//        String s1 = e2.select("a").attr("abs:href");
//        String s2 = s1.substring(s1.indexOf("_") + 1);
//        int nub = Integer.parseInt(s2.substring(0, s2.indexOf("."))); //总页数
//        System.out.println(nub);
//        for (int j = 1; j < nub; j++) {
//            String url2 = "http://www.shuquge.com/category/" + i + "_" + j + ".html";
//            Document document = JsoupUtil.getDoc(url2);
//            Element element = document.select("div.l").first();
//            Elements elements = element.select("span.s2");
//            for (Element element1 : elements) {
//                String string = element1.select("a").text();
//                if (string.equals(name)) {
//                    return element1.select("a").attr("abs:href");
//                }
//            }
//            System.out.println(j);
//        }
//        return null;
//    }
//
//    public static int sum(int... a) {
//        int sum = 0;
//
//        for (int x : a) {
//            sum += x;
//        }
//        return sum;
//
//    }
//
//    public static String sum(String... strings) {
//        String Str = "";
//
//        for (String x : strings) {
//            Str = x;
//        }
//        return Str;
//
//    }
//
//    public static void Map(HashMap<String, String> map) {
//        Iterator<Map.Entry<String, String>> iterator;
//        iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            String key = (String) entry.getKey();
//            String val = (String) entry.getValue();
//            System.out.println(key + val);
//        }
//    }
//
//
//}
