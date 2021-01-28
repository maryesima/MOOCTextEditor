package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 *
 * @author UC San Diego Intermediate Programming MOOC team
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
  LLNode<E> head;
  LLNode<E> tail;
  int size;

  /** Create a new empty LinkedList */
  public MyLinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Appends an element to the end of the list
   *
   * @param element The element to add
   */
  public boolean add(E element) {
    add(size, element);
    return true;
  }

  /**
   * Get the element at position index
   *
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public E get(int index) {
    if (size <= index || index < 0) {
      throw new IndexOutOfBoundsException(
          String.format("Index must be between 0 and %s but is %s", size - 1, index));
    }
    LLNode node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return (E) node.data;
  }

  /**
   * Add an element to the list at the specified index
   *
   * @param The index where the element should be added
   * @param element The element to add
   */
  public void add(int index, E element) {
    if (size < index || index < 0) {
      throw new IndexOutOfBoundsException(
          String.format("Index must be between 0 and %s but is %s", size - 1, index));
    }
    LLNode new_node = new LLNode(element);
    if (head == null) {
      head = tail = new_node;
    } else if (index == 0) {
      new_node.next = head;
      head.prev = new_node;
      head = new_node;
    } else if (index == size) {
      new_node.prev = tail;
      tail.next = new_node;
      tail = new_node;
    } else {
      LLNode node = head;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
      LLNode next_node = node.next;
      node.next = new_node;
      new_node.prev = node;
      next_node.prev = new_node;
      new_node.next = next_node;
    }
    size++;
  }

  /** Return the size of the list */
  public int size() {
    return size;
  }

  /**
   * Remove a node at the specified index and return its data element.
   *
   * @param index The index of the element to remove
   * @return The data element removed
   * @throws IndexOutOfBoundsException If index is outside the bounds of the list
   */
  public E remove(int index) {
    if (size <= index || index < 0) {
      throw new IndexOutOfBoundsException(
          String.format("Index must be between 0 and %s but is %s", size - 1, index));
    }
    LLNode node_to_remove = head;
    if (size == 1) {
      head = tail = null;
    } else if (index == 0) {
      head = node_to_remove.next;
      head.prev = null;
    } else if (index == size - 1) {
      node_to_remove = tail;
      tail = node_to_remove.prev;
      tail.next = null;
    } else {
      for (int i = 0; i < index; i++) {
        node_to_remove = node_to_remove.next;
      }
      LLNode next_node = node_to_remove.next;
      LLNode prev_node = node_to_remove.prev;
      next_node.prev = node_to_remove.prev;
      prev_node.next = next_node;
    }
    size--;
    return (E) node_to_remove.data;
  }

  /**
   * Set an index position in the list to a new element
   *
   * @param index The index of the element to change
   * @param element The new element
   * @return The element that was replaced
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public E set(int index, E element) {
    if (size <= index || index < 0) {
      throw new IndexOutOfBoundsException(
          String.format("Index must be between 0 and %s but is %s", size - 1, index));
    }
    LLNode node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    E original_element = (E) node.data;
    node.data = element;
    return original_element;
  }
}

class LLNode<E> {
  LLNode<E> prev;
  LLNode<E> next;
  E data;

  // TODO: Add any other methods you think are useful here
  // E.g. you might want to add another constructor

  public LLNode(E e) {
    this.data = e;
    this.prev = null;
    this.next = null;
  }
}
