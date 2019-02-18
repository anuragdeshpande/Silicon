package framework.enums;

public enum Environments {

    CC8DEV("CC8DEV"),
    CC8DEV3("CC8DEV3"),
    CC8UAT("CC8UAT"),
    CC8QA("CC8QA"),
    CC8IT("CC8IT");

    private String environment;

    Environments(String environment) {
        this.environment = environment;
    }
}
