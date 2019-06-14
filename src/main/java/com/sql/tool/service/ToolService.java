package com.sql.tool.service;



import com.sql.tool.config.Config;
import com.sql.tool.mapper.ToolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToolService {
    @Value("${sqlpath}")
    private String sqlpath;
    @Value("${index}")
    private int index;

    @Autowired
    private ToolMapper toolMapper;
    @Autowired
    private Config config;

    /**
     * 更新sqlservice
     */
    public List<String> updatesqlservice(String sqlfiles){
        List<String> result=new ArrayList<String>();
        String temp=null;
        String reportid=null;
        String reportsql=null;
        Map<String,String> filenames =new HashMap<String,String>();
        Map<String, String> keymap=config.getKeyMap();
        String sqlfile[] = sqlfiles.split(",");
        filenames=getsqlfilename();
        String sqlid=null;
        if(filenames!=null&&filenames.size()>0){
        if (sqlfiles!=null&&sqlfiles.length()>0) {

                for (int i = 0; i < sqlfile.length; i++) {
                    try {
                    reportsql = readSqlFile(filenames.get(sqlfile[i]));
                    sqlid=sqlfile[i].substring(0,index);
                    temp=sqlfile[i].substring(0,2);
                    reportid=keymap.get(temp);
                    if(reportid!=null&&reportid.length()>0) {
                        toolMapper.updatesql(reportsql, reportid);
                    }
                        result.add("成功更新: "+sqlfile[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.add("失败更新: "+sqlfile[i]);
                    }
                }

        }}
        return result;
    }

    /**
     * 遍历sql文件
     */
    public Map<String,String> getsqlfilename(){
        Map<String,String> filenames =new HashMap<String,String>();

        File dir = new File(sqlpath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if(fileName.endsWith(".sql")){
                    filenames.put(fileName.substring(0,fileName.indexOf(".")),files[i].getAbsolutePath());
                }

            }

        }
        return filenames;
    }

    /**
     * 读取SQL文件内容
     */
    public String readSqlFile(String fileName)  {
        File file = new File(fileName);
        StringBuffer sql = new StringBuffer();
        InputStream is=null;
        BufferedReader br=null;
        try{
         is = new FileInputStream(fileName);
         br = new BufferedReader(new InputStreamReader(is, "GB2312"));
        String temp;
        while ((temp = br.readLine()) != null) {
            sql.append(temp).append("\n");
        }
            is.close();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sql.toString();
    }





}
