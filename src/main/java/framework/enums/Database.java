package framework.enums;

public enum Database {
    GWDEV("fbmsgwsql-dev02.idfbins.com", "ccUserDEV", "mau$2ug"),
    GWUAT("fbmsgwsql-uat02.idfbins.com", "ccUserUAT", "mau$2ug"),
    GWIT("fbmsgwsql-it02.idfbins.com", "ccUserIT", "mau$2ug"),
    GWQA("fbmsgwsql-qa02.idfbins.com", "ccUserQA", "mau$2ug"),
    REPORTING("FBMSGWSQL-RR01", "sa", "mau$2ugguidewire"),
    REGR01("fbmsgwsql-rr01", "sa", "mau$2ugguidewire"),
    REGR02("fbmsgwsql-rr02", "sa", "mau$2ugguidewire"),
    REGR03("fbmsgwsql-rr03", "sa", "mau$2ugguidewire"),
    REGR04("fbmsgwsql-rr04", "sa", "mau$2ugguidewire"),
    REGR05("fbmsgwsql-rr05", "sa", "mau$2ugguidewire"),
    REGR06("fbmsgwsql-rr06", "sa", "mau$2ugguidewire"),
    REGR07("fbmsgwsql-rr07", "sa", "mau$2ugguidewire"),
    QAWIZPRO_DATA_REPO("fbms2048", "qawizproglobal", "test4work");

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
