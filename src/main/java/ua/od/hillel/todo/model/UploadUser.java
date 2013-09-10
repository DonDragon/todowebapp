package ua.od.hillel.todo.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created with IntelliJ IDEA.
 * User: altair
 * Date: 10.09.13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class UploadUser {

    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Range(min = 6, max = 100)
    private Integer age;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    private MultipartFile file;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
