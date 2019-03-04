package com.example.service.invoker.controller;

import com.example.service.invoker.service.ClientService;
import com.example.service.invoker.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "testRibbon", method = RequestMethod.GET)
    public Object index2() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
         Map map = new HashMap<>();
         map.put("aa","testRibbon");
         map.put("bb", restTemplate.getForObject("http://service-provider:7000/testRibbon",String.class));
         return map;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public Object index() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
         Map map = new HashMap<>();
         map.put("aa","21321");
         map.put("bb", clientService.index());
         return map;
    }
    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public Object index1() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
        Map map = new HashMap<>();
        map.put("aa","21321");
        map.put("bb", clientService.index1());
        return map;
    }

    @RequestMapping(value = "testHystrix", method = RequestMethod.GET)
    public Object testHystrix(@RequestParam("userName") String userName ) throws Exception {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
        Map map = new HashMap<>();
        map.put("aa","testHystrix");
        map.put("bb", userService.getUser(userName));
        return map;
    }


    @RequestMapping(value = "xlsx", method = RequestMethod.POST)
    public void xlsx(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<Teacher> classmateList = null;

        String fileName = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "学号", "姓名", "身份类型", "登录密码"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (int i=0;i<5;i++) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(0);
            row1.createCell(1).setCellValue("1");
            row1.createCell(2).setCellValue("2");
            row1.createCell(3).setCellValue("23");
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());

    }

    public static void main(String[] args) {
        System.out.println(12390-2080-100-7*21-30*36);
    }
}
