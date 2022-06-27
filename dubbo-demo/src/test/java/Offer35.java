import java.util.HashMap;
import java.util.Map;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-25 21:49
 */
public class Offer35 {

    public static void main(String[] args) {
        Offer35 offer35 = new Offer35();

        Node node = new Node(1);

        node.next = new Node(2);

        node.next.random = node.next;

        Node node1 = offer35.copyRandomList(node);

        System.out.println(node1);
    }

    private Map<Node,Node> cache = new HashMap<>();


    public Node copyRandomList(Node head) {



        if (head==null){
            return null;
        }

        if (!cache.containsKey(head)) {

            Node node = new Node(head.val);
            cache.put(head , node);
            node.next = copyRandomList(head.next);
            node.random =copyRandomList(head.random);
        }
        return cache.get(head);
    }




    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
