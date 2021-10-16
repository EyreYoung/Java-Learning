package com.ke.enumTest;


public enum StateType implements behavior {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(405, "Not Found");

    private int code;
    private String line = null;

    StateType(int code, String line) {
        this.code = code;
        this.line = line;
    }

    public int getCode() {
        return code;
    }

    public String getLine() {
        return line;
    }

    public static Boolean isValidateStateType(String... statetype) {
        boolean flag = false;
        for (String s : statetype) {
            StateType[] values = StateType.values();
            for (StateType type : values
            ) {
                if (type.line.equals(s)) flag = true;
            }
        }
        return flag;
    }

    @Override
    public String toString() {
        return "StateType{" +
                "code=" + code +
                ", line='" + line + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("StateType.OK.getCode() = " + StateType.OK.getCode());
        System.out.println("StateType.isValidateStateType(\"OK\") = " + StateType.isValidateStateType("Forbidden"));
        System.out.println("StateType.OK = " + StateType.OK);
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String getInfo() {
        return this.line;
    }
}

class Test{

    StateType stateType = StateType.OK;

    public void change(){
        switch (stateType) {
            case OK:
                stateType = StateType.NOT_FOUND;
                break;
            case FORBIDDEN:
                stateType = StateType.UNAUTHORIZED;
                break;
            case NOT_FOUND:
                stateType = StateType.BAD_REQUEST;
                break;
        }
    }

    public static void main(String[] args) {
        Test test;
        test = new Test();
        System.out.println("test.stateType = " + test.stateType);
        test.change();
        System.out.println("test.stateType = " + test.stateType);
    }
}

interface behavior{
    void print();
    String getInfo();
}
