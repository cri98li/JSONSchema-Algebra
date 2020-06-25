package patterns;

interface REBoolVisitor {

    boolean visit(RE re);
    boolean visit(RETokenAny re);
    boolean visit(RETokenBackRef re);
    boolean visit(RETokenChar re);
    boolean visit(RETokenEnd re);
    boolean visit(RETokenEndSub re);
    boolean visit(RETokenLookAhead re);
    boolean visit(RETokenOneOf re);
    boolean visit(RETokenPOSIX re);
    boolean visit(RETokenRange re);
    boolean visit(RETokenRepeated re);
    boolean visit(RETokenStart re);
    boolean visit(RETokenWordBoundary re);

}
