package org.docksidestage.javatry.basic.st6.os;

public class OldWindows extends OS {
    public OldWindows(String loginId) {
        super(loginId, "\\", "/Documents and Settings/" + loginId);
    }
}
