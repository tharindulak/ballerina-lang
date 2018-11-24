function testUnreachableStmtInIfFunction1() {
    int a = 2;
    if (a > 0) {
        string s1 = "hello if";
    } else {
        string s2 = "hello else";
    }
    return;
    if(a > 10) {
        int number = 10;
    }
    int i = 9;
}

function testUnreachableStmtInIfFunction2() {
    int a = 2;
    int b;
    if (a > 0) {
        string s1 = "hello if";
    } else {
        string s2 = "hello else";
    }
    b = 7;
    return;
    int i = 9;
}

function testUnreachableStmtInIfBlock() returns (int) {
    int a = 2;
    if (a > 0) {
        string s1 = "hello if";
        return a;
        int b = 10;
    } else {
        string s2 = "hello else";
    }
    return a;
}

function testUnreachableStmtInWhileBlock() {
    int a = 2;
    while(a < 4) {
        a = a + 1;
        if (a == 3) {
            return;
            string s = "hello";
        }
    }
}

function testCommentAfterReturnStmt() returns (int) {
    int a = 2;
    if (a > 0) {
        string s1 = "hello if";
        return 1;
        //comment after return stmt
    } else {
        string s2 = "hello else";
    }
    return a;
    //comment after return stmt
    int x = 10;
    return x;
}

function testUnreachableTrapExpression() returns (string){
    string a = "";
    if (2 > 1) {
        return "one";
    } else {
        return "two";
    }
    var newVar = trap "abc";

    if newVar is error {
        return "catch1";
    }
    return a;
}

function testUnreachableNext() returns (string){
    while (true) {
        return "unreachable continue";
        continue;
    }
    return "done";
}

function testUnreachableBreak() returns (string){
    if (true) {
        return "unreachable break";
        break;
    }
    return "done";
}

public type testError record {
    string message;
    error cause;
    string code?;
    !...
};

function testUnreachableThrow (int value) returns (string) {
    if (value > 10) {
        testError tError = {message: "error", cause: error("errorMsg", {code:"test"})};
        return "unreachable throw";
        panic tError.cause;
    }
    return "done";
}

function testRedeclareFunctionArgument (int value) returns (string) {
    int value = 11;
    if (value > 10) {
        testError tError = {message: "error", cause: error("errorMsg", {code:"test"})};
        return "unreachable throw";
        panic tError.cause;
    }
    return "done";
}