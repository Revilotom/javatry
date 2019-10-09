package org.docksidestage.javatry.basic.st6.os;

public abstract class OS {
    private final String loginId;
    private final String separator;
    private final String userDirectory;

    public OS(String loginId, String separator, String userDirectory) {
        this.loginId = loginId;
        this.separator = separator;
        this.userDirectory = userDirectory;
    }

    public String getLoginId() {
        return loginId;
    }
    public String getSeparator() {
        return separator;
    }
    public String getUserDirectory() {
        return userDirectory;
    }
}
