package org.docksidestage.javatry.basic.st6.os;

public class Windows extends OS {
    public Windows(String loginId) {
        super(loginId, "\\", "/Users/" + loginId);
    }
}
