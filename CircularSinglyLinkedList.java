
import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Kiavosh Peynabard
 * @version 1.0
 * @userid kpeynabard3
 * @GTID 903353136
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data could not be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Selected Index is greater than size");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, null);

            CircularSinglyLinkedListNode<T> oneToTarget = recTraversal(head, index - 1, 0);
            newNode.setNext(oneToTarget.getNext());
            oneToTarget.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data could not be null");
        }

        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, null);

        if (size == 0) {

            head = newNode;
            head.setNext(head);
            size++;

        } else if (size == 1) {

            newNode.setNext(head);
            head.setNext(newNode);
            head = newNode;
            size++;

        } else {

            newNode.setNext(head.getNext());
            head.setNext(newNode);
            T dataTemp = head.getData();
            head.setData(head.getNext().getData());
            head.getNext().setData(dataTemp);
            size++;

        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data could not be null");
        }

        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, null);

        if (size == 0) {
            addToFront(data);
        } else if (size == 1) {
            newNode.setNext(head);
            head.setNext(newNode);
            size++;
        } else {
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            T dataTemp = head.getData();
            head.setData(head.getNext().getData());
            head.getNext().setData(dataTemp);
            head = head.getNext();
            size++;

        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Selected Index is greater than size");
        }
        if (index == 0) {
            return removeFromFront();
        } else {
            CircularSinglyLinkedListNode<T> currNode = recTraversal(head, index - 1, 0);
            T dataTemp = currNode.getNext().getData();
            currNode.setNext(currNode.getNext().getNext());
            size--;
            return dataTemp;
        }

    }
    /**
     * Recursively travel through the list.
     *
     * @return the one to the target node
     * @param currNode is the node to start from, head.
     * @param index desired index to travel to.
     * @param counter to track the traversal.
     */
    private CircularSinglyLinkedListNode<T>
        recTraversal(CircularSinglyLinkedListNode<T> currNode, int index, int counter) {
        if (index == counter) {
            return currNode;
        } else {
            return recTraversal(currNode.getNext(), index, counter + 1);
        }
    }
    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty and does not have such element");
        }
        T dataTemp = head.getData();
        head.setData(head.getNext().getData());
        head.setNext(head.getNext().getNext());
        size--;
        return dataTemp;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (head == null) {
            throw new NoSuchElementException("List is empty and does not have such element");
        }
        CircularSinglyLinkedListNode<T> currNode = recTraversal(head, size - 2, 0);
        T dataTemp = currNode.getNext().getData();
        currNode.setNext(currNode.getNext().getNext());
        size--;
        return dataTemp;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Selected Index is greater than size");
        }
        if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> currNode = head;
            for (int i = 0; i < index; i++) {
                currNode = currNode.getNext();
            }
            return currNode.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null ? true : false;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data could not be null");
        }
        if (size == 0) {
            throw new NoSuchElementException("List is empty and does not have such element");
        }
        CircularSinglyLinkedListNode<T> currNode = head;
        CircularSinglyLinkedListNode<T> targetNode = null;
        T toBeRet;

        for (int i = 0; i < size - 1; i++) {
            if (currNode.getNext().getData() == data) {
                targetNode = currNode;
            }
            currNode = currNode.getNext();
        }

        if (targetNode == null && head.getData() == data) {
            toBeRet = head.getData();
            if (size == 1) {
                head = null;
            } else {
                removeFromFront();
                size++; // remove from front reduces size by 1
            }
        } else if (targetNode == null) {
            throw new NoSuchElementException("No Such a Element");
        } else {
            toBeRet = targetNode.getNext().getData();
            if (targetNode.getNext().getNext() != null) {
                targetNode.setNext(targetNode.getNext().getNext());
            } else {
                targetNode.setNext(null);
            }
        }
        size--;
        return toBeRet;

    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        CircularSinglyLinkedListNode<T> currNode = head;
        T[] arrRep = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arrRep[i] = currNode.getData();
            currNode = currNode.getNext();
        }
        return arrRep;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
