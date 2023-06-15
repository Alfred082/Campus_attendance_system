package com.jdk.projectinterface.controller;

import com.jdk.projectinterface.bean.Student;
import com.jdk.projectinterface.bean.Teacher;
import com.jdk.projectinterface.common.ServiceResponse;
import com.jdk.projectinterface.service.AccountService;
import com.jdk.projectinterface.utils.Utils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     * 用户登录
      * @param type 用户角色
     * @param account 账号
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回code401
     */
    @GetMapping(name="用户登录", value="/login")
    @ApiOperation(value = "用户登录")
    public Object login(@RequestParam("type") @ApiParam(value = "用户类型", example = "1") Integer type,
                        @RequestParam("account") @ApiParam(value = "用户账号", example = "000001") String account,
                        @RequestParam("password") @ApiParam(value = "用户密码", example = "000000") String password){
        return accountService.accountLogin(type, account, password);
    }

    /**
     * 注销用户
     * @param type 被删除用户角色
     * @param accountId 被删用户的账户id
     * @return 执行删除操作的结果
     */
    @GetMapping("/deleteAccount")
    @ApiOperation(value = "注销用户")
    public Object deleteAccount(
            @RequestParam("type") @ApiParam(value = "被删除用户的角色", example = "1") Integer type,
            @RequestParam("accountId") @ApiParam(value = "被删除用户的id", example = "1") Integer accountId
    ){
        switch (type){
            case 1:
                /**
                 * 删除教师用户
                 */
                return accountService.deleteTeacher(accountId);
            case 2:
                /**
                 * 删除学生用户
                 */
                return accountService.deleteStudent(accountId);
            default:
                return ServiceResponse.createFailResponse("操作不允许");
        }
    }

    /**
     * 添加学生和教师的两种方法
     */

    @GetMapping("/addTeacher")
    @ApiOperation(value = "添加教师")
    public Object addTeacher(
            @RequestParam("adminId") @ApiParam(value = "管理员id", example = "111") Integer adminId,
            @RequestParam("account") @ApiParam(value = "账号", example = "0000001") String account,
            @RequestParam("password") @ApiParam(value = "密码", example = "000000") String password,
            @RequestParam("name") @ApiParam(value = "姓名", example = "张三") String name,
            @RequestParam("sex") @ApiParam(value = "性别", example = "男") Boolean sex,
            @RequestParam("phone") @ApiParam(value = "电话", example = "130XXXXXXXX") String phone,
            @RequestParam("email") @ApiParam(value = "邮箱", example = "1186419030@qq.com") String email,
            @RequestParam("avatar") @ApiParam(value = "头像", example = "") String avatar
    ){
        ServiceResponse<Teacher> response;
        if (Utils.isEmpty(phone) || Utils.isEmpty(email)){
            response = ServiceResponse.createFailResponse("电话或邮箱填写有误");
        } else {
            Teacher teacher = new Teacher(adminId,account,password,name,sex,phone,email,avatar);
            response = accountService.addTeacher(teacher);
        }
        return response;
    }

    @GetMapping("/addStudent")
    @ApiOperation(value = "添加学生")
    public Object addStudent(
            @RequestParam("account") @ApiParam(value = "账号", example = "00000011") String account,
            @RequestParam("password") @ApiParam(value = "密码", example = "000000") String password,
            @RequestParam("name") @ApiParam(value = "姓名", example = "张三") String name,
            @RequestParam("sex") @ApiParam(value = "性别", example = "男") Boolean sex,
            @RequestParam("major") @ApiParam(value = "专业", example = "软件工程") String classname,
            @RequestParam("phone") @ApiParam(value = "电话", example = "130XXXXXXXX") String phone,
            @RequestParam("email") @ApiParam(value = "邮箱", example = "1186419030@qq.com") String email
    ){
        ServiceResponse<Student> response;
        String avatar = "/image/avatars/user-default.png";
        if (Utils.isEmpty(phone) || Utils.isEmpty(email)){
            response = ServiceResponse.createFailResponse("电话或邮箱填写有误");
        } else {
            Student student = new Student(account,password,name,sex,avatar,classname,phone,email);
            response = accountService.addStudent(student);
        }
        return response;
    }

    /**
     * 修改学生和教师的两种方法
     */
    @GetMapping("/modifyTeacher")
    public Object modifyTeacher(
            @RequestParam("teacherId") Integer teacherId,
            @RequestParam(value = "adminId",required = false) Integer adminId,
            @RequestParam(value = "account",required = false) String account,
            @RequestParam(value = "password",required = false) String password,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "sex",required = false) Boolean sex,
            @RequestParam(value = "phone",required = false) String phone,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "avatar",required = false) String avatar
    ){
        ServiceResponse<Teacher> response;
        Teacher teacher = new Teacher(adminId,account,password,name,sex,phone,email,avatar);
        teacher.setTeacherId(teacherId);
        response = accountService.modifyTeacher(teacher);
        return response;
    }

    @GetMapping("/modifyStudent")
    public Object modifyStudent(
            @RequestParam("studentId") Integer studentId,
            @RequestParam(value = "account",required = false) String account,
            @RequestParam(value = "password",required = false) String password,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "sex",required = false) Boolean sex,
            @RequestParam(value = "avatar",required = false) String avatar,
            @RequestParam(value = "major",required = false) String classname,
            @RequestParam(value = "face",required = false) String face,
            @RequestParam(value = "phone",required = false) String phone,
            @RequestParam(value = "email",required = false) String email
    ){
        ServiceResponse<Student> response;
        Student student = new Student(account,password,name,sex,avatar,classname,phone,email);
        student.setStudentId(studentId);
        student.setStudentFace(face);
        response = accountService.modifyStudent(student);
        return response;
    }

    /**
     * 查询所有用户账号
     */
    @GetMapping("/findAccount")
    public Object findAccount(@RequestParam("type") Integer type){
        switch (type){
            case 1:
                return accountService.findAllTeacher();
            case 2:
                return accountService.findAllStudent();
            default:
                return ServiceResponse.createFailResponse("查询失败");
        }
    }

    /**
     * 通过模糊查询查找用户
     * @return 返回是一个数组
     */
    @GetMapping("/findAccountByColumn")
    public Object findAccountByColumn(
            @RequestParam("type") Integer type,
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ){

        switch (type){
            case 1:
                return accountService.findTeacher(column,value);
            case 2:
                return accountService.findStudent(column,value);
            default:
                return ServiceResponse.createFailResponse("查询失败");
        }
    }

    /**
     * 验证账户是否存在
     */
    @GetMapping("/confirmAccount")
    public Object confirmAccount(
            @RequestParam("type") Integer type,
            @RequestParam("account") String account,
            @RequestParam(value = "phone",required = false) String phone
    ){
        if(phone.isEmpty()) {
            return accountService.confirmAccount(type, account);
        } else {
            return accountService.confirmAccount(type,account,phone);
        }
    }

}
