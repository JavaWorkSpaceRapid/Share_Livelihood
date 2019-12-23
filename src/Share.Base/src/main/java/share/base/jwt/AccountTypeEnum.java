package share.base.jwt;

import lombok.Getter;

public enum AccountTypeEnum {
    web(1,"1","web","web端登录，默认时长30分钟",1800000l),
    phone(2,"2","phone","手机端登录，默认时长2周",1296000000l);

    @Getter
    private int number;
    @Getter
    private String code;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private long timeSpan;

    private AccountTypeEnum(int number, String code, String name, String description, long timeSpan) {
        this.number = number;
        this.code = code;
        this.name = name;
        this.description = description;
        this.timeSpan = timeSpan;
    }

    public static AccountTypeEnum getType(String code) {
        switch (code) {
            case "2":
                return phone;
            default:
                return web;
        }
    }
}
