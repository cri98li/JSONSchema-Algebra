package patterns;

interface REVisitor {

    void visit(RE re);
    void visit(RETokenAny re);
    void visit(RETokenBackRef re);
    void visit(RETokenChar re);
    void visit(RETokenEnd re);
    void visit(RETokenEndSub re);
    void visit(RETokenLookAhead re);
    void visit(RETokenOneOf re);
    void visit(RETokenPOSIX re);
    void visit(RETokenRange re);
    void visit(RETokenRepeated re);
    void visit(RETokenStart re);
    void visit(RETokenWordBoundary re);

}
