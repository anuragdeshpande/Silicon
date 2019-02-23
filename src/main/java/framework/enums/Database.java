package framework.enums;

public enum Database {
    GWDEV("fbmsgwsql-dev02.idfbins.com", "ccUserDEV", "mau$2ug"),
    GWUAT("fbmsgwsql-uat02.idfbins.com", "ccUserUAT", "mau$2ug"),
    GWIT("fbmsgwsql-it02.idfbins.com", "ccUserIT", "mau$2ug"),
    GWQA("fbmsgwsql-qa02.idfbins.com", "ccUserQA", "mau$2ug"),
    REPORTING("FBMSGWSQL-RR01", "sa", "mau$2ugguidewire");



    private String serverName;
    private String username;
    private String password;

    Database(String serverName, String username, String password){
        this.serverName = serverName;
        this.username = username;
        this.password = password;
    }

    public String getServerName() {
        return serverName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
