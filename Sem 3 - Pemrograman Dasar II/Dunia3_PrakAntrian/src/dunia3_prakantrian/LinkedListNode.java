package dunia3_prakantrian;

/**
 *
 * @author Kyrin
 */
public class LinkedListNode {
    LinkedListNode next;
    LinkedListNode prev;
    String data;
    
    LinkedListNode(String new_data) {
        this.data = new_data;
        this.prev = null;
        this.next = null;
    }

    void set_prev(LinkedListNode other) {
            this.prev = other;
        if (other != null) {
            other.next = this;
        }
    }
    
    void set_next(LinkedListNode other) {
        this.next = other;
        if (other != null) {
            other.prev = this;
        }
    }
}