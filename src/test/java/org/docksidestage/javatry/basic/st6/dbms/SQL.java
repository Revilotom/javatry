package org.docksidestage.javatry.basic.st6.dbms;

public abstract class SQL {

    public String getOffset(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return Integer.toString(offset);
    }
    public abstract String buildPagingQuery(int pageSize, int pageNumber);
}
