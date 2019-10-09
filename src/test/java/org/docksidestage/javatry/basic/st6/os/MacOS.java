package org.docksidestage.javatry.basic.st6.os;

public class MacOS extends OS {
    public MacOS(String loginId) {
        super(loginId, "/", "/Users/" + loginId);
    }
}
