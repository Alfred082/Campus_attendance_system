package com.jdk.projectinterface;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jdk.projectinterface.bean.Admin;
import com.jdk.projectinterface.bean.Teacher;
import com.jdk.projectinterface.mapper.AdminMapper;
import com.jdk.projectinterface.mapper.TeacherMapper;
import com.jdk.projectinterface.utils.Utils;
import org.apache.catalina.filters.ExpiresFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootTest
class ProjectInterfaceApplicationTests {

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void testAccountLogin() {

        QueryWrapper<Teacher> teacherQuery = new QueryWrapper<Teacher>().eq("teacher_account",000001).eq("teacher_password",000000);
        System.out.println(teacherQuery);
        Teacher teacher = teacherMapper.selectOne(teacherQuery);
        System.out.println(teacher);
    }

    @Test
    public void testFaceRec() {
        String path1 = "F:\\NIITProject\\project-interface\\src\\main\\resources\\static\\check\\13_55.png";
        String path2 = "F:\\NIITProject\\project-interface\\src\\main\\resources\\static\\face\\13.png";

        StringBuffer sb = new StringBuffer();
        try {
            String[] args = new String[]{"python", "F:/NIITProject/face-recognition/main.py", path1, path2};

            Process process = Runtime.getRuntime().exec(args);
            System.out.println(process.waitFor());
            System.out.println(process.getOutputStream());
            process.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.err.println(in.read());
            String line;
            while ((line = in.readLine()) != null) {
                System.err.println(line);
                sb.append(line);
            }
            in.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPython() throws IOException, InterruptedException {
        // 待执行的Python脚本

        String path1 = "\"F:\\NIITProject\\project-interface\\src\\main\\resources\\static\\check\\13_55.png\"";
        String path2 = "\"F:\\NIITProject\\project-interface\\src\\main\\resources\\static\\face\\13.png\"";
        // 创建ProcessBuilder对象
        ProcessBuilder pb = new ProcessBuilder("F:\\Anaconda3\\envs\\niit-project\\python.exe", "F:\\NIITProject\\face-recognition\\main.py", path1, path2);
        // 启动进程并等待执行完成
        Process process = pb.start();
        process.waitFor();
        System.out.println(process.getErrorStream());

        // 读取Python脚本的输出结果
        InputStream out = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(out));
        System.err.println(reader.readLine());
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}
