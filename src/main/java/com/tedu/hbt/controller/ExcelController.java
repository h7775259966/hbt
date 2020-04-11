package com.tedu.hbt.controller;

import com.tedu.hbt.Utils.ExportExcelUtils;
import com.tedu.hbt.pojo.ExcelData;
import com.tedu.hbt.pojo.Person;
import com.tedu.hbt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouLu on 2017-12-14.
 */
@RestController
public class ExcelController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {

        List<Person> info = personService.findAll();

        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("a1");
        titles.add("a2");
        titles.add("a3");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for (int i = 0; i < info.size(); i++) {//遍历数组，把数组内容放进Excel的行中
            List<Object> row = new ArrayList();
            row.add(info.get(i).getId());
            row.add(info.get(i).getName());
            row.add(info.get(i).getGender());
            row.add(info.get(i).getAge());
            rows.add(i, row);
        }

        data.setRows(rows);


        //生成本地
        /*File f = new File("c:/test.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        ExportExcelUtils.exportExcel(data, out);
        out.close();*/
        ExportExcelUtils.exportExcel(response,"hello.xlsx",data);
    }
}
