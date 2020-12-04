//package cn.book;
//
//import cn.book.bus.domain.Chapter;
//import cn.book.bus.domain.ChapterContent;
//import cn.book.bus.domain.Fiction;
//import cn.book.bus.mapper.ChapterContentMapper;
//import cn.book.bus.mapper.FictionMapper;
//import cn.book.bus.service.*;
//import cn.book.bus.utils.JsoupUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.jsoup.nodes.Document;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//class BookApplicationTests {
//
//
//    @Resource
//    private IUserService iUserService;
//
//    @Resource
//    private FictionMapper fictionMapper;
//
//    @Resource
//    private IFictionService iFictionService;
//    @Resource
//    private IChapterService iChapterService;
//    @Resource
//    private ChapterContentMapper chapterContentMapper;
//
//    @Resource
//    private IChapterContentService iChapterContentService;
//    @Resource
//    private WriteFictionService writeFictionService;
//    @Test
//    void contextLoads() {
//        boolean b = iUserService.verificationUser("9");
//        System.out.println(b);
//
//    }
//
//    //删除小说
//    @Test
//    void delete(){
//        QueryWrapper<Chapter> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("fiction_id",605);
//        //得到章节列表
//        List<Chapter> list = iChapterService.list(queryWrapper);
//        for (Chapter chapter:list){
//            //删除章节内容
//            iChapterContentService.removeById(chapter.getContentId());
//        }
//        //删除章节
//        iChapterService.remove(queryWrapper);
//        //删除小说
//        iFictionService.removeById(605);
//    }
//
//    @Test
//    void getName(){
//        QueryWrapper<Fiction> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("fiction_name","我的系统无所不能");
//        Fiction one = iFictionService.getOne(queryWrapper);
//        System.out.println(one);
//    }
//    @Test
//    void like(){
//        QueryWrapper<Fiction> queryWrapper=new QueryWrapper<>();
//        queryWrapper.like("fiction_name","%万%");
//        List<Fiction> list = iFictionService.list(queryWrapper);
//        HashMap<String,String> hashMap=new HashMap<>();
//        System.out.println(list);
//    }
//    @Test
//    public void testSelectPage(){
//        //构建分页条件第二页每页显示3条
//        Page<Fiction> page=new Page<>(1,10);
//        //使用分页条件查询，不使用其他条件
//        HashMap<String,String> map=new HashMap<>();
//        map.put("state","完结");
//        QueryWrapper<Fiction> queryWrapper = new QueryWrapper<>();
////        queryWrapper.eq("state","完结");
//
//        Iterator<Map.Entry<String, String>> iterator;
//        iterator = map.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry) iterator.next();
//            queryWrapper.eq((String) entry.getKey(),entry.getValue());
//        }
//        fictionMapper.selectPage(page,queryWrapper);
//        Fiction fiction=new Fiction();
//        //获取分页后查询出的记录
//        List<Fiction> records = page.getRecords();
//        System.out.println("/"+records.size());
//        records.forEach(System.out::println);
//        System.out.println("是否有下一页："+page.hasNext());
//        System.out.println("是否有上一页："+page.hasPrevious());
//        System.out.println("一页的条数"+page.getSize());
//        System.out.println("当前页"+page.getCurrent());
//        System.out.println("总记录数："+page.getTotal());
//    }
//}
