package hxzy.ptb.hxzyappkit.domain;

import hxzy.ptb.hxzyappkit.App;

/**
 * Created by xiantm on 2018/2/28.
 */

public class User {

    private String id;
    private String username;
    private String password;
    private String account;
    private String deptname;

    public User() {

    }

    public User(String id, String username, String password, String account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public User(String username, String deptname) {
        this.username = username;
        this.deptname = deptname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
}
