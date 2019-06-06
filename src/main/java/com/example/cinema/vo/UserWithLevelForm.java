package com.example.cinema.vo;

/**带level的User表
 * @author sky
 * @date 2019/6/6 20:13
 */
public class UserWithLevelForm {
    private String username;
    private String password;
    private Integer level;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
