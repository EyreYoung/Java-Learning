package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewStack {

    public static void main(String[] args) {
        NewStack stack = new NewStack();
        stack.push(600);
        stack.push(300);
        stack.push(800);
        stack.push(200);
        stack.push(400);
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.getMin());
    }

    public ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> minList = new ArrayList<>();

    public Integer poll() {
        if (list.isEmpty()) {
            throw new RuntimeException();
        }
        Integer ret = list.get(list.size() - 1);
        if(ret == minList.get(minList.size() - 1)) {
            minList.remove(ret);
        }
        list.remove(ret);
        return ret;
    }

    public Integer push(Integer newInt) {
        if (Objects.isNull(newInt)){
            return 0;
        }
        list.add(newInt);
        if (minList.isEmpty() || newInt < minList.get(minList.size() - 1)) {
            minList.add(newInt);
        }
        return 1;
    }

    public Integer getMin() {
        if (list.isEmpty()){
            throw new RuntimeException();
        }
        return minList.get(minList.size() - 1);
    }

}
