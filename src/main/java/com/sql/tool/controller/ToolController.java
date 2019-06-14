package com.sql.tool.controller;



import com.sql.tool.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;


@Controller
@RequestMapping(path = "/report")
public class ToolController {

   @Autowired
   private ToolService toolService;





    @GetMapping(path = "/sqlupdate")
    public String sqlupdate( Model model,@RequestParam(value = "sqlfiles") String sqlfiles){
         List<String> result= toolService.updatesqlservice(sqlfiles);
        Map<String,List<String>> re=new HashMap<String,List<String>>();
        re.put("re",result);
         model.addAllAttributes(re);
         return "result";
    }

    @GetMapping(path = "/tool")
    public String tool(Model model){
        Map<String,String> filenames=toolService.getsqlfilename();
        List<String> list=new ArrayList<String>();
        for(String sqlname:filenames.keySet()){
            list.add(sqlname);
        }
        Collections.sort(list);
        Map<String,List<String>> sqlname=new HashMap<String,List<String>>();
        sqlname.put("list",list);
        model.addAllAttributes(sqlname);
        return "index";
    }
}
