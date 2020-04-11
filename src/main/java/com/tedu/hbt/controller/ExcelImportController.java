package com.tedu.hbt.controller;

import com.tedu.hbt.pojo.Person;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller

public class ExcelImportController {

    @GetMapping("/toHtml")
    String test(HttpServletRequest request) {

        return "excelImport";

    }

//处理文件上传

    @ResponseBody//返回json数据

    @RequestMapping(value = "/excelImport", method = RequestMethod.POST)

    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {

            return "文件为空！";

        }

        try {

//根据路径获取这个操作excel的实例

            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());            //根据页面index 获取sheet页

            HSSFSheet sheet = wb.getSheetAt(0);

//实体类集合

            List<Person> importDatas = new ArrayList<>();

            HSSFRow row = null;

//循环sesheet页中数据从第二行开始，第一行是标题

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

//获取每一行数据

                row = sheet.getRow(i);

                Person data = new Person();

                data.setId(Integer.valueOf((int) row.getCell(0).getNumericCellValue()));
                data.setName(row.getCell(1).getStringCellValue());

                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                //data.setCreateDate(df.parse(df.format(HSSFDateUtil.getJavaDate(row.getCell(2).getNumericCellValue()))));
                data.setGender(row.getCell(2).getStringCellValue());

                data.setAge(Integer.valueOf((int) row.getCell(3).getNumericCellValue()));
                importDatas.add(data);

            }

//循环展示导入的数据，实际应用中应该校验并存入数据库

            for (Person imdata : importDatas) {

                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

                System.out.println("ID:" + imdata.getId() + " name:" + imdata.getName() + " age:" + imdata.getGender() + " createDate:" + df.format(imdata.getAge()));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "导入成功!";

    }

}