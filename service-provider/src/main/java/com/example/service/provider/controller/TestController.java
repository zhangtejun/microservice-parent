package com.example.service.provider.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Data
@Slf4j
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView indexaaa() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
        return "Hello World";
    }
    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public String index2() {
        System.err.println("provider  hello2 testing");
        return "Hello World";
    }
    @GetMapping(value = "testRibbon")
    public String testRibbon(HttpServletRequest httpServletRequest) {
        return "-----------> From Port: "+httpServletRequest.getServerPort();
    }
    @RequestMapping("/testHystrixCache/{string}")
    public String testHystrixCache(@PathVariable("string") String string) {
        return "-----------> testHystrixCache ok "+ string ;
    }

    @RequestMapping(value = "xlsx", method = RequestMethod.POST)
    public ResponseEntity<byte[]> xlsx(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

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
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/octet-stream");
        /*response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());*/
        return ResponseEntity.ok(workbook.getBytes());
    }
}
